package com.example.andoridclass;

public class Book {
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

    public void setName(String name){
        this.name = name;
    }
}
