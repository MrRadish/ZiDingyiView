package activity.ds.qianfeng.com.zidingyiview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

/**
 * Created by Administrator on 2015/3/12.
 */
public class CustomView extends View {
    /**
     * java代码中使用
     * @param context
     */
    public CustomView(Context context) {
        super(context);
    }

    /**
     * 该方法在xml中使用
     * @param context
     * @param attrs
     */
    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        start();
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义view的属性值
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.CustomView,defStyleAttr,0);
        Drawable drawable=typedArray.getDrawable(R.styleable.CustomView_src);
        if(drawable!=null&&drawable instanceof BitmapDrawable){
            bitmap= ((BitmapDrawable) drawable).getBitmap();
        }
        color=typedArray.getColor(R.styleable.CustomView_hour_color,Color.WHITE);
        start();
    }
    private int color;
    private Bitmap bitmap;
    private void start(){
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        }
        new Thread(){
            @Override
            public void run() {
                while (true){
                    //子线程中,提醒刷新
                    postInvalidate();
                    //主线程中刷新
//                    invalidate();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
    /**
     * 绘制
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
//        drawText(canvas);
//        getJuzhen(canvas);
        //绘制钟表
        canvas.save();
        float temp=Math.min(getWidth()/200f,getHeight()/200f);
        //缩放比例
        canvas.scale(temp,temp);

        canvas.drawColor(Color.BLACK);
        {
//            Paint paint = new Paint();
//            //绘制背景
//            paint.setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT,Shader.TileMode.REPEAT));
//            canvas.drawCircle(100, 100, 100, paint);

        }
        //绘制圆圈
        Paint paint=new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        canvas.drawCircle(100,100,100,paint);
        canvas.save();
        for (int i=0;i<12;i++){
            if(i%3==0){
                paint.setStrokeWidth(3);
            }else {
                paint.setStrokeWidth(1);
            }
            //绘制表盘刻度
            canvas.drawLine(100,0,100,10,paint);
            //顺时针旋转
            canvas.rotate(30,100,100);
        }
        canvas.restore();
        //获取日历类
        Calendar calendar=Calendar.getInstance();
        canvas.save();
        //时针矩阵
        canvas.rotate(30*calendar.get(Calendar.HOUR)+30*calendar.get(Calendar.MINUTE)/60f,100,100);
        Path path=new Path();
        path.moveTo(100,30);
        path.lineTo(110, 100);
        path.lineTo(100,100);
        path.lineTo(90,100);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, paint);
        canvas.restore();
        //分针矩阵
        canvas.save();
        canvas.rotate(6*calendar.get(Calendar.MINUTE)+6*calendar.get(Calendar.SECOND)/60f,100,100);
        Path path1=new Path();
        path1.moveTo(100,10);
        path1.lineTo(105,100);
        path1.lineTo(100,100);
        path1.lineTo(95,100);
        paint.setColor(Color.GREEN);
//        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path1,paint);
        canvas.restore();
        //秒针
        canvas.save();
        canvas.rotate(6*calendar.get(Calendar.SECOND)+calendar.get(Calendar.MILLISECOND)/1000f,100,100);
        canvas.drawLine(100,10,100,110,paint);
        canvas.restore();
        canvas.restore();
    }

    private void getJuzhen(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        Paint paint=new Paint();
        //边框
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.WHITE);
        //存储当前矩阵
        canvas.save();
        canvas.scale(2,1,100,100);
        canvas.drawCircle(100,100,30,paint);
        //回复上一次存储的矩阵
        canvas.restore();
        paint.setColor(Color.RED);
        canvas.drawCircle(100,100,30,paint);
        //存储当前矩阵加标签
//        canvas.save(1);
//        //回复到指定矩阵
//        canvas.restoreToCount(1);
//        //长方形
//        canvas.drawRect(10,10,30,50,paint);
//        //圆
//        canvas.drawCircle(100,100,30,paint);
        //
//        canvas.drawOval(50,200,80,250,paint);
        //菱形
//        Path path=new Path();
//        path.moveTo(100,100);
//        path.lineTo(120,120);
//        path.lineTo(100,140);
//        path.lineTo(80,120);
//        path.lineTo(100,100);
//        canvas.drawPath(path,paint);
    }

    public void drawText(Canvas canvas){
        canvas.drawColor(Color.YELLOW);
        Paint paint=new Paint();
        paint.setColor(0xff00ff00);
        //左右中心点
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(30);
        //抗锯齿
        paint.setAntiAlias(true);
        //坐标原点在画布左上角，x轴正方向向右，y轴正方向向下，单位长度是像素
        int y=getHeight()/2;
        canvas.drawText("自定义viewgl",getWidth()/2,getHeight()/2,paint);
        //字体
        Paint.FontMetrics font=paint.getFontMetrics();
        paint.setColor(0xffff0000);
        canvas.drawLine(0,y,getWidth(),y,paint);
        paint.setColor(Color.BLUE);
        canvas.drawLine(0,y+font.bottom,getWidth(),y+font.bottom,paint);
        paint.setColor(Color.BLACK);
        canvas.drawLine(0,y+font.top,getWidth(),y+font.top,paint);
        paint.setColor(Color.WHITE);
        canvas.drawLine(0,y+font.ascent,getWidth(),y+font.ascent,paint);
        paint.setColor(Color.CYAN);
        canvas.drawLine(0,y+font.descent,getWidth(),y+font.descent,paint);
        paint.setColor(Color.GREEN);
        float temp=(font.top+font.bottom)/2;
        canvas.drawLine(0,y+temp,getWidth(),y+temp,paint);
        paint.setColor(Color.WHITE);
        canvas.drawText("中心文字",getWidth()/2,y-temp,paint);
    }
    /**
     * 计算尺寸
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
