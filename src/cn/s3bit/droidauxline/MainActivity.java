package cn.s3bit.droidauxline;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.app.*;
import android.content.Intent;
import android.os.*;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.*;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainActivity extends Activity {
    public Switch onOffSwitch;
    public ListView lineListView;
    public static Intent service;
    public static MainActivity instance;
	@Override
    public void onCreate(Bundle savedInstanceState) {
		instance = this;
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        onOffSwitch = (Switch) (findViewById(R.id.onoffswitch));
        lineListView = (ListView) (findViewById(R.id.listViewLines));
        MainService.lineData = new ArrayList<LineDataStructure>();
        load("__state");
        if (MainService.lineData.size() == 0)
        	MainService.lineData.add(new LineDataStructure());
    	save("__state");
        
        onOffSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					showWindow();
				} else {
					if (service != null) {
						stopService(service);
						service = null;
					}
				}
			}
		});
    }
    
    @Override
    protected void onPause() {
    	save("__state");
    	super.onPause();
    }
    
    @Override
    protected void onDestroy() {
    	save("__state");
    	super.onDestroy();
    }
    
    public void save(String name) {
    	File savefile = new File(getFilesDir(), name);
    	try {
    		FileOutputStream fos = new FileOutputStream(savefile);
			ObjectOutputStream dos = new ObjectOutputStream(fos);
			dos.writeBoolean(onOffSwitch.isChecked());
			dos.writeObject(MainService.lineData);
			dos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @SuppressWarnings("unchecked")
	public void load(String name) {
    	File savefile = new File(getFilesDir(), name);
    	if (!savefile.exists())
    		return;
    	try {
			FileInputStream fis = new FileInputStream(savefile);
			ObjectInputStream dis = new ObjectInputStream(fis);
			onOffSwitch.setChecked(dis.readBoolean());
			onOffSwitch.setChecked(service != null);
			MainService.lineData = (ArrayList<LineDataStructure>)dis.readObject();
			dis.close();
			fis.close();
			lineListView.setAdapter(new LineListAdapter(this, R.layout.linelistitem, MainService.lineData));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @SuppressLint("InlinedApi")
	public void showWindow() {
    	if (Build.VERSION.SDK_INT >= 23) {
            if (Settings.canDrawOverlays(MainActivity.this)) {
            	if (service != null)
            		stopService(service);
                service = new Intent(MainActivity.this, MainService.class);
                startService(service);
            } else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                Toast.makeText(MainActivity.this, "需要取得权限以绘制在最顶端", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        } else {
        	if (service != null)
        		stopService(service);
            service = new Intent(MainActivity.this, MainService.class);
            startService(service);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
    	menu.add("Open").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Dialogs.showOpenSelection();
				menu.close();
				return true;
			}
		});
    	menu.add("Save").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Dialogs.showSaveSelection();
				menu.close();
				return true;
			}
		});
    	return super.onCreateOptionsMenu(menu);
    }
}
