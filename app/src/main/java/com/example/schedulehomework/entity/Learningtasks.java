package com.example.schedulehomework.entity;

import java.util.Date;

public class Learningtasks {
    public Learningtasks() {
    }
    private int mouth;
    private int day;
    private String content;//任务内容
    private Date begin;//任务开始时间
    private Date finfish;//任务截至时间
    private int value;//任务重要程度
    private int over;//是否完成，0未完成1完成

    @Override
    public String toString() {
        return "Learningtasks{" +
                "mouth=" + mouth +
                ", day=" + day +
                ", content='" + content + '\'' +
                ", begin=" + begin +
                ", finfish=" + finfish +
                ", value=" + value +
                ", over=" + over +
                '}';
    }

    public Learningtasks(int mouth, int day, String content, Date begin, Date finfish, int value, int over) {
        this.mouth = mouth;
        this.day = day;
        this.content = content;
        this.begin = begin;
        this.finfish = finfish;
        this.value = value;
        this.over = over;
    }

    public int getOver() {
        return over;
    }

    public void setOver(int over) {
        this.over = over;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getFinfish() {
        return finfish;
    }

    public void setFinfish(Date finfish) {
        this.finfish = finfish;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMouth() {
        return mouth;
    }

    public void setMouth(int mouth) {
        this.mouth = mouth;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
