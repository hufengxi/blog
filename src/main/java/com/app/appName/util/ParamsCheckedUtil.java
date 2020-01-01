package com.app.appName.util;


public class ParamsCheckedUtil {

    public static boolean checkedStringLength(String params, int length){
        return params.length() < length;
    }
}
