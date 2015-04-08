package activity.ds.qianfeng.com.zidingyiview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2015/3/12.
 */
public class CustomHomwork extends View {
    public CustomHomwork(Context context) {
        super(context);
    }

    public CustomHomwork(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomHomwork(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.drawColor(Color.WHITE);
        Paint paint=new Paint();
        paint.setColor(Color.BLACK);
        canvas.save();
        Path path=new Path();
        //柱状图原点
        path.moveTo(10,150);
        path.lineTo(10,100);
        canvas.restore();
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
