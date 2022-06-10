package com.example.schedulehomework.entity;

public class CalenderBean {
    private int DataNnum;
    private Boolean isHaveClass;

    public Boolean getHaveClass() {
        return isHaveClass;
    }

    public int getDataNnum() {
        return DataNnum;
    }

    public void setDataNnum(int dataNnum) {
        DataNnum = dataNnum;
    }

    public void setHaveClass(Boolean haveClass) {
        isHaveClass = haveClass;
    }
    public CalenderBean(int Datanum){
        this.DataNnum=Datanum;
        this.isHaveClass=false;
    }
}
