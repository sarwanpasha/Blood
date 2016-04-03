package com.example.root.giveblood;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class User {

    @JsonField(name="slNo")
    public Integer slNo;

    @JsonField(name="name")
    public String name;

    @JsonField(name="contactNo")
    public String contactNo;
}
