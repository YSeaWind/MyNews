package com.lenovo.mynews.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

public class CircleImage extends AppCompatImageView {
    private Paint paint; //画笔
    private int radius;//半径
    private float scale;//缩放比例

    public CircleImage(Context context) {
        super(context);
    }

    public CircleImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //确定圆的半径
        int min = Math.min(getMeasuredWidth(), getMeasuredHeight());
        radius = min / 2;
        setMeasuredDimension(min, min);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint = new Paint();
        Drawable drawable = getDrawable();
        //获取位图对象
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        //获取着色器
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.CLAMP);
        //计算缩放比例
        scale = (radius * 2.0f) / Math.min(bitmap.getHeight(), bitmap.getWidth());
        //转换坐标 矩阵
        Matrix matrix = new Matrix();
        //控制 x y轴的缩放比例
        matrix.setScale(scale, scale);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, paint);
    }
}
