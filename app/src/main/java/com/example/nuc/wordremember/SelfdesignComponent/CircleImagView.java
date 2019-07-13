package com.example.nuc.wordremember.SelfdesignComponent;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.example.nuc.wordremember.R;

public class CircleImagView extends AppCompatImageView {


    private Paint paint = new Paint();

    public CircleImagView(Context context) {
        super(context);
    }

    public CircleImagView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImagView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //将头像按比例缩放
    private Bitmap scaleBitmap(Bitmap bitmap){
        int width = getWidth();
        //一定要强转成float 不然有可能由于精度不够 出现 scale为0 的错误
        float scale = (float)width/(float)bitmap.getWidth();
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

    }
    //将原始图像裁剪成正方形
    private Bitmap dealRawBitmap(Bitmap bitmap){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //获取宽度
        int minWidth = width > height ?
                height:width ;
        //计算正方形的范围
        int leftTopX = (width - minWidth)/2;
        int leftTopY = (height - minWidth)/2;
        //裁剪成正方形
        Bitmap newBitmap = Bitmap.createBitmap(bitmap,leftTopX,leftTopY,minWidth,minWidth,null,false);
        return  scaleBitmap(newBitmap);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (null != drawable) {
            Bitmap rawBitmap =((BitmapDrawable)drawable).getBitmap();

            //处理Bitmap 转成正方形
            Bitmap newBitmap = dealRawBitmap(rawBitmap);
            //将newBitmap 转换成圆形
            Bitmap circleBitmap = toRoundCorner(newBitmap, 14);

            final Rect rect = new Rect(0, 0, circleBitmap.getWidth(), circleBitmap.getHeight());
            paint.reset();
            //绘制到画布上
            canvas.drawBitmap(circleBitmap, rect, rect, paint);
        } else {
            super.onDraw(canvas);
        }
    }

    private Bitmap toRoundCorner(Bitmap bitmap, int pixels) {

        //指定为 ARGB_4444 能够减小图片大小
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Rect rect = new Rect(0, 0,bitmap.getWidth(), bitmap.getHeight());
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        int x = bitmap.getWidth();
        canvas.drawCircle(x / 2, x / 2, x / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }
//    private float width;
//    private float height;
//    private float radius;
//    private Paint paint;
//    private Matrix matrix;
//
//    public CircleImagView(Context context) {
//        this(context, null);
//    }
//
//    public CircleImagView(Context context, @Nullable AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public CircleImagView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        paint = new Paint();
//        paint.setAntiAlias(true);   //设置抗锯齿
//        matrix = new Matrix();      //初始化缩放矩阵
//    }
//
//    /**
//     * 测量控件的宽高，并获取其内切圆的半径
//     */
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        width = getMeasuredWidth();
//        height = getMeasuredHeight();
//        radius = Math.min(width, height) / 2;
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        Drawable drawable = getDrawable();
//        if (drawable == null) {
//            super.onDraw(canvas);
//            return;
//        }
//        if (drawable instanceof BitmapDrawable) {
//            paint.setShader(initBitmapShader((BitmapDrawable) drawable));//将着色器设置给画笔
//            canvas.drawCircle(width / 2, height / 2, radius, paint);//使用画笔在画布上画圆
//            return;
//        }
//        super.onDraw(canvas);
//    }
//
//    /**
//     * 获取ImageView中资源图片的Bitmap，利用Bitmap初始化图片着色器,通过缩放矩阵将原资源图片缩放到铺满整个绘制区域，避免边界填充
//     */
//    private BitmapShader initBitmapShader(BitmapDrawable drawable) {
//        Bitmap bitmap = drawable.getBitmap();
//        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        float scale = Math.max(width / bitmap.getWidth(), height / bitmap.getHeight());
//        matrix.setScale(scale, scale);//将图片宽高等比例缩放，避免拉伸
//        bitmapShader.setLocalMatrix(matrix);
//        return bitmapShader;
//    }
}
