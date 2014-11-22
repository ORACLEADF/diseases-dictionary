package com.diseases.dictionary.common;

/**
 * Created by ameysami on 9/8/13.
 */
public class PersianDate {
    private String year;
    private String month;

    public PersianDate(String year, String month) {
        this.year = year;
        this.month = month;
    }

    public String getDate1(){
        return this.year+"/"+this.month+"/"+"01";
    }

    public String getDate2(){
        return this.year+"/"+this.month+"/"+"31";
    }
}
