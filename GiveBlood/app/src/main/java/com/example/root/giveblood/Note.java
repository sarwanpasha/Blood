package com.example.root.giveblood;
public class Note {

//    private String getGet_Donor_name;
//    private String title;
//    private String content;
//    private String city;
//    private String cnic;
    private String objectid;
    public String get_Donor_name,get_blood_group,get_phone_Number,get_city,get_Home_address;


    Note(String noteget_Donor_name, String noteget_blood_group, String noteget_phone_Number,String noteget_city,
         String noteget_Home_address,String noteObjectid) {
        get_Donor_name = noteget_Donor_name;
        get_blood_group = noteget_blood_group;
        get_phone_Number = noteget_phone_Number;
        get_city = noteget_city;
        get_Home_address = noteget_Home_address;
        objectid=noteObjectid;
    }

    public String getId() {
        return get_Donor_name;
    }
    public void setId(String id) {
        this.get_Donor_name = id;
    }
    public String getTitle() {
        return get_blood_group;
    }
    public void setTitle(String title) {
        this.get_blood_group = title;
    }
    public String getContent() {
        return get_phone_Number;
    }
    public void setContent(String content) {
        this.get_phone_Number = content;
    }


    public String getCity() {
        return get_city;
    }
    public void setCity(String City) {
        this.get_city = City;
    }


    public String getCnic() {
        return get_Home_address;
    }
    public void setCnic(String CNIC) {
        this.get_Home_address = CNIC;
    }

    public String getObjectid() {
        return objectid;
    }
    public void setObjectid(String ObjectId) {
        this.objectid = ObjectId;
    }

    @Override
    public String toString() {
        return this.getTitle();
    }

}
