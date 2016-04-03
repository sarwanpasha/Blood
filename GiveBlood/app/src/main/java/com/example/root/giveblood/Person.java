package com.example.root.giveblood;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {

    String name;
    String age,city;
    int photoId;
    private boolean selected;

    public Person(String name, String age, int photoId,String city, boolean selected) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.photoId = photoId;
        this.selected = selected;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCity() {
        return city;
    }


    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.age);
        dest.writeInt(this.photoId);
        dest.writeString(this.city);
        dest.writeByte(selected ? (byte) 1 : (byte) 0);
    }

    private Person(Parcel in) {
        this.name = in.readString();
        this.age = in.readString();
        this.photoId = in.readInt();
        this.city = in.readString();
        this.selected = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
