package com.frain.androidproject;

public class ContactData
{
    String id;
    String name;
    String number;
    
    public void setId(String idValue){
        id = idValue;
    }
    public void setContactName(String contactName){
        name = contactName;
    }
    public void setNumber(String phoneNumber){
        number = phoneNumber;
    }
    
    public String getId(){
        return id;
    }
    public String getContactName(){
        return name;
    }
    public String getNumber(){
        return number;
    }
}