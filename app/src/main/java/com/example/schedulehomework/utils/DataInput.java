package com.example.schedulehomework.utils;

import com.example.schedulehomework.entity.Learningtasks;
import com.example.schedulehomework.entity.SimpleNEUClass;
import com.example.schedulehomework.entity.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DataInput {
    //默认同一次课上的节数相同，不同则取第一个

    public static  ArrayList<SimpleNEUClass> classInput(){
        ArrayList<SimpleNEUClass> classes=new ArrayList<>();

        //第一课
        SimpleNEUClass simpleNEUClass=new SimpleNEUClass();
        simpleNEUClass.setName("操作系统");
        ArrayList<Integer> ints=new ArrayList<Integer>();
        ints.add(1);
        ints.add(3);
        simpleNEUClass.setDay(ints);
        simpleNEUClass.setTeacher("石凯");
        ints=new ArrayList<Integer>();
        ints.add(1);
        ints.add(2);
        simpleNEUClass.setSections(ints);
        simpleNEUClass.setPosition("1号A201");
        ints=new ArrayList<Integer>();
        ints.add(9);
        ints.add(10);
        ints.add(11);
        ints.add(12);
        ints.add(13);
        ints.add(14);
        ints.add(15);
        ints.add(16);
        simpleNEUClass.setWeeks(ints);
        classes.add(simpleNEUClass);

        //第二课
        simpleNEUClass=new SimpleNEUClass();
        simpleNEUClass.setName("数据库概论");
        ints=new ArrayList<Integer>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        simpleNEUClass.setDay(ints);
        simpleNEUClass.setTeacher("刘莹");
        ints=new ArrayList<Integer>();
        ints.add(3);
        ints.add(4);
        simpleNEUClass.setSections(ints);
        simpleNEUClass.setPosition("1号A402");
        ints=new ArrayList<Integer>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(4);
        ints.add(5);
        ints.add(6);
        ints.add(7);
        ints.add(8);
        simpleNEUClass.setWeeks(ints);
        classes.add(simpleNEUClass);


        //第三课
        simpleNEUClass=new SimpleNEUClass();
        simpleNEUClass.setName("软件构造与测试");
        ints=new ArrayList<Integer>();
        ints.add(1);
        ints.add(3);
        ints.add(5);
        simpleNEUClass.setDay(ints);
        simpleNEUClass.setTeacher("吴辰铌");
        ints=new ArrayList<Integer>();
        ints.add(3);
        ints.add(4);
        simpleNEUClass.setSections(ints);
        simpleNEUClass.setPosition("1号A304");
        ints=new ArrayList<Integer>();
        ints.add(9);
        ints.add(10);
        ints.add(11);
        ints.add(12);
        ints.add(13);
        ints.add(14);
        ints.add(15);
        ints.add(16);
        simpleNEUClass.setWeeks(ints);
        classes.add(simpleNEUClass);


        //第四课
        simpleNEUClass=new SimpleNEUClass();
        simpleNEUClass.setName("计算机网络");
        ints=new ArrayList<Integer>();
        ints.add(1);
        ints.add(3);
        simpleNEUClass.setDay(ints);
        simpleNEUClass.setTeacher("李昕");
        ints=new ArrayList<Integer>();
        ints.add(3);
        ints.add(4);
        simpleNEUClass.setSections(ints);
        simpleNEUClass.setPosition("1号A302");
        ints=new ArrayList<Integer>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(4);
        ints.add(5);
        ints.add(6);
        ints.add(7);
        ints.add(8);
        simpleNEUClass.setWeeks(ints);
        classes.add(simpleNEUClass);


        //第5课
        simpleNEUClass=new SimpleNEUClass();
        simpleNEUClass.setName("软件工程经济与软件生态");
        ints=new ArrayList<Integer>();
        ints.add(4);
        simpleNEUClass.setDay(ints);
        simpleNEUClass.setTeacher("于海");
        ints=new ArrayList<Integer>();
        ints.add(3);
        ints.add(4);
        simpleNEUClass.setSections(ints);
        simpleNEUClass.setPosition("1号A304");
        ints=new ArrayList<Integer>();
        ints.add(10);
        ints.add(11);
        ints.add(12);
        ints.add(13);
        ints.add(14);
        ints.add(15);
        ints.add(16);
        ints.add(17);
        simpleNEUClass.setWeeks(ints);
        classes.add(simpleNEUClass);

        //第6课
        simpleNEUClass=new SimpleNEUClass();
        simpleNEUClass.setName("体育（四）");
        ints=new ArrayList<Integer>();
        ints.add(5);
        simpleNEUClass.setDay(ints);
        simpleNEUClass.setTeacher("王兴华");
        ints=new ArrayList<Integer>();
        ints.add(5);
        ints.add(6);
        simpleNEUClass.setSections(ints);
        simpleNEUClass.setPosition("");
        ints=new ArrayList<Integer>();
        ints.add(3);
        ints.add(4);
        ints.add(5);
        ints.add(6);
        ints.add(7);
        ints.add(8);
        ints.add(10);
        ints.add(11);
        ints.add(12);
        ints.add(13);
        ints.add(14);
        ints.add(15);
        simpleNEUClass.setWeeks(ints);
        classes.add(simpleNEUClass);


        //第7课
        simpleNEUClass=new SimpleNEUClass();
        simpleNEUClass.setName("思想政治理论实践课");
        ints=new ArrayList<Integer>();
        ints.add(3);
        simpleNEUClass.setDay(ints);
        simpleNEUClass.setTeacher("王喆");
        ints=new ArrayList<Integer>();
        ints.add(9);
        ints.add(10);
        simpleNEUClass.setSections(ints);
        simpleNEUClass.setPosition("建筑A302");
        ints=new ArrayList<Integer>();
        ints.add(5);
        ints.add(6);
        ints.add(7);
        ints.add(8);
        simpleNEUClass.setWeeks(ints);
        classes.add(simpleNEUClass);


        //第8课
        simpleNEUClass=new SimpleNEUClass();
        simpleNEUClass.setName("软件创新方法与实例");
        ints=new ArrayList<Integer>();
        ints.add(5);
        ints.add(7);
        simpleNEUClass.setDay(ints);
        simpleNEUClass.setTeacher("宋杰");
        ints=new ArrayList<Integer>();
        ints.add(7);
        ints.add(8);
        simpleNEUClass.setSections(ints);
        simpleNEUClass.setPosition("信息A101");
        ints=new ArrayList<Integer>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(4);
        ints.add(5);
        ints.add(6);
        ints.add(7);
        ints.add(8);
        ints.add(10);
        ints.add(11);
        ints.add(12);
        simpleNEUClass.setWeeks(ints);
        classes.add(simpleNEUClass);


        //第9课
        simpleNEUClass=new SimpleNEUClass();
        simpleNEUClass.setName("摄影艺术赏析与创作");
        ints=new ArrayList<Integer>();
        ints.add(7);
        simpleNEUClass.setDay(ints);
        simpleNEUClass.setTeacher("周淼");
        ints=new ArrayList<Integer>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(4);
        simpleNEUClass.setSections(ints);
        simpleNEUClass.setPosition("文管A112");
        ints=new ArrayList<Integer>();
        ints.add(7);
        ints.add(8);
        ints.add(10);
        ints.add(11);
        ints.add(12);
        ints.add(13);
        ints.add(14);
        ints.add(16);
        simpleNEUClass.setWeeks(ints);
        classes.add(simpleNEUClass);


        //第10课
        simpleNEUClass=new SimpleNEUClass();
        simpleNEUClass.setName("Android开发技术");
        ints=new ArrayList<Integer>();
        ints.add(6);
        simpleNEUClass.setDay(ints);
        simpleNEUClass.setTeacher("于鲲鹏");
        ints=new ArrayList<Integer>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(4);
        simpleNEUClass.setSections(ints);
        simpleNEUClass.setPosition("信息A101");
        ints=new ArrayList<Integer>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(4);
        ints.add(6);
        simpleNEUClass.setWeeks(ints);
        classes.add(simpleNEUClass);


        //第10课
        simpleNEUClass=new SimpleNEUClass();
        simpleNEUClass.setName("Web开发技术");
        ints=new ArrayList<Integer>();
        ints.add(6);
        ints.add(7);
        simpleNEUClass.setDay(ints);
        simpleNEUClass.setTeacher("于鲲鹏");
        ints=new ArrayList<Integer>();
        ints.add(9);
        ints.add(10);
        ints.add(11);
        ints.add(12);
        simpleNEUClass.setSections(ints);
        simpleNEUClass.setPosition("信息A101");
        ints=new ArrayList<Integer>();
        ints.add(11);
        ints.add(12);
        ints.add(13);
        ints.add(14);
        ints.add(16);
        ints.add(17);
        simpleNEUClass.setWeeks(ints);
        classes.add(simpleNEUClass);

        //第11课
        simpleNEUClass=new SimpleNEUClass();
        simpleNEUClass.setName("数值分析");
        ints=new ArrayList<Integer>();
        ints.add(2);
        ints.add(4);
        simpleNEUClass.setDay(ints);
        simpleNEUClass.setTeacher("史大涛");
        ints=new ArrayList<Integer>();
        ints.add(3);
        ints.add(4);
        simpleNEUClass.setSections(ints);
        simpleNEUClass.setPosition("信息A101");
        ints=new ArrayList<Integer>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(4);
        ints.add(6);
        ints.add(7);
        ints.add(8);
        ints.add(9);
        ints.add(10);
        ints.add(11);
        ints.add(12);

        simpleNEUClass.setWeeks(ints);
        classes.add(simpleNEUClass);



        return classes;

    }
    public static ArrayList<Learningtasks> learningtasksInput(){
        ArrayList<Learningtasks> learningtasks=new ArrayList<>();
        Learningtasks learningtasks1=new Learningtasks();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        learningtasks1.setMouth(6);
        learningtasks1.setDay(2);
        String begin="2022-6-2 17:12:16";
        String finish="2022-6-6 17:12:16";
        try {
            learningtasks1.setBegin(sdf.parse(begin));
            learningtasks1.setFinfish(sdf.parse(finish));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        learningtasks1.setValue(0);
        String content="软件开发实践";
        learningtasks1.setContent(content);
        learningtasks1.setOver(0);
        learningtasks.add(learningtasks1);



        learningtasks1=new Learningtasks();
        learningtasks1.setMouth(6);
        learningtasks1.setDay(2);
        begin="2022-6-2 17:12:16";
        finish="2022-6-10 17:12:16";
        try {
            learningtasks1.setBegin(sdf.parse(begin));
            learningtasks1.setFinfish(sdf.parse(finish));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        learningtasks1.setValue(0);
        content="安卓";
        learningtasks1.setContent(content);
        learningtasks1.setOver(0);
        learningtasks.add(learningtasks1);


        learningtasks1=new Learningtasks();
        learningtasks1.setMouth(6);
        learningtasks1.setDay(3);
        begin="2022-6-2 17:12:16";
        finish="2022-6-12 17:12:16";
        try {
            learningtasks1.setBegin(sdf.parse(begin));
            learningtasks1.setFinfish(sdf.parse(finish));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        learningtasks1.setValue(0);
        content="web";
        learningtasks1.setContent(content);
        learningtasks1.setOver(0);
        learningtasks.add(learningtasks1);


        learningtasks1=new Learningtasks();
        learningtasks1.setMouth(6);
        learningtasks1.setDay(3);
        begin="2022-6-2 17:12:16";
        finish="2022-6-15 17:12:16";
        try {
            learningtasks1.setBegin(sdf.parse(begin));
            learningtasks1.setFinfish(sdf.parse(finish));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        learningtasks1.setValue(0);
        content="操作系统";
        learningtasks1.setContent(content);
        learningtasks1.setOver(0);
        learningtasks.add(learningtasks1);


        return learningtasks;
    }
    public static ArrayList<User> userInput(){
        ArrayList<User> users=new ArrayList<>();
        User user=new User("沂风",111111,"cat.png");
        users.add(user);
        return users;
    }
}
