package com.example.schedulehomework;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.schedulehomework.controller.Controller;
import com.example.schedulehomework.entity.Activitytasks;
import com.example.schedulehomework.entity.Learningtasks;
import com.example.schedulehomework.entity.SimpleNEUClass;
import com.example.schedulehomework.entity.User;
import com.example.schedulehomework.utils.DataInput;
import com.example.schedulehomework.utils.IOutils;

import java.util.ArrayList;

public class HelloView extends AppCompatActivity {
    ProgressBar pb;
    Controller con=Controller.getController();

    final int MY_PERMISSIONS_REQUEST_CALENDAR = 0;
    private static String[] PERMISSSION_CALENDAR ={
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.WRITE_CALENDAR
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_v_iew);
        pb = findViewById(R.id.progressBar);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null)
            actionbar.hide();
        //输出初始化
        initView();
        new UpatTask().execute();

        //请求权限
        requestPermission();


    }
    public void initView(){
        //基本数据传递
        //dataprepare();
        //classprepare();
        //LearnTasksprepare();
        //userinfoPre();
        //activityDataChange();
       //clear();
        ArrayList<SimpleNEUClass> classes=new ArrayList<>();
        ArrayList<Learningtasks> learningtasks=new ArrayList<>();
        ArrayList<Activitytasks> activitytasks=new ArrayList<>();
        try {
            classes= (ArrayList<SimpleNEUClass>) IOutils.readFileData(this,"text.json",SimpleNEUClass.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            learningtasks=(ArrayList<Learningtasks>) IOutils.readFileData(this,"learnTasks.json",Learningtasks.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            activitytasks= (ArrayList<Activitytasks>) IOutils.readFileData(this,"ActivityTask.json",Activitytasks.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        con.setActivitytasks(activitytasks);
        con.setClasses(classes);
        con.setLearningtasks(learningtasks);
    }
    public void dataprepare(){
        ArrayList<Learningtasks> learningtasks=new ArrayList<>();
        for(int i=0;i<30;i++){
            i++;
            Learningtasks learningtasks1=new Learningtasks();
            learningtasks1.setDay(i);
            learningtasks1.setMouth(5);
            learningtasks.add(learningtasks1);
        }
        try {
            IOutils.writeFileData(this,"learningTasks.json",learningtasks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void classprepare(){
        System.out.println("开始调用");
        ArrayList<SimpleNEUClass> classes=new ArrayList<>();
        classes= DataInput.classInput();
        try {
            IOutils.writeFileData(this,"text.json",classes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //传递学习任务数据
    public void LearnTasksprepare(){
        ArrayList<Learningtasks> learningtasks=new ArrayList<>();
        learningtasks=DataInput.learningtasksInput();
        try {
            IOutils.writeFileData(this,"learnTasks.json",learningtasks);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //输入个人信息
    public void userinfoPre(){
        ArrayList<User> users=new ArrayList<>();
        users=DataInput.userInput();
        try {
            IOutils.writeFileData(this,"user.json",users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void requestPermission() {
        int permission = ActivityCompat.checkSelfPermission(this, "android.permission.READ_CALENDAR");
        int permissionl = ActivityCompat.checkSelfPermission(this, "android.permission.WRITE_CALENDAR");

        if ((permission != PackageManager.PERMISSION_GRANTED) || (permissionl != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, PERMISSSION_CALENDAR, MY_PERMISSIONS_REQUEST_CALENDAR);
        }

    }

    public void activityDataChange(){
        ArrayList<Activitytasks> activitytasks=new ArrayList<>();
        try {
            activitytasks= (ArrayList<Activitytasks>) IOutils.readFileData(this,"ActivityTask.json",Activitytasks.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(Activitytasks activitytasks1:activitytasks){
            activitytasks1.setOver(1);
        }
        try {
            IOutils.writeFileData(this,"ActivityTask.json",activitytasks);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void clear(){
        ArrayList<Activitytasks> activitytasks=new ArrayList<>();
        ArrayList<Learningtasks> learningtasks=new ArrayList<>();
        try {
            IOutils.writeFileData(this,"ActivityTask.json",activitytasks);
            IOutils.writeFileData(this,"learnTasks.json",learningtasks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    class UpatTask extends AsyncTask {
        //       预处理
        protected  void onPreExrcute(){
            super.onPreExecute();
        }
        // 完成任务后的操作
        @Override
//        主体任务
        protected Object doInBackground(Object[] objects) {
            for(int i=0;i<100;i++){
                try {
                    Thread.sleep(10);
                    if(pb.getProgress()>=100)
                        break;
                    pb.setProgress(pb.getProgress()+1);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            //跳过api页面进入日历主进程
            //startActivity(new Intent(HelloView.this,MainActivity.class));
            startActivity(new Intent(HelloView.this,keyfun.class));
            finish();
            return null;
        }
    }
}