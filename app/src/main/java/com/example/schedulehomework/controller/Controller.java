package com.example.schedulehomework.controller;

import android.content.Context;

import com.example.schedulehomework.entity.Activitytasks;
import com.example.schedulehomework.entity.Learningtasks;
import com.example.schedulehomework.entity.SimpleNEUClass;
import com.example.schedulehomework.utils.IOutils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Controller {
    private Controller() {};
    private static Controller con=null;
    //静态工厂方法
    public static Controller getController() {
        if (con == null) {
            con = new Controller();
        }
        return con;
    }
    private int turn=0;
    private int Daynum=0;
    private ArrayList<SimpleNEUClass> classes=new ArrayList<>();
    private ArrayList<Learningtasks>  learningtasks=new ArrayList<>();
    private ArrayList<Activitytasks> activitytasks=new ArrayList<>();

    public ArrayList<Activitytasks> getActivitytasks() {
        return activitytasks;
    }

    public void setActivitytasks(ArrayList<Activitytasks> activitytasks) {
        this.activitytasks = activitytasks;
    }

    //今日日期属性
    private int Dayturn;//今天是周几
    private int TodayWeekturn;//第几教学周

    private int currentMonth;
    private int currentDay;
    private int FirstWeekTurn;//本月第一天是周几

    public String getChangeTaskName() {
        return ChangeTaskName;
    }

    public void setChangeTaskName(String changeTaskName) {
        ChangeTaskName = changeTaskName;
    }

    private String ChangeTaskName;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private Context context;

    public int getFirstWeekTurn() {
        return FirstWeekTurn;
    }

    public void setFirstWeekTurn(int firstWeekTurn) {
        FirstWeekTurn = firstWeekTurn;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(int currentDay) {
        this.currentDay = currentDay;
    }

    public int getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(int currentMonth) {
        this.currentMonth = currentMonth;
    }

    public int getDayturn() {
        return Dayturn;
    }

    public void setDayturn(int dayturn) {
        Dayturn = dayturn;
    }

    public int getTodayWeekturn() {
        return TodayWeekturn;
    }

    public void setTodayWeekturn(int weekturn) {
        TodayWeekturn = weekturn;
    }

    public ArrayList<Learningtasks> getLearningtasks() {
        return learningtasks;
    }

    public void setLearningtasks(ArrayList<Learningtasks> learningtasks) {
        this.learningtasks = learningtasks;
    }

    public ArrayList<SimpleNEUClass> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<SimpleNEUClass> classes) {
        this.classes = classes;
    }

    public int getDaynum() {
        return Daynum;
    }

    public void setDaynum(int daynum) {
        Daynum = daynum;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void refresh(Context context){
        try {
            activitytasks= (ArrayList<Activitytasks>) IOutils.readFileData(context,"ActivityTask.json",Activitytasks.class);
            learningtasks= (ArrayList<Learningtasks>) IOutils.readFileData(context,"learnTasks.json",Learningtasks.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
