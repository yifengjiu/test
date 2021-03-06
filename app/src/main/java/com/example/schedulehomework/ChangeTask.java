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

public class ChangeTask extends AppCompatActivity {
    Controller con=Controller.getController();
    private Button button3;
    private Button button4;
    private Button button5;
    private ArrayList<Learningtasks> learningtasks=new ArrayList<>();
    private ArrayList<Activitytasks> activitytasks=new ArrayList<>();
    private Learningtasks learningtasks1=null;
    private Activitytasks activitytasks1=null;
    private String SelectTaskName;

    private EditText inname;
    private EditText invalue;
    private Button intime;
    private Button intimedetial;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String finishtimeYMD;
    private String Selecttime="";
    private int Day;
    private int judgela=0;


    private Context mycontext=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_task);

        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null)
            actionbar.hide();

        SelectTaskName=con.getChangeTaskName();
        learningtasks=con.getLearningtasks();
        activitytasks=con.getActivitytasks();
        //?????????????????????
        Search();

        button3=findViewById(R.id.Chmakesure);
        button4=findViewById(R.id.Chback);
        button5=findViewById(R.id.CHfail);
        inname=findViewById(R.id.inname);
        invalue=findViewById(R.id.invalue);
        intime=findViewById(R.id.intime);
        intimedetial=findViewById(R.id.intimedetial);
        //??????
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChangeTask.this,keyfun.class));
            }
        });
        //??????
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(judgela==1){
                    int num=0;
                    for(Learningtasks learningtasks2:learningtasks){
                        if(learningtasks2.getContent().equals(SelectTaskName)){
                            learningtasks2.setContent(inname.getText().toString());
                            learningtasks2.setValue(Integer.parseInt(invalue.getText().toString()));
                            System.out.println(finishtimeYMD);
                            System.out.println(Selecttime);
                            if(Selecttime!=""){
                                Selecttime=finishtimeYMD+Selecttime+":00";
                                System.out.println(Selecttime);
                                try {
                                    learningtasks2.setFinfish(sdf.parse(Selecttime));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                            learningtasks.set(num,learningtasks2);
                            System.out.println(learningtasks2.getFinfish());
                            con.setLearningtasks(learningtasks);
                            try {
                                IOutils.writeFileData(mycontext,"learnTasks.json",learningtasks);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            //????????????
                            CalendarUtils.deleteCalendarEventRemind(mycontext, SelectTaskName, SelectTaskName, learningtasks2.getFinfish().getTime(), new CalendarUtils.onCalendarRemindListener() {
                                @Override
                                public void onFailed(Status error_code) {

                                }

                                @Override
                                public void onSuccess() {

                                }
                            });
                            //???????????????
                            CalendarUtils.addCalendarEventRemind(mycontext,inname.getText().toString(),inname.getText().toString(),learningtasks2.getFinfish().getTime(),learningtasks2.getFinfish().getTime()+1000*600,2, new CalendarUtils.onCalendarRemindListener() {
                                @Override
                                public void onFailed(Status error_code) {
                                    Toast.makeText(ChangeTask.this, "??????????????????", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onSuccess() {

                                }
                            });

                        }
                        num++;
                    }
                }else{
                    int num=0;
                    for(Activitytasks activitytasks2:activitytasks){
                        if(activitytasks2.getContent().equals(SelectTaskName)){
                            activitytasks2.setContent(inname.getText().toString());
                            activitytasks2.setValue(Integer.parseInt(invalue.getText().toString()));
                            if(Selecttime!=""){
                                Selecttime=finishtimeYMD+Selecttime+":00";
                                try {
                                    activitytasks2.setFinfish(sdf.parse(Selecttime));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                            activitytasks.set(num,activitytasks2);
                            con.setActivitytasks(activitytasks);
                            try {
                                IOutils.writeFileData(mycontext,"ActivityTask.json",activitytasks);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            //????????????
                            CalendarUtils.deleteCalendarEventRemind(mycontext, SelectTaskName, SelectTaskName, activitytasks2.getFinfish().getTime(), new CalendarUtils.onCalendarRemindListener() {
                                @Override
                                public void onFailed(Status error_code) {

                                }

                                @Override
                                public void onSuccess() {

                                }
                            });
                            //???????????????
                            CalendarUtils.addCalendarEventRemind(mycontext,inname.getText().toString(),inname.getText().toString(), activitytasks2.getFinfish().getTime(), activitytasks2.getFinfish().getTime()+1000*600,2, new CalendarUtils.onCalendarRemindListener() {
                                @Override
                                public void onFailed(Status error_code) {
                                    Toast.makeText(ChangeTask.this, "??????????????????", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onSuccess() {

                                }
                            });

                        }
                        num++;
                    }

                }
                finish();
            }
        });
        //?????????
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //???????????????????????????2
                if(judgela==1){
                    int num=0;
                    for(Learningtasks learningtasks2:learningtasks){
                        if(learningtasks2.getContent().equals(SelectTaskName)){
                            learningtasks2.setOver(2);
                            learningtasks.set(num,learningtasks2);
                            con.setLearningtasks(learningtasks);
                            try {
                                IOutils.writeFileData(mycontext,"learnTasks.json",learningtasks);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            CalendarUtils.deleteCalendarEventRemind(mycontext, SelectTaskName, SelectTaskName, learningtasks2.getFinfish().getTime(), new CalendarUtils.onCalendarRemindListener() {
                                @Override
                                public void onFailed(Status error_code) {

                                }

                                @Override
                                public void onSuccess() {

                                }
                            });
                        }
                        num++;
                    }
                }else{
                    int num=0;
                    for(Activitytasks activitytasks2:activitytasks){
                        if(activitytasks2.getContent().equals(SelectTaskName)){
                            activitytasks2.setOver(2);
                            activitytasks.set(num,activitytasks2);
                            con.setActivitytasks(activitytasks);
                            try {
                                IOutils.writeFileData(mycontext,"ActivityTask.json",activitytasks);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            CalendarUtils.deleteCalendarEventRemind(mycontext, SelectTaskName, SelectTaskName, activitytasks2.getFinfish().getTime(), new CalendarUtils.onCalendarRemindListener() {
                                @Override
                                public void onFailed(Status error_code) {

                                }

                                @Override
                                public void onSuccess() {

                                }
                            });
                        }
                        num++;
                    }

                }
                finish();
            }
        });
        //????????????
        if(learningtasks1!=null){
            inname.setText(learningtasks1.getContent());
            invalue.setText(Integer.toString(learningtasks1.getValue()));
            intime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(learningtasks1.getFinfish()));
            intimedetial.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(learningtasks1.getFinfish()));
        }else{
            inname.setText(activitytasks1.getContent());
            invalue.setText(Integer.toString(activitytasks1.getValue()));
            intime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(activitytasks1.getFinfish()));
            intimedetial.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(activitytasks1.getFinfish()));
        }

        intime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                new DatePickerDialog(ChangeTask.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String text = year + "-" + (month + 1) + "-" + dayOfMonth+" ";
                        Day=dayOfMonth;
                        finishtimeYMD=text;
                        intime.setText(text);
                    }
                }
                        , calendar.get(Calendar.YEAR)
                        , calendar.get(Calendar.MONTH)
                        , calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        intimedetial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog();
                intimedetial.setText(finishtimeYMD);
            }
        });

    }
    public void Search(){
        int judge=0;
        for(Learningtasks learningtasks2:learningtasks){
            if(learningtasks2.getContent().equals(SelectTaskName)){
                learningtasks1=learningtasks2;
                judge=1;
                judgela=1;
            }
        }
        if(judge==0){
            for(Activitytasks activitytasks2:activitytasks){
                if(activitytasks2.getContent().equals(SelectTaskName)){
                    activitytasks1=activitytasks2;
                    judgela=0;
                }
            }
        }
    }

    public void timePickerDialog(){
        Selecttime="";
        //?????????????????????
        Calendar calendar = Calendar.getInstance();
        //??????????????????
        calendar.setTimeInMillis(System.currentTimeMillis());
        //????????????
        final int hour = calendar.get(Calendar.HOUR_OF_DAY) ;
        int minute = calendar.get(Calendar.MINUTE);

        //????????????????????????????????? ????????????????????????24????????????
        final TimePickerDialog time = new TimePickerDialog(ChangeTask.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(ChangeTask.this, "Hour" + hourOfDay + "minute" + minute, Toast.LENGTH_SHORT).show();
                send(hourOfDay,minute);
            }
        }, hour, minute, true);
        time.show();

    }
    public void send(int hour,int minute){
        Selecttime=Integer.toString(hour);
        Selecttime=Selecttime+":"+minute;
        }

}