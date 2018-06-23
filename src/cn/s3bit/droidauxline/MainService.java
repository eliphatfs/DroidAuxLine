package cn.s3bit.droidauxline;

import java.util.ArrayList;

import android.app.Application;
import android.app.Service;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.WindowManager;

public class MainService extends Service {

    public static ArrayList<LineDataStructure> lineData;
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	CanvasView canvasView;
	@Override
	public void onCreate() {
		canvasView = new CanvasView(this);
		WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.format = PixelFormat.TRANSPARENT;
		WindowManager windowManager = (WindowManager) this.getSystemService(Application.WINDOW_SERVICE);
		if (windowManager.getDefaultDisplay().getRotation() == Surface.ROTATION_0 || windowManager.getDefaultDisplay().getRotation() == Surface.ROTATION_180)
			params.screenOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
		else
			params.screenOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        windowManager.addView(canvasView, params);
		super.onCreate();
	}
	
	@Override
	public void onDestroy() {
		WindowManager windowManager = (WindowManager) this.getSystemService(Application.WINDOW_SERVICE);
        windowManager.removeView(canvasView);
		super.onDestroy();
	}
}
