package com.example.andoridclass.book;

import java.io.Serializable;

public class Book implements Serializable {
    private int picId;
    private String name;

    public Book(int picId, String name){
        this.picId = picId;
        this.name = name;
    }

    public int getPicId(){
        return picId;
    }

    public String getName(){
        return name;
    }

    public void setPicId(int picId){
        this.picId = picId;
    }

    public void setName(String name){
        this.name = name;
    }
}
