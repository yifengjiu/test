package com.example.schedulehomework;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.schedulehomework.controller.Controller;
import com.example.schedulehomework.entity.Activitytasks;
import com.example.schedulehomework.entity.Learningtasks;
import com.example.schedulehomework.utils.CalendarUtils;
import com.example.schedulehomework.utils.IOutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CreatTask extends AppCompatActivity {

    private Button beginbutton;
    private Button finishbutton;
    private static Context context;
    private EditText name;
    private EditText value;
    private Button makesure;
    private Button cancel;
    private String taskname;
    private int taskvalue=0;
    private String tasktype;
    private RadioGroup rg;
    ArrayList<RadioButton> rbs=new ArrayList<>();
    private RadioButton rba;
    private RadioButton rbl;
    private int beginMonth;
    private int beginDay;
    private int finishMonth;
    private int finishDay;
    private String beginTasktime;
    private String finishTasktime;
    private String begintimeYMD;
    private String finishtimeYMD;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Date beginDate;
    private Date finishDate;


    private Button begintimebutton;
    private Button finishtimebutton;
    private String Selecttime;//用于方法赋值
    private int Selecttimeturn=0;//辅助计数
    private String Selectbegintime;
    private String Selectfinishtime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_task);
        Controller con=Controller.getController();

        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null)
            actionbar.hide();

        context=this;

        name=findViewById(R.id.name);
        makesure=findViewById(R.id.makesure);
        value=findViewById(R.id.value);
        makesure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskname=name.getText().toString();
                taskvalue=Integer.parseInt(value.getText().toString());
                //日期转换
                beginTasktime=begintimeYMD+Selectbegintime+":00";
                finishTasktime=finishtimeYMD+Selectfinishtime+":00";
                try {
                    beginDate=sdf.parse(beginTasktime);
                    finishDate=sdf.parse(finishTasktime);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //调用函数
                CreatTask(tasktype,taskname,beginMonth,beginDay,beginDate,finishDate,taskvalue);
                finish();
            }
        });

        cancel=findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

        beginbutton=findViewById(R.id.beginbutton);
        beginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                new DatePickerDialog(CreatTask.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String text = year + "-" + (month + 1) + "-" + dayOfMonth+" ";
                        begintimeYMD=text;
                        beginMonth=month+1;
                        beginDay=dayOfMonth;
                        beginbutton.setText(text);
                    }
                }
                        , calendar.get(Calendar.YEAR)
                        , calendar.get(Calendar.MONTH)
                        , calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        finishbutton=findViewById(R.id.finishbutton);
        finishbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                new DatePickerDialog(CreatTask.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String text = year + "-" + (month + 1) + "-" + dayOfMonth+" ";
                        finishtimeYMD=text;
                        finishMonth=month+1;
                        finishDay=dayOfMonth;
                        finishbutton.setText(text);
                    }
                }
                        , calendar.get(Calendar.YEAR)
                        , calendar.get(Calendar.MONTH)
                        , calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        rba=findViewById(R.id.activityType);
        rbl=findViewById(R.id.learnType);
        rbs.add(rba);
        rbs.add(rbl);
        rg=findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for(RadioButton rb:rbs){
                    if(rb.getId()==checkedId){
                        //获取类型
                        tasktype=rb.getText().toString();
                    }
                }

            }
        });

        begintimebutton=findViewById(R.id.begintimebutton);
        begintimebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog();
            }
        });
        finishtimebutton=findViewById(R.id.finishtimebutton);
        finishtimebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog();
            }
        });


    }
    public void CreatTask(String tasktype,String content,int beginmonth,int beginDay,Date begindate,Date finishdate,int taskvalue){
        if(tasktype.equals("learnType")){
            //创建learnType型任务
            Learningtasks learningtasks=new Learningtasks(beginmonth,beginDay,content,begindate,finishdate,taskvalue,0);
            ArrayList<Learningtasks> learningtasks1=new ArrayList<Learningtasks>();
            learningtasks1=findAllLearnTasks();
            learningtasks1.add(learningtasks);
            try {
                IOutils.writeFileData(this,"learnTasks.json",learningtasks1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //对任务建立提醒
            CalendarUtils.addCalendarEventRemind(this,learningtasks.getContent(),learningtasks.getContent(),learningtasks.getFinfish().getTime(),learningtasks.getFinfish().getTime()+1000*600,2, new CalendarUtils.onCalendarRemindListener() {
                @Override
                public void onFailed(Status error_code) {
                    Toast.makeText(CreatTask.this, "提醒建立成功", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess() {

                }
            });


        }else if(tasktype.equals("activityType")){
            //创建activityType型任务
        Activitytasks activitytasks=new Activitytasks(beginmonth,beginDay,content,begindate,finishdate,taskvalue,0);
        ArrayList<Activitytasks> activitytasks1=new ArrayList<>();
        activitytasks1=findAllActivityTasks();
        activitytasks1.add(activitytasks);
            try {
                IOutils.writeFileData(this,"ActivityTask.json",activitytasks1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //建立提醒
            CalendarUtils.addCalendarEventRemind(this,activitytasks.getContent(),activitytasks.getContent(),activitytasks.getFinfish().getTime(),activitytasks.getFinfish().getTime()+1000*600,2, new CalendarUtils.onCalendarRemindListener() {
                @Override
                public void onFailed(Status error_code) {
                    Toast.makeText(CreatTask.this, "提醒建立成功", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess() {

                }
            });

        }else{
            System.out.println("错误");
        }

    }
    //搜寻全部learn任务
    public ArrayList<Learningtasks> findAllLearnTasks(){
        ArrayList<Learningtasks> learningtasks1=new ArrayList<Learningtasks>();
        try {
            learningtasks1= (ArrayList<Learningtasks>) IOutils.readFileData(this,"learnTasks.json",Learningtasks.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  learningtasks1;

    }
    //搜寻全部activity任务
    public ArrayList<Activitytasks> findAllActivityTasks(){
        ArrayList<Activitytasks> activitytasks=new ArrayList<Activitytasks>();
        try {
            activitytasks= (ArrayList<Activitytasks>) IOutils.readFileData(this,"ActivityTask.json",Activitytasks.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(activitytasks);
        return  activitytasks;

    }
    public void timePickerDialog(){
        Selecttime="";
        //获得日历的实列
        Calendar calendar = Calendar.getInstance();

        //设置当前时间
        calendar.setTimeInMillis(System.currentTimeMillis());
        //获取时分
        final int hour = calendar.get(Calendar.HOUR_OF_DAY) ;
        int minute = calendar.get(Calendar.MINUTE);

        //第三、四个参数初始时分 第五个参数是否为24小时显示
        final TimePickerDialog time = new TimePickerDialog(CreatTask.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(CreatTask.this, "Hour" + hourOfDay + "minute" + minute, Toast.LENGTH_SHORT).show();
                send(hourOfDay,minute);
            }
        }, hour, minute, true);
        time.show();

    }
    public void send(int hour,int minute){
        Selecttime=Integer.toString(hour);
        Selecttime=Selecttime+":"+minute;
        if(Selecttimeturn==0){
            Selectbegintime=Selecttime;
            begintimebutton.setText(Selectbegintime);
            Selecttimeturn++;
        }
        else{
            Selectfinishtime=Selecttime;
            finishtimebutton.setText(Selectfinishtime);
            Selecttimeturn--;
        }
    }
}