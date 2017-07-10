package com.acme.a3csci3130;

/**
 * Created by Wenlong on 2017/7/10.
 */

public class Check {
    private static String[] province_list = {"AB", "BC", "MB", "NB", "NL", "NS", "NT", "NU", "ON", "PE", "QC", "SK", "YT"};
    private static String[] primaryB_list ={"Fisher", "Distributor", "Processor", "Fish Monger"};

    public static boolean checkProvince(String province){
        boolean result = false;
        for(int i = 0; i < province_list.length; i++){
            if(province.equals(province_list[i])){
                result = true;
            }
        }
        return result;
    }
    public static boolean checkPrimaryB(String primaryBusiness){
        boolean result = false;
        for(int i = 0; i < primaryB_list.length; i++){
            if(primaryBusiness.equals(primaryB_list[i])){
                result = true;
            }
        }
        return result;
    }
    public static boolean checkBussinessNum(String businessNum){
        boolean result = false;
        if(businessNum.length()==9){
            result = true;
        }
        return result;
    }
    public static boolean checkName(String name){
        boolean result = false;
        if(name.length()>=2 && name.length()<=48){
            result = true;
        }
        return result;
    }
    public static boolean checkAddress(String address){
        boolean result = false;
        if(address.length() < 50){
            result = true;
        }
        return result;
    }
}
