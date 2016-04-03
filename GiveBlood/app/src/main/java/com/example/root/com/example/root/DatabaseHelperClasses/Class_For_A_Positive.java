package com.example.root.com.example.root.DatabaseHelperClasses;


public class Class_For_A_Positive {
    public static String name,blood_group,city,address,phone_Number;

    public Class_For_A_Positive(){

    }
    public static void set_Person_Name(String save_Name){
        name=save_Name;
    }

    public static void set_blood_group(String save_blood_group){
        blood_group=save_blood_group;

    }

    public static void set_city(String save_city){
        city=save_city;

    }

    public static void set_address(String save_address){
        address=save_address;

    }

    public static void set_phone_Number(String save_phone_Number){
        phone_Number=save_phone_Number;

    }

    ///////////// Getter //////////////
    public  String get_name(){
        return name;

    }
    public static String get_blood_group(){
        return blood_group;

    }
    public static String get_User_city(){
        return city;

    }
    public static String get_address(){
        return address;

    }
    public static String getphone_Number(){
        return phone_Number;

    }
}
