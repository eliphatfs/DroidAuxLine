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
import android.widget.*;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainActivity extends Activity {
    public Switch onOffSwitch;
    public ListView lineListView;
    public static Intent service;
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        onOffSwitch = (Switch) (findViewById(R.id.onoffswitch));
        lineListView = (ListView) (findViewById(R.id.listViewLines));
        MainService.lineData = new ArrayList<LineDataStructure>();
        load();
        if (MainService.lineData.size() == 0)
        	MainService.lineData.add(new LineDataStructure());
        lineListView.setAdapter(new LineListAdapter(this, R.layout.linelistitem, MainService.lineData));
        
        onOffSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					showWindow();
				} else {
					if (service != null)
						stopService(service);
				}
			}
		});
    }
    
    @Override
    protected void onPause() {
    	save();
    	super.onPause();
    }
    
    @Override
    protected void onDestroy() {
    	save();
    	super.onDestroy();
    }
    
    public void save() {
    	File savefile = new File(getFilesDir(), "state");
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
	public void load() {
    	File savefile = new File(getFilesDir(), "state");
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
}
