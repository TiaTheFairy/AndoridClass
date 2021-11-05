package com.example.andoridclass.book;

import android.content.Context;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataBank {
    public static final String DATA_FILE_NAME = "data";
    private final Context context;
    List<Book> bookshelf;

    public DataBank(Context context) {
        this.context=context;
    }

    @SuppressWarnings("unchecked")
    public List<Book> loadData() {
        bookshelf = new ArrayList<>();
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(context.openFileInput(DATA_FILE_NAME));
            bookshelf = (ArrayList<Book>) objectInputStream.readObject();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return bookshelf;
    }

    public void saveData() {
        ObjectOutputStream objectOutputStream = null;
        try{
            objectOutputStream = new ObjectOutputStream(context.openFileOutput(DATA_FILE_NAME, Context.MODE_PRIVATE));
            objectOutputStream.writeObject(bookshelf);
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}