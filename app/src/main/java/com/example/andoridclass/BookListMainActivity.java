package com.example.andoridclass;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class BookListMainActivity extends AppCompatActivity {

    private List<Book> books;
    private MyRecyclerViewAdapter recyclerViewAdapter;
    public static final int RESULT_CODE_ADD_DATA = 996;

    ActivityResultLauncher<Intent> launcherAdd = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            Intent data = result.getData();
            int resultCode = result.getResultCode();
            if(resultCode== RESULT_CODE_ADD_DATA) {
                if (null == data) return;
                String name = data.getStringExtra("name");
                int position = data.getIntExtra("position", books.size());
                books.add(position, new Book(R.drawable.book_no_name, name));
                recyclerViewAdapter.notifyItemInserted(position);
            }
        }
    });

    ActivityResultLauncher<Intent>  launcherEdit = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            Intent data = result.getData();
            int resultCode = result.getResultCode();
            if(resultCode== RESULT_CODE_ADD_DATA) {
                if(null==data) return;
                String name = data.getStringExtra("name");
                int position = data.getIntExtra("position", books.size());
                books.get(position).setName(name);
                recyclerViewAdapter.notifyItemChanged(position);
            }
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        initData();

        FloatingActionButton fabAdd = findViewById(R.id.recycle_floatingActionButton);
        fabAdd.setOnClickListener(View ->{
            Intent intent = new Intent(BookListMainActivity.this, EditBookActivity.class);
            intent.putExtra("position", books.size());
            launcherAdd.launch(intent);
        });

        RecyclerView mainRecycleView = findViewById(R.id.recycle_view_books);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mainRecycleView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new MyRecyclerViewAdapter(books);
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

        private class MyViewHolder
                extends RecyclerView.ViewHolder
                implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
            private final ImageView imageView;
            private final TextView textView;

            public MyViewHolder(View itemView){
                super(itemView);

                this.imageView = itemView.findViewById(R.id.rch_img);
                this.textView = itemView.findViewById(R.id.rch_name);

                itemView.setOnCreateContextMenuListener(this);
            }

            public ImageView getImageView(){
                return imageView;
            }

            public TextView getTextView(){
                return textView;
            }

            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo){
                int position = getAdapterPosition();
                MenuItem menuItemAdd = contextMenu.add(Menu.NONE, 1,1,getResources().getString(R.string.recycleview_add)+" "+ (position+1));
                MenuItem menuItemEdit = contextMenu.add(Menu.NONE,2,2,getResources().getString(R.string.recycleview_edit2)+ " " + (position+1));
                MenuItem menuItemDelete = contextMenu.add(Menu.NONE,3,3,getResources().getString(R.string.recycleview_delete)+ " " + (position+1));

                menuItemAdd.setOnMenuItemClickListener(this);
                menuItemEdit.setOnMenuItemClickListener(this);
                menuItemDelete.setOnMenuItemClickListener(this);
            }

            @Override
            public boolean onMenuItemClick(MenuItem menuItem){
                int position = getAdapterPosition();
                Intent intent;
                switch(menuItem.getItemId()){
                    case 1:
                        intent = new Intent(BookListMainActivity.this,EditBookActivity.class);
                        intent.putExtra("position", position);
                        launcherAdd.launch(intent);
                        break;

                    case 2:
                        intent = new Intent(BookListMainActivity.this,EditBookActivity.class);
                        intent.putExtra("position", position);
                        intent.putExtra("name", books.get(position).getName());
                        launcherEdit.launch(intent);
                        break;
                    /*
                    case 1:
                        View rc_add = LayoutInflater.from(BookListMainActivity.this).inflate(R.layout.rc_edit,null);
                        AlertDialog.Builder alertDialogBuilderAdd = new AlertDialog.Builder(BookListMainActivity.this);
                        alertDialogBuilderAdd.setView(rc_add);

                        alertDialogBuilderAdd.setPositiveButton(getResources().getString(R.string.recycleview_save), new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i){
                                EditText et = rc_add.findViewById(R.id.rce_et);
                                books.add(position, new Book(R.drawable.book_no_name, et.getText().toString()));
                                MyRecyclerViewAdapter.this.notifyItemInserted(position);
                            }
                        });
                        alertDialogBuilderAdd.setCancelable(false).setNegativeButton(getResources().getString(R.string.recycleview_cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        alertDialogBuilderAdd.create().show();
                        break;
                    case 2:
                        View rc_edit = LayoutInflater.from(BookListMainActivity.this).inflate(R.layout.rc_edit,null);
                        AlertDialog.Builder alertDialogBuilderEdit = new AlertDialog.Builder(BookListMainActivity.this);
                        alertDialogBuilderEdit.setView(rc_edit);

                        alertDialogBuilderEdit.setPositiveButton(getResources().getString(R.string.recycleview_save), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                EditText et = rc_edit.findViewById(R.id.rce_et);
                                books.get(position).setName(et.getText().toString());
                                MyRecyclerViewAdapter.this.notifyItemChanged(position);
                            }
                        });
                        alertDialogBuilderEdit.setCancelable(false).setNegativeButton(getResources().getString(R.string.recycleview_cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        alertDialogBuilderEdit.create().show();
                        break;
                    */
                    case 3:
                        books.remove(position);
                        MyRecyclerViewAdapter.this.notifyItemRemoved(position);
                        break;
                }
                return false;
            }
        }
    }
}

/*
//有两类数据，分别命名为item1和item2
public enum itemType{
    item1, item2
}

//onCreateViewHolder中，使用if语句判断数据属于哪一种，根据数据类型的不同创建不同的ViewHolder
@Override
public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if(viewType == itemType.item1.ordinal()){
        return new RecyclerViewHolder1(mInflater.inflate(R.layout.rv_item1, parent, false));
    }
    else if(viewType == itemType.item2.ordinal()){
        return new RecyclerViewHolder1(mInflater.inflate(R.layout.rv_item2, parent, false))
    }
    return null;
}

//onBindViewHolder中，使用if语句判断上一个函数创建的holder属于哪一种，根据holder的不同绑定holder
@Override
public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    String text = titles[position];
    int id = resId[position];

    if (holder instanceof RecyclerViewHolder1) {
        ((rch_1) holder).imageView.setImageResource(books.get(position).getPicId());
        ((rch_1) holder).textView.setText(books.get(position).getName());
    } else if (holder instanceof RecyclerViewHolder2) {
        ((rch_2) holder).imageView.setImageResource(books.get(position).getPicId());
        ((rch_2) holder).textView.setText(books.get(position).getName());
    }
}

//getItemViewType，获取元素的类型，根据元素的位置来确定元素属于哪一种，这个函数使用的判断方法是对
//偶数序号的元素，归为第一类，使用holder1
//奇数序号的元素，归为第二类，使用holder2
@Override
public int getItemViewType(int position) {
    int num = position % 2;
    if (num == 0) {
        return itemType.item1.ordinal();
    }
    else {
        return itemType.item2.ordinal();
    }
}

//在此之外，还需要编写两个xml文件作为holder；还需要编写两个holder的函数
// public static class RecyclerViewHolder1 extends RecyclerView.ViewHolder
// public static class RecyclerViewHolder2 extends RecyclerView.ViewHolder

 */