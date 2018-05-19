package cn.s3bit.droidauxline;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.View;

public class CanvasView extends View {
	public CanvasView(Context context) {
		super(context);
		handler = new Handler();
		handler.postDelayed(refresh, 100);
		paint = new Paint();
	}
	public Handler handler;
	public Paint paint;
	public Runnable refresh = new Runnable() {
		
		@Override
		public void run() {
			invalidate();
			handler.postDelayed(refresh, 100);
		}
	};
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int w = canvas.getWidth();
		int h = canvas.getHeight();
		for (LineDataStructure line : MainService.lineData) {
			paint.setColor(Color.argb(line.ColorA, line.ColorR, line.ColorG, line.ColorB));
			paint.setStrokeWidth(3f);
			canvas.drawLine(
				line.SX + line.SXRef.mul * w,
				line.SY + line.SYRef.mul * h,
				line.EX + line.EXRef.mul * w,
				line.EY + line.EYRef.mul * h,
				paint
			);
		}
	}
}
