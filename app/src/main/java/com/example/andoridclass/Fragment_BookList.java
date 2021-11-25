package com.example.andoridclass;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
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

import java.util.List;

public class Fragment_BookList extends Fragment {

    public static final int RESULT_CODE_ADD_DATA = 996;
    private List<Book> books;
    private DataBank dataBank;
    private Fragment_BookList.MyRecyclerViewAdapter recyclerViewAdapter;

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

    public Fragment_BookList(){

    }

    public static Fragment_BookList newInstance(){
        Fragment_BookList fragment = new Fragment_BookList();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(getArguments() != null){

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_view, container, false);

        initData();

        FloatingActionButton fabAdd = rootView.findViewById(R.id.recycle_floatingActionButton);
        fabAdd.setOnClickListener(view ->{
            Intent intent = new Intent(this.getContext(), EditBookActivity.class);
            intent.putExtra("position", books.size());
            launcherAdd.launch(intent);
        });

        RecyclerView mainRecycleView = rootView.findViewById(R.id.recycle_view_books);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        mainRecycleView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new Fragment_BookList.MyRecyclerViewAdapter(books);
        mainRecycleView.setAdapter(recyclerViewAdapter);

        return rootView;
    }

    public void initData(){
        dataBank = new DataBank(this.getContext());
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
                MenuItem menuItemAdd = contextMenu.add(Menu.NONE, LIST_ADD,LIST_ADD, Fragment_BookList.this.getContext().getResources().getString(R.string.recycleview_add)+" "+(position+1));
                MenuItem menuItemEdit = contextMenu.add(Menu.NONE,LIST_EDIT, LIST_EDIT, Fragment_BookList.this.getContext().getResources().getString(R.string.recycleview_edit2)+" "+(position+1));
                MenuItem menuItemDelete = contextMenu.add(Menu.NONE,LIST_DELETE,LIST_DELETE, Fragment_BookList.this.getContext().getResources().getString(R.string.recycleview_delete)+" "+(position+1));

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
                        intent = new Intent(Fragment_BookList.this.getContext() ,EditBookActivity.class);
                        intent.putExtra("position", position);
                        launcherAdd.launch(intent);
                        break;
                    case LIST_EDIT:
                        intent = new Intent(Fragment_BookList.this.getContext() ,EditBookActivity.class);
                        intent.putExtra("position", position);
                        intent.putExtra("name", books.get(position).getName());
                        launcherEdit.launch(intent);
                        break;
                    case LIST_DELETE:
                        AlertDialog.Builder alertDeleteConfirm = new AlertDialog.Builder(Fragment_BookList.this.getContext());
                        alertDeleteConfirm.setPositiveButton(Fragment_BookList.this.getContext().getResources().getString(R.string.recycleview_deleteConfirm), (dialogInterface, i) -> {
                            books.remove(position);
                            dataBank.saveData();
                            Fragment_BookList.MyRecyclerViewAdapter.this.notifyItemRemoved(position);
                        });
                        alertDeleteConfirm.setNegativeButton(Fragment_BookList.this.getContext().getResources().getString(R.string.recycleview_cancel),(dialogInterface, i) -> {

                        });
                        alertDeleteConfirm.setMessage(Fragment_BookList.this.getContext().getResources().getString(R.string.recycleview_deleteMessage));
                        alertDeleteConfirm.setTitle(Fragment_BookList.this.getContext().getResources().getString(R.string.recycleview_deleteTitle)).show();
                        break;
                }
                return false;
            }
        }
    }
}