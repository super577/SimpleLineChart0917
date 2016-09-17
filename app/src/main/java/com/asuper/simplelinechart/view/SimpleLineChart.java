package com.asuper.simplelinechart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.asuper.simplelinechart.data.MyData;

import java.util.ArrayList;
import java.util.List;

public class SimpleLineChart extends View {
    //属性定义：
    private int width;
    private int height;
    private int spaceWidth;
    private int spaceHeight;
    private int outerBottomSpace;
    //灰色背景的画笔
    private Paint paint_bg;
    //灰色网格的画笔
    private Paint paint_gridline;
    //每个点对应的文本数据的画笔
    private Paint paint_text;
    //折线圆点的蓝色背景
    private Paint paint_point_bg;
    //折线圆点的白色表面
    private Paint paint_point_sur;
    //阴影路径的画笔
    private Paint paint_path;
    //折线的画笔
    private Paint paint_brokenline;
    //路径
    private Path path=new Path();
    //客户拜访的折线（BrokenLineCusVisit）数据
    private List<MyData> mData=new ArrayList();
    public SimpleLineChart(Context context) {
        super(context);
    }
    public SimpleLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        inite(context);
    }

    /**
     * jhg
     * @param context
     */
    private void inite(Context context) {
        paint_bg=new Paint(Paint.ANTI_ALIAS_FLAG);//带去锯齿效果的画笔
        paint_bg.setColor(Color.parseColor("#e4e3e4"));
        paint_gridline=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint_gridline.setColor(Color.argb(0xff,0xce,0xCB,0xce));
        paint_brokenline=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint_brokenline.setColor(Color.RED);
        paint_brokenline.setTextSize(22);
//        paint_brokenline.setTextAlign(Paint.Align.CENTER);
        paint_point_bg=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint_point_bg.setColor(Color.BLUE);
        paint_point_sur=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint_point_sur.setColor(Color.WHITE);
        //注意path的画笔的透明度已经改变了
        paint_path=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint_path.setColor(Color.parseColor("#81d741"));
        paint_text=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint_text.setColor(Color.BLACK);
        paint_text.setTextAlign(Paint.Align.CENTER);

        invalidate();
    }

    public List<MyData> getMdata() {
        return mData;
    }

    //data的set/get方法，用于设置数据，外部传入数据
    public void setMdata(List<MyData> myData) {
        Log.i("TAG", "getMdata: setData=="+myData.size());
        this.mData = myData;
        /**
         * View的生命周期方法requestLayout()触发
         * measure-->onMeasure-->layout-->onLayout生命周期方法
         */
        requestLayout();
        /**
         * View的生命周期方法invalidate()触发
         * dispatchDraw-->draw-->onDraw生命周期方法
         */
        invalidate();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i("TAG", "onMeasure: ");
        /**
         * getDefaultSize作用是返回一个默认的值，如果MeasureSpec没有强制限制的话
         * 则使用提供的大小.否则在允许范围内可任意指定大小
         * 第一个参数size为提供的默认大小，第二个参数为测量的大小
         public static int getDefaultSize(int size, int measureSpec) {
         int result = size;
         int specMode = MeasureSpec.getMode(measureSpec);
         int specSize = MeasureSpec.getSize(measureSpec);
         switch (specMode) {
         // Mode = UNSPECIFIED,AT_MOST时使用提供的默认大小
         case MeasureSpec.UNSPECIFIED:
         result = size;
         break;
         case MeasureSpec.AT_MOST:
         // Mode = EXACTLY时使用测量的大小
         case MeasureSpec.EXACTLY:
         result = specSize;
         break;
         }
         return result;
         }
         */
        /**
         * 初始化各种长度参数
         *666
         */
        //设置宽度
        spaceWidth=80;
        if(mData.size()==0){
            Log.i("TAG", "onMeasure: mData.size()=="+mData.size());
            width=getDefaultSize(getSuggestedMinimumWidth(),widthMeasureSpec);
        }else{
            //根据数据的条数设置宽度
            width=spaceWidth*mData.size()+15;
        }
        //设置高度
        height=getDefaultSize(getSuggestedMinimumHeight(),heightMeasureSpec);
        outerBottomSpace=50;
        spaceHeight=(height-outerBottomSpace-5)/5;
        setMeasuredDimension(width+spaceWidth,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i("TAG","onDraw:");
        super.onDraw(canvas);
        //绘制整个View的白色背景
        canvas.drawColor(Color.WHITE);
        //绘制灰色阴影大矩形
        canvas.drawRect(15,5,width+spaceWidth,height-outerBottomSpace,paint_bg);

        //绘制网格线，横向的；Y轴不变 X轴绘制直线
        for (int j=0;j<5;j++){
            canvas.drawLine(15,spaceHeight*(j+1)+5,width+spaceWidth,spaceHeight*(j+1)+5,paint_gridline);
        }
        //绘制纵向网格线,X轴不变（多绘制左右各一条）
        for (int i= 0; i < mData.size()+2;i++) {
            canvas.drawLine(spaceWidth * i + 15, 5, spaceWidth * i + 15, height-outerBottomSpace,paint_gridline);
        }

        //绘制点和具体数据
        for(int i=0; i<mData.size();i++){
            //绘制圆点，圆点位置根据网格线的位置确定
            canvas.drawCircle(spaceWidth * (i+1) + 15, height - outerBottomSpace - (height - outerBottomSpace) * mData.get(i).getAmount()/100, 7, paint_point_bg);
            canvas.drawCircle(spaceWidth * (i+1) + 15,  height - outerBottomSpace - (height - outerBottomSpace) * mData.get(i).getAmount()/100, 4, paint_point_sur);

            //绘制数据的数值
            String data=mData.get(i).getAmount()+"";
            canvas.drawText(data,spaceWidth*(i+1)+15,height - outerBottomSpace - (height - outerBottomSpace) * mData.get(i).getAmount()/100-
                    paint_brokenline.measureText(data),paint_text);

            //绘制底部空白处：数据的日期
            String date=mData.get(i).getDate();
            canvas.drawText(date, spaceWidth * (i+1) + 15, height - outerBottomSpace / 2, paint_text);


//            if (i==mData.size()-1){
//                //绘制完最后一个点，path需要沿着纵轴向下到达height - outerBottomSpace 的位置再水平到达（15，height - outerBottomSpace ）的位置，最后闭合
//                path.quadTo(spaceWidth * (i+1) + 15, height - outerBottomSpace - (height - outerBottomSpace) * mData.get(i).getAmount() / 100,
//                        spaceWidth * (i+1) + 15, outerBottomSpace );
//                path.quadTo(spaceWidth * (i+1) + 15, height - outerBottomSpace ,15, height - outerBottomSpace);
//                path.close();
//            }
            for (int j = 0; j < mData.size() - 1; j++) {
                canvas.drawLine(spaceWidth * (j+1) + 15, height - outerBottomSpace - (height - outerBottomSpace) * mData.get(j).getAmount()/100,
                        spaceWidth * (j+2) + 15, height - outerBottomSpace - (height - outerBottomSpace) * mData.get(j+1).getAmount()/100,paint_brokenline);
            }
        }

    //        //最终在画布上绘制path
//        canvas.drawPath(path,paint_path);

    }

}


