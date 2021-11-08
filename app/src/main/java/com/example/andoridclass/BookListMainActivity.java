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

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andoridclass.book.Book;
import com.example.andoridclass.book.DataBank;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class BookListMainActivity extends AppCompatActivity {

    public static final int RESULT_CODE_ADD_DATA = 996;
    private List<Book> books;
    private DataBank dataBank;
    private MyRecyclerViewAdapter recyclerViewAdapter;

    ActivityResultLauncher<Intent> launcherAdd = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            Intent data = result.getData();
            int resultCode = result.getResultCode();
            if(resultCode == RESULT_CODE_ADD_DATA) {
                if (null == data) return;
                String name = data.getStringExtra("name");
                int position = data.getIntExtra("position", books.size());

                books.add(position, new Book(R.drawable.book_no_name, name));
                dataBank.saveData();
                recyclerViewAdapter.notifyItemInserted(position);
            }
        }
    });

    ActivityResultLauncher<Intent>  launcherEdit = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            Intent data = result.getData();
            int resultCode = result.getResultCode();
            if(resultCode == RESULT_CODE_ADD_DATA) {
                if(null == data) return;
                String name = data.getStringExtra("name");
                int position = data.getIntExtra("position", books.size());

                books.get(position).setName(name);
                dataBank.saveData();
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
        mainRecycleView.setAdapter(recyclerViewAdapter);
    }

    public void initData(){
        dataBank = new DataBank(BookListMainActivity.this);
        books = dataBank.loadData();
        /*
        books = new ArrayList<Book>();
        books.add(new Book(R.drawable.book_2,getResources().getString(R.string.books_1)));
        books.add(new Book(R.drawable.book_no_name,getResources().getString(R.string.books_2)));
        books.add(new Book(R.drawable.book_1,getResources().getString(R.string.books_3)));*/
    }

    private class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>{
        private List<Book> books;

        public MyRecyclerViewAdapter(List<Book> books){
            this.books = books;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_holder, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.MyViewHolder holder, int position){
            holder.getImageView().setImageResource(books.get(position).getPicId());
            holder.getTextView().setText(books.get(position).getName());
        }

        @Override
        public int getItemCount(){
            return books.size();
        }

        private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
            public static final int LIST_ADD = 1;
            public static final int LIST_EDIT = 2;
            public static final int LIST_DELETE = 3;

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
                MenuItem menuItemAdd = contextMenu.add(Menu.NONE, LIST_ADD,LIST_ADD,BookListMainActivity.this.getString(R.string.recycleview_add)+" "+(position+1));
                MenuItem menuItemEdit = contextMenu.add(Menu.NONE,LIST_EDIT, LIST_EDIT,BookListMainActivity.this.getResources().getString(R.string.recycleview_edit2)+" "+(position+1));
                MenuItem menuItemDelete = contextMenu.add(Menu.NONE,LIST_DELETE,LIST_DELETE,BookListMainActivity.this.getResources().getString(R.string.recycleview_delete)+" "+(position+1));

                menuItemAdd.setOnMenuItemClickListener(this);
                menuItemEdit.setOnMenuItemClickListener(this);
                menuItemDelete.setOnMenuItemClickListener(this);
            }

            @Override
            public boolean onMenuItemClick(MenuItem menuItem){
                int position = getAdapterPosition();
                Intent intent;
                switch(menuItem.getItemId()){
                    case LIST_ADD:
                        intent = new Intent(BookListMainActivity.this,EditBookActivity.class);
                        intent.putExtra("position", position);
                        launcherAdd.launch(intent);
                        break;
                    case LIST_EDIT:
                        intent = new Intent(BookListMainActivity.this,EditBookActivity.class);
                        intent.putExtra("position", position);
                        intent.putExtra("name", books.get(position).getName());
                        launcherEdit.launch(intent);
                        break;
                    case LIST_DELETE:
                        AlertDialog.Builder alertDeleteConfirm = new AlertDialog.Builder(BookListMainActivity.this);
                        alertDeleteConfirm.setPositiveButton(BookListMainActivity.this.getResources().getString(R.string.recycleview_deleteConfirm), (dialogInterface, i) -> {
                            books.remove(position);
                            dataBank.saveData();
                            MyRecyclerViewAdapter.this.notifyItemRemoved(position);
                        });
                        alertDeleteConfirm.setNegativeButton(BookListMainActivity.this.getResources().getString(R.string.recycleview_cancel),(dialogInterface, i) -> {

                        });
                        alertDeleteConfirm.setMessage(BookListMainActivity.this.getResources().getString(R.string.recycleview_deleteMessage));
                        alertDeleteConfirm.setTitle(BookListMainActivity.this.getResources().getString(R.string.recycleview_deleteTitle)).show();
                        break;
                }
                return false;
            }
        }
    }
}