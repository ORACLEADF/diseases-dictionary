package com.diseases.dictionary.common;

/**
 * Created by meysami on 9/13/13.
 */
public class InsertCommas {
    public String insertCommas(String str) {
        if(str == null)
            return "0";
        else if (str.length() < 4) {
            return str;
        }
        return insertCommas(str.substring(0, str.length() - 3)) + "," + str.substring(str.length() - 3, str.length());
    }
}
