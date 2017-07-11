package com.acme.a3csci3130;

/* This class is a check class, which contains several check method. For example, checkProvince() will check whether
*   the parameter is on the province list or not.
*   @author Wenlong Wu
*   @since 1.0
* */
public class Check {
    /*
    * attribute province_list: contains provinces in Canada
    * attribute primaryB_list: contains several businesses
    * */
    private static String[] province_list = {"AB", "BC", "MB", "NB", "NL", "NS", "NT", "NU", "ON", "PE", "QC", "SK", "YT"};
    private static String[] primaryB_list ={"Fisher", "Distributor", "Processor", "Fish Monger"};

    /*
    * This returns the result of whether the parameter in the province list or not
    * @parameter province: test it whether in list or not
    * @return result of whether the parameter in the province list or not
    * */
    public static boolean checkProvince(String province){
        boolean result = false;
        for(int i = 0; i < province_list.length; i++){
            if(province.equals(province_list[i])){
                result = true;
            }
        }
        return result;
    }
    /*
    * This returns the result of whether the parameter in the primary business list or not
    * @parameter primaryBusiness: test it whether in list or not
    * @return result of whether the parameter in the primary business list or not
    * */
    public static boolean checkPrimaryB(String primaryBusiness){
        boolean result = false;
        for(int i = 0; i < primaryB_list.length; i++){
            if(primaryBusiness.equals(primaryB_list[i])){
                result = true;
            }
        }
        return result;
    }
    /*
    * This returns the result of whether the length of parameter is 9 digits or not
    * @parameter businessNum: test it whether 9 digits or not
    * @return result of whether the length of parameter is 9 digits or not
    * */
    public static boolean checkBussinessNum(String businessNum){
        boolean result = false;
        if(businessNum.length()==9){
            result = true;
        }
        return result;
    }
    /*
   * This returns the result of whether name is greater than 2 characters and less than 48 characters
   * @parameter name
   * @return result of whether name is greater than 2 characters and less than 48 characters
   * */
    public static boolean checkName(String name){
        boolean result = false;
        if(name.length()>=2 && name.length()<=48){
            result = true;
        }
        return result;
    }
    /*
  * This returns the result of whether address is less than 50 characters
  * @parameter address
  * @return result of whether address is less than 50 characters
  * */
    public static boolean checkAddress(String address){
        boolean result = false;
        if(address.length() < 50){
            result = true;
        }
        return result;
    }
}
