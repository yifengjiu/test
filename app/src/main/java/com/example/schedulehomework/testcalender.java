package com.example.schedulehomework;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.icu.util.Calendar;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.schedulehomework.controller.Controller;
import com.example.schedulehomework.entity.Activitytasks;
import com.example.schedulehomework.entity.CalenderBean;
import com.example.schedulehomework.entity.Learningtasks;
import com.example.schedulehomework.entity.SimpleNEUClass;

import java.util.ArrayList;

public class testcalender extends View {

    //数据
    private String[] weekStr = {"日","一","二","三","四","五","六"};
    private int viewMargin = 80;//绘制日历文字的距离日历的左右间隔
    private float posweekStrY ;//绘制日期文字的画笔所在的y轴坐标
    private float lineTextDest = 20;//线与文字上、下的间距
    private int canvasWidth;
    private int widthSize;
    private int heightSize;
    private float poslineBottomY;//绘制第二根线的画笔所在的y轴坐标
    private float pointRadius  = 5;//小点半径
    private float pointMarginTop = 20;//小球顶部距离文字的距离

    //数据
    int sizeWidth;
    int sizeHeigth;
    int MonthDays;
    ArrayList<CalenderBean> CalenderBeans=new ArrayList<>();
    int Weekturn;//本月第一天是周几
    int DrawLineTurn=0;
    float AreaWidth=0;//单元块宽度
    float AreaLength=80;//单元块长度
    Controller con=Controller.getController();
    int turn=con.getTurn();
    ArrayList<Learningtasks> learningtasks=new ArrayList<>();
    ArrayList<SimpleNEUClass> simpleNEUClasses=new ArrayList<>();
    private ArrayList<Activitytasks> activitytasks=new ArrayList<>();

    private ClickListener clickListener;//回调接口

    private int currentYear;
    private int currentMonth;
    private int currentDay;

    //线画笔
    private Paint mPaintLine;
    //圆点画笔
    private Paint mPaintPoint;
    //文字画笔
    private Paint mPaintTv;
    //背景图画笔
    private Paint mPaintBg;

    public testcalender(Context context) {
        super(context);
    }
    //构造方法
    public testcalender(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public void init(){
        initView();

        Calendar calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH)+1;
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        con.setCurrentMonth(currentMonth);
        con.setCurrentDay(currentDay);

        //当前月份天数
        MonthDays=getMonthDays(currentYear,currentMonth);
        //创建相对应的CalenderBean并加入arraylist
        for(int i=0;i<MonthDays;i++){
            CalenderBean cb=new CalenderBean(i);
            CalenderBeans.add(cb);
        }
        //判定第一天是周几
        Calendar ca=Calendar.getInstance();
        int TodayTurn=ca.get(Calendar.DAY_OF_WEEK)-1;//今天周几
        con.setDayturn(TodayTurn);
        Weekturn=TodayTurn-currentDay%7+1;//月初周几
        con.setFirstWeekTurn(Weekturn);
        int TodayWeek=chargeWeek(currentMonth,currentDay,Weekturn);
        con.setTodayWeekturn(TodayWeek);//当前周数
    }
    //绘制
    protected void onDraw(Canvas canvas) {
        DrawLineTurn=0;
        super.onDraw(canvas);
        //绘制顶部横线
        canvas.drawLine(0,0,sizeWidth,0,mPaintLine);
        //令中心线为x轴
        posweekStrY =60;//= lineTextDest-(mPaintTv.getFontMetrics().ascent + mPaintTv.getFontMetrics().leading);
        //绘制日期
        for (int i = 0; i < weekStr.length; i++) {
            canvas.drawText(
                    weekStr[i],
                    viewMargin + mPaintTv.measureText("11")/2 + i * (sizeWidth - viewMargin*2 - mPaintTv.measureText("11")) / 6,
                    posweekStrY,
                    mPaintTv
            );
        }
        //绘制日期下虚线
        canvas.drawLine(0,posweekStrY*2,sizeWidth,posweekStrY*2,mPaintLine);
        //绘制月份对应天数
        for (int i= 0; i <MonthDays; i++) {
            //数据处理
            if(((i+Weekturn)%7==0)&&(i!=0)){
                DrawLineTurn=DrawLineTurn+2;
            }
            //绘制当日深色
            if(currentDay==(i+1)){
                canvas.drawCircle(
                        viewMargin + mPaintTv.measureText("11")/2 + (i+Weekturn)%7* (sizeWidth - viewMargin*2 - mPaintTv.measureText("11")) / 6,
                        posweekStrY*(DrawLineTurn+3),
                        30,
                        mPaintBg

                );
            }
           int num=i+1;
            canvas.drawText(
                    Integer.toString(num),
                    viewMargin + mPaintTv.measureText("11")/2 + (i+Weekturn)%7* (sizeWidth - viewMargin*2 - mPaintTv.measureText("11")) / 6,
                    posweekStrY*(DrawLineTurn+3),
                    mPaintTv
            );
            //绘制开始时间
            for(Learningtasks learningtasks1:learningtasks){
                int daynum=learningtasks1.getDay();
                if((daynum==num)&&(learningtasks1.getOver()==0)){
                    mPaintPoint.setColor(Color.YELLOW);
                    canvas.drawCircle(viewMargin + mPaintTv.measureText("11")/2 + (i+Weekturn)%7* (sizeWidth - viewMargin*2 - mPaintTv.measureText("11")) / 6,
                            posweekStrY*(DrawLineTurn+3)+30,
                            10,
                            mPaintPoint);
                }
            }
            for(Activitytasks activitytasks1:activitytasks){
                if((activitytasks1.getDay()==num)&&(activitytasks1.getOver()==0)){
                    mPaintPoint.setColor(Color.YELLOW);
                    canvas.drawCircle(viewMargin + mPaintTv.measureText("11")/2 + (i+Weekturn)%7* (sizeWidth - viewMargin*2 - mPaintTv.measureText("11")) / 6,
                            posweekStrY*(DrawLineTurn+3)+30,
                            10,
                            mPaintPoint);
                }
            }
            //绘制活动任务
            for(Activitytasks activitytasks1:activitytasks){
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(activitytasks1.getFinfish());
                int daynum=calendar.get(Calendar.DATE);
                if((daynum==num)&&(activitytasks1.getOver()==0)){
                    mPaintPoint.setColor(Color.RED);
                    canvas.drawCircle(viewMargin + mPaintTv.measureText("11")/2 + (i+Weekturn)%7* (sizeWidth - viewMargin*2 - mPaintTv.measureText("11")) / 6+20,
                            posweekStrY*(DrawLineTurn+3)+30,
                            10,
                            mPaintPoint);
                }
            }
            //绘制学习任务
            for(Learningtasks learningtasks1:learningtasks){
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(learningtasks1.getFinfish());
                int daynum=calendar.get(Calendar.DATE);
                if((daynum==num)&&(learningtasks1.getOver()==0)){
                    mPaintPoint.setColor(Color.BLUE);
                    canvas.drawCircle(viewMargin + mPaintTv.measureText("11")/2 + (i+Weekturn)%7* (sizeWidth - viewMargin*2 - mPaintTv.measureText("11")) / 6-20,
                            posweekStrY*(DrawLineTurn+3)+30,
                            10,
                            mPaintPoint);
                }
            }
        }
        mPaintPoint.setColor(Color.YELLOW);
        canvas.drawCircle(viewMargin + mPaintTv.measureText("11")/2 + (1)%7* (sizeWidth - viewMargin*2 - mPaintTv.measureText("11")) / 6-120,
                posweekStrY*(DrawLineTurn+3)+50,
                10,
                mPaintPoint);
        canvas.drawText(
                "任务开始日",
                viewMargin + mPaintTv.measureText("11")/2 + (2)%7* (sizeWidth - viewMargin*2 - mPaintTv.measureText("11")) / 6-120,
                posweekStrY*(DrawLineTurn+3)+58,
                mPaintTv
        );
        mPaintPoint.setColor(Color.RED);
        canvas.drawCircle(viewMargin + mPaintTv.measureText("11")/2 + (5)%7* (sizeWidth - viewMargin*2 - mPaintTv.measureText("11")) / 6-120,
                posweekStrY*(DrawLineTurn+3)+50,
                10,
                mPaintPoint);
        mPaintPoint.setColor(Color.BLUE);
        canvas.drawText(
                "活动任务截止日",
                viewMargin + mPaintTv.measureText("11")/2 + (6)%7* (sizeWidth - viewMargin*2 - mPaintTv.measureText("11")) / 6-120,
                posweekStrY*(DrawLineTurn+3)+58,
                mPaintTv
        );
        canvas.drawCircle(viewMargin + mPaintTv.measureText("11")/2 + (10)%7* (sizeWidth - viewMargin*2 - mPaintTv.measureText("11")) / 6-120,
                posweekStrY*(DrawLineTurn+3)+50,
                10,
                mPaintPoint);
        canvas.drawText(
                "学习任务截止日",
                viewMargin + mPaintTv.measureText("11")/2 + (11)%7* (sizeWidth - viewMargin*2 - mPaintTv.measureText("11")) / 6-120,
                posweekStrY*(DrawLineTurn+3)+58,
                mPaintTv
        );
        canvas.drawLine(0,posweekStrY*(DrawLineTurn+3)+88,sizeWidth,posweekStrY*(DrawLineTurn+3)+88,mPaintLine);
        canvas.drawLine(0,posweekStrY*(DrawLineTurn+3)+95,sizeWidth,posweekStrY*(DrawLineTurn+3)+95,mPaintLine);
        canvas.drawText(
                "学习任务列表：",
                viewMargin + mPaintTv.measureText("11")/2 + (1)%7* (sizeWidth - viewMargin*2 - mPaintTv.measureText("11")) / 6-120,
                posweekStrY*(DrawLineTurn+3)+138,
                mPaintTv
        );

    }

    public boolean onTouchEvent(MotionEvent event) {
        //计算单元格宽度
        AreaWidth=(sizeWidth-2*viewMargin)/7;
        //获取点击位置,取得的是相对本view的位置
        int x = (int) event.getX();
        int y = (int) event.getY();
        int action = event.getAction();
        //判断点击的天数
        int Daynum=CheckDay(x,y);
            turn=con.getTurn();
            if(turn==0) {
                turn++;
                con.setTurn(turn);
                con.setDaynum(Daynum);
               if (clickListener != null) {
                   clickListener.Click(Daynum);
                   turn=con.getTurn();
              }
            }

        return true;
    }


    //数据初始化
    public void initView(){
        mPaintLine = new Paint();
        mPaintLine.setAntiAlias(true);
        //绘制图形内容
        mPaintLine.setStyle(Paint.Style.FILL);
        mPaintLine.setColor(Color.GRAY);
        //画笔宽度
        mPaintLine.setStrokeWidth(3);

        mPaintTv = new Paint();
        mPaintTv.setAntiAlias(true);
        mPaintTv.setStyle(Paint.Style.FILL);
        mPaintTv.setTextSize(32);
        mPaintTv.setColor(Color.BLACK);
        //设置绘制点为中心点
        mPaintTv.setTextAlign(Paint.Align.CENTER);

        mPaintPoint = new Paint();
        mPaintPoint.setAntiAlias(true);
        mPaintPoint.setStyle(Paint.Style.FILL);
        mPaintPoint.setStrokeWidth(0.1f);

        mPaintBg = new Paint();
        mPaintBg.setAntiAlias(true);
        mPaintBg.setStyle(Paint.Style.FILL);
        mPaintBg.setColor(Color.parseColor("#E5E5E5"));

        //任务状态导入
        simpleNEUClasses=con.getClasses();
        learningtasks=con.getLearningtasks();
        activitytasks=con.getActivitytasks();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 宽和高的计算模式
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        sizeHeigth = MeasureSpec.getSize(heightMeasureSpec);

    }

    public static int getMonthDays(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, (month - 1));
        cal.set(Calendar.DATE, 1);
        cal.roll(Calendar.DATE, -1);
        return cal.getActualMaximum(Calendar.DATE);
    }

    public int CheckDay(int x,int y){
        int xNum=(int)Math.ceil((double)(x-80)/131);
        int yNum=(int)Math.ceil((double)(y-120)/120-1);//(y-80)/80)-1;
        int Daynumpre=yNum*7+xNum;
        //确认点击区域是否在本月内
        if((Daynumpre>Weekturn)&&(Daynumpre<=(Weekturn+MonthDays))){
            int Daynum=Daynumpre-Weekturn;
            return Daynum;
        }
        return 0;
    }
    public void setMyOnClickListener(ClickListener cl) {
        clickListener=cl;
    }
    public interface ClickListener {
        void Click(int num);
    }
    //判断当前周数
    public int chargeWeek(int currentMonth,int currentDay,int Weekturn){
        int Dayweek=0;
        //月份第一天对应周数
        int [] turnDouble={0,0,0,0,6,10,14,18};
        int monthWeekTurn=turnDouble[currentMonth];//第一天周数
        if(currentDay<=(7-Weekturn)){
            return monthWeekTurn;
        }
        else{
            int floor= (int) Math.floor((currentDay-(7-Weekturn))/7);
            Dayweek=monthWeekTurn+floor+1;
        }

        return Dayweek;
    }



}

