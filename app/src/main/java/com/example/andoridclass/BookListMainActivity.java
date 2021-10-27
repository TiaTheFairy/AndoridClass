package com.example.andoridclass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BookListMainActivity extends AppCompatActivity {

    private List<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        initData();

        RecyclerView mainRecycleView = findViewById(R.id.recycle_view_books);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mainRecycleView.setLayoutManager(layoutManager);
        mainRecycleView.setAdapter(new MyRecyclerViewAdapter (books));
    }

    public void initData(){
        books = new ArrayList<Book>();
        books.add(new Book(R.drawable.book_2,getResources().getString(R.string.books_1)));
        books.add(new Book(R.drawable.book_no_name,getResources().getString(R.string.books_2)));
        books.add(new Book(R.drawable.book_1,getResources().getString(R.string.books_3)));
    }

    private class MyRecyclerViewAdapter extends RecyclerView.Adapter{
        private List<Book> books;

        public MyRecyclerViewAdapter(List<Book> books){
            this.books = books;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_holder, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder Holder, int position){
            MyViewHolder holder = (MyViewHolder) Holder;

            holder.getImageView().setImageResource(books.get(position).getPicId());
            holder.getTextView().setText(books.get(position).getName());
        }

        @Override
        public int getItemCount(){
            return books.size();
        }

        private class MyViewHolder extends RecyclerView.ViewHolder{
            private final ImageView imageView;
            private final TextView textView;

            public MyViewHolder(View itemView){
                super(itemView);

                this.imageView = itemView.findViewById(R.id.rch_img);
                this.textView = itemView.findViewById(R.id.rch_name);
            }

            public ImageView getImageView(){
                return imageView;
            }

            public TextView getTextView(){
                return textView;
            }
        }
    }
}
