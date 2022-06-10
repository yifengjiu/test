package com.example.schedulehomework;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schedulehomework.controller.Controller;
import com.example.schedulehomework.entity.Activitytasks;
import com.example.schedulehomework.entity.Learningtasks;
import com.example.schedulehomework.entity.SimpleNEUClass;
import com.example.schedulehomework.utils.IOutils;

import java.util.ArrayList;
import java.util.HashMap;

public class keyfun extends AppCompatActivity {
    private PopupWindow mPopWindow;
    Controller con=Controller.getController();

    private Context mycontext=this;
    int turn=0;
    private ArrayList<SimpleNEUClass> classes=new ArrayList<>();
    private ArrayList<Learningtasks> learningtasks=new ArrayList<>();
    private ArrayList<Activitytasks> activitytasks=new ArrayList<>();
    private int currentMonth;//现实今天是几月
    private int Dayturn;//现实今天是周几
    private int TodayWeekturn;//现实第几教学周
    private int FirstWeekTurn;//月初第一天是周几
    private ArrayList<SimpleNEUClass> Todayclasses=new ArrayList<>();
    private ArrayList<Button> buttons=new ArrayList<>();
    private TextView class1;
    private TextView class2;
    private TextView class3;
    private TextView class4;
    private TextView class5;
    private TextView class6;
    //利用选择的天数判断，该日是第几周，周几
    private int SelectDayturn;//选择天数周几  1234567
    private int SelectWeekTurn;//选择天数是第几周
    private int TodayNum;//选择的天数是几号

    private int taxkTurn=0;//按钮设定标识数字

    private Button Creat;
    private Button preInfo;

    Button work1=null;
    Button work2=null;
    Button work3=null;
    Button work4=null;
    Button work5=null;
    private Activity mActivity;

    ListView lv1,lv2;
    private testcalender tc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyfun);

        tc=findViewById(R.id.testcalender);
        tc.setMyOnClickListener(new testcalender.ClickListener() {
            @Override
            public void Click(int num) {
                System.out.println(con.getDaynum());
                turn=con.getTurn();
                if(con.getDaynum()!=0){
                    TodayNum=con.getDaynum();
                    showPopupWindow(con.getDaynum());
               }

           }
        });
        init();

    }
    //弹出窗口
    private void showPopupWindow(int num) {
        taxkTurn=0;

        //获取选择天数是第几周
        SelectWeekTurn=chargeWeek(currentMonth,num,FirstWeekTurn);
        SelectDayturn=chargeWeekDay(num,FirstWeekTurn);
        //设置contentView
        View contentView = LayoutInflater.from(keyfun.this).inflate(R.layout.popuplayout, null);
        mPopWindow = new PopupWindow(contentView);
        mPopWindow.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        mPopWindow.setHeight(ViewGroup.LayoutParams.FILL_PARENT);

        //设置各个控件的点击响应
        TextView tv3 = (TextView)contentView.findViewById(R.id.pop_manage);
        TextView tv4 = (TextView)contentView.findViewById(R.id.pop_num);
        //tv3.setOnClickListener(this);

         class1=(TextView)contentView.findViewById(R.id.Class0);
         class2=(TextView)contentView.findViewById(R.id.Class1);
         class3=(TextView)contentView.findViewById(R.id.Class2);
         class4=(TextView)contentView.findViewById(R.id.Class3);
         class5=(TextView)contentView.findViewById(R.id.Class4);
         class6=(TextView)contentView.findViewById(R.id.Class5);
         setClass(class1,1, SelectWeekTurn,SelectDayturn);
         setClass(class2,3, SelectWeekTurn,SelectDayturn);
         setClass(class3,5, SelectWeekTurn,SelectDayturn);
         setClass(class4,7, SelectWeekTurn,SelectDayturn);
         setClass(class5,9, SelectWeekTurn,SelectDayturn);
         setClass(class6,11, SelectWeekTurn,SelectDayturn);


        work1=contentView.findViewById(R.id.work01);
        work2=contentView.findViewById(R.id.work02);
        work3=contentView.findViewById(R.id.work03);
        work4=contentView.findViewById(R.id.work04);
        work5=contentView.findViewById(R.id.work05);
        work1.setVisibility(View.INVISIBLE);
        work2.setVisibility(View.INVISIBLE);
        work3.setVisibility(View.INVISIBLE);
        work4.setVisibility(View.INVISIBLE);
        work5.setVisibility(View.INVISIBLE);
        setTask(work1);
        setTask(work2);
        setTask(work3);
        setTask(work4);
        setTask(work5);


        work1.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                Toast.makeText(mycontext, "您完成了该任务", Toast.LENGTH_SHORT).show();
                work1.setVisibility(View.INVISIBLE);
                //对文件进行操作
                delettask(work1.getText().toString());
                //mActivity.recreate();
                return true;
            }
        });
        work1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlertDialog(mycontext,work1.getText().toString(),work1);
            }

        });


        work2.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                Toast.makeText(mycontext, "您完成了该任务", Toast.LENGTH_SHORT).show();
                work2.setVisibility(View.INVISIBLE);
                //对文件进行操作
                delettask(work2.getText().toString());
                return true;
            }
        });
        work2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlertDialog(mycontext,work2.getText().toString(),work2);
            }

        });


        work3.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                Toast.makeText(mycontext, "您完成了该任务", Toast.LENGTH_SHORT).show();
                work3.setVisibility(View.INVISIBLE);
                //对文件进行操作
                delettask(work3.getText().toString());

                return true;
            }
        });
        work3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlertDialog(mycontext,work3.getText().toString(),work3);
            }

        });


        //对work2进行点击时间监听
        //work2.setOnClickListener(this);
        work4.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                Toast.makeText(mycontext, "您完成了该任务", Toast.LENGTH_SHORT).show();
                work4.setVisibility(View.INVISIBLE);
                //对文件进行操作
                delettask(work4.getText().toString());
                return true;
            }
        });
        work4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlertDialog(mycontext,work4.getText().toString(),work4);
            }

        });


        //对work2进行点击时间监听
        //work2.setOnClickListener(this);
        work5.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                Toast.makeText(mycontext, "您完成了该任务", Toast.LENGTH_SHORT).show();
                work5.setVisibility(View.INVISIBLE);
                //对文件进行操作
                delettask(work5.getText().toString());
                return true;
            }
        });
        work5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlertDialog(mycontext,work5.getText().toString(),work5);
            }

        });



        //显示PopupWindow
        mPopWindow.setAnimationStyle(R.style.contextMenuAnim);
        View rootview = LayoutInflater.from(keyfun.this).inflate(R.layout.activity_main, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭弹出窗口
                mPopWindow.dismiss();
                turn--;
                con.setTurn(turn);
            }
        });
    }
    public void initView(){
        con.refresh(mycontext);
        Dayturn=con.getDayturn();
        TodayWeekturn=con.getTodayWeekturn();
        classes=con.getClasses();
        currentMonth=con.getCurrentMonth();
        FirstWeekTurn=con.getFirstWeekTurn();
        learningtasks=con.getLearningtasks();
        activitytasks=con.getActivitytasks();
        buttons.add(work1);
        buttons.add(work2);
        buttons.add(work3);
        buttons.add(work4);
        buttons.add(work5);
    }
    //对课程区域赋值
    public void setClass(TextView textView,int day,int SelectWeekTurn,int SelectDayturn){
        ArrayList<SimpleNEUClass> classes1=new ArrayList<>();//当天的课程
        //筛选该周上课的课程
        for(SimpleNEUClass class1:classes){
            ArrayList<Integer> ints=class1.getWeeks();
            for(Integer i:ints){
                if(i==SelectWeekTurn){
                    classes1.add(class1);
                }
            }
        }
        //筛选该日上课的课程
        Todayclasses=new ArrayList<>();
        Todayclasses.clear();
        for(SimpleNEUClass class2:classes1){
            ArrayList<Integer> ints=class2.getDay();
            for(Integer i:ints){
                if(i==( SelectDayturn)){
                    Todayclasses.add(class2);
                }
            }
        }
        for(SimpleNEUClass simpleNEUClass:Todayclasses){
            if(simpleNEUClass.getSections().contains(day)) {
                System.out.println("Weekturn"+SelectWeekTurn);
                if(simpleNEUClass.getWeeks().contains(SelectWeekTurn))
                textView.setText(simpleNEUClass.getName());
                textView.setBackgroundColor(Color.YELLOW);
            }
        }
    }
    //判断选择天数在第几周
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
    //判断选择天数是周几
    public int  chargeWeekDay(int currentDay,int Weekturn){
        int WeekDay=0;
        WeekDay=(currentDay+Weekturn-1)%7;
        return  WeekDay;
    }
    //按钮设置对应任务
    public void setTask(Button button){
        //筛选对应天数拥有的任务
        //目前完成版本，只在开始日显示任务
        ArrayList<Learningtasks> sel=new ArrayList<>();
        ArrayList<Activitytasks> sel1=new ArrayList<>();
        for(Learningtasks learningtasks1:learningtasks){
            int month=learningtasks1.getMouth();
            int day=learningtasks1.getDay();
            if((month==currentMonth)&&(day==TodayNum)&&(learningtasks1.getOver()==0)) {
                sel.add(learningtasks1);
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(learningtasks1.getFinfish());
            int daynum=calendar.get(Calendar.DATE);
            if((daynum==TodayNum)&&(learningtasks1.getOver()==0)) {
                if(day!=daynum){
                    sel.add(learningtasks1);
                }
            }
        }
        for(Activitytasks activitytasks1:activitytasks){
            int month=activitytasks1.getMouth();
            int day=activitytasks1.getDay();
            if((month==currentMonth)&&(day==TodayNum)&&(activitytasks1.getOver()==0)) {
                sel1.add(activitytasks1);
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(activitytasks1.getFinfish());
            int daynum=calendar.get(Calendar.DATE);
            if((daynum==TodayNum)&&(activitytasks1.getOver()==0)) {
                if(day!=daynum){
                    sel1.add(activitytasks1);
                }
            }
        }
       if(taxkTurn<sel.size()){
            String content=sel.get(taxkTurn).getContent();
           System.out.println("添加learn任务"+content);
            button.setText(content);
            button.setVisibility(View.VISIBLE);
            button.setBackgroundColor(Color.rgb(179,229,252));
            taxkTurn++;
            return;
        }
       if((taxkTurn<(sel.size()+sel1.size()))&&(sel1.size()!=0)){
           String content=sel1.get(taxkTurn-sel.size()).getContent();
           System.out.println("添加act任务"+content);
           button.setText(content);
           button.setVisibility(View.VISIBLE);
           button.setBackgroundColor(Color.rgb(239,154,154));
           taxkTurn++;
       }


    }

    public void setAlertDialog(Context context,String name,Button Selectbutton){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(name);
        builder.setMessage(name);
        builder.setIcon(R.mipmap.ic_launcher_round);
        //点击对话框以外的区域是否让对话框消失
        builder.setCancelable(true);
        //设置正面按钮
        builder.setPositiveButton("完成", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "您完成了该任务", Toast.LENGTH_SHORT).show();
                Selectbutton.setVisibility(View.INVISIBLE);
                //对文件进行操作
                delettask(Selectbutton.getText().toString());
                dialog.dismiss();
            }
        });
        //设置反面按钮
        builder.setNegativeButton("修改", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "请稍后", Toast.LENGTH_SHORT).show();
                con.setChangeTaskName(Selectbutton.getText().toString());
                startActivity(new Intent(keyfun.this,ChangeTask.class));
                //进入修改页面
                dialog.dismiss();

            }
        });
        //设置中立按钮
        builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        //对话框显示的监听事件
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
            }
        });
        //对话框消失的监听事件
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
            }
        });
        //显示对话框
        dialog.show();
    }
    public ArrayList addTaskL(ArrayList AL){
        for(int i=0;i<learningtasks.size()-1;i++){
            for(int j=0;j<learningtasks.size()-1-i;j++){
                if(learningtasks.get(j).getValue()>learningtasks.get(j+1).getValue()){
                    Learningtasks learningtasks1=learningtasks.get(j);
                    learningtasks.set(j,learningtasks.get(j+1));
                    learningtasks.set(j+1,learningtasks1);
                }
            }
        }
        for(Learningtasks learningtasks1:learningtasks){
            if(learningtasks1.getOver()==0){
                HashMap map = new HashMap();
                map.put("text",learningtasks1.getContent());
                map.put("content",learningtasks1.getFinfish());
                AL.add(map);
            }
        }
        return AL;
    }
    public ArrayList addTaskA(ArrayList AL){
        for(int i=0;i<activitytasks.size()-1;i++){
            for(int j=0;j<activitytasks.size()-1-i;j++){
                if(activitytasks.get(j).getValue()>activitytasks.get(j+1).getValue()){
                    Activitytasks activitytasks1=activitytasks.get(j);
                    activitytasks.set(j,activitytasks.get(j+1));
                    activitytasks.set(j+1,activitytasks1);
                }
            }
        }
        for(Activitytasks activitytasks1:activitytasks){
            if(activitytasks1.getOver()==0){
                HashMap map = new HashMap();
                map.put("text",activitytasks1.getContent());
                map.put("content",activitytasks1.getFinfish());
                AL.add(map);
            }
        }
        return AL;
    }
    public void delettask(String name){
        int judeg=0;
        int num=0;
        for(Learningtasks learningtasks1:learningtasks){
            if(learningtasks1.getContent().equals(name)){
                judeg=1;
                learningtasks1.setOver(1);
                learningtasks.set(num,learningtasks1);
                con.setLearningtasks(learningtasks);
                try {
                    IOutils.writeFileData(this,"learnTasks.json",learningtasks);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            num++;
        }
        if(judeg==0){
            int num1=0;
            for(Activitytasks activitytasks1:activitytasks){
                if(activitytasks1.getContent().equals(name)){
                    activitytasks1.setOver(1);
                    activitytasks.set(num1,activitytasks1);
                    con.setActivitytasks(activitytasks);
                    try {
                        IOutils.writeFileData(this,"ActivityTask.json",activitytasks);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                num1++;

            }
        }
        con.refresh(this);
        init();
        tc.initView();
        tc.init();
    }

    public void init(){
        Creat=findViewById(R.id.creat);
        preInfo=findViewById(R.id.PerInfo);
        Creat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(keyfun.this,CreatTask.class));
            }
        });
        //点击跳转至个人信息页面
        preInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(keyfun.this, com.example.schedulehomework.PerInfoSetting.preInfo.class));
            }
        });
        initView();
        //隐藏导航栏
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        testcalender tc=findViewById(R.id.testcalender);
        tc.initView();
        tc.setMyOnClickListener(new testcalender.ClickListener() {
            @Override
            public void Click(int num) {
                System.out.println(con.getDaynum());
                turn=con.getTurn();
                if(con.getDaynum()!=0){
                    TodayNum=con.getDaynum();
                    showPopupWindow(con.getDaynum());
                }

            }
        });


        //下班部分任务总体栏
        lv1 = findViewById(R.id.listview1);
        ArrayList AL = new ArrayList();
        AL=addTaskL(AL);
        String[] from = {"text","content"};
        int[] to = {R.id.textView,R.id.content};
        SimpleAdapter adapter = new SimpleAdapter(this, AL,R.layout.listitem,from,to);
        lv1.setAdapter(adapter);

        lv2 = findViewById(R.id.listview2);
        AL = new ArrayList();
        AL=addTaskA(AL);
        adapter = new SimpleAdapter(this, AL,R.layout.listitema,from,to);
        lv2.setAdapter(adapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

}



