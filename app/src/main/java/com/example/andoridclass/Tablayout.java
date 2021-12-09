package com.example.andoridclass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

public class Tablayout extends AppCompatActivity {

    private ViewPager2 pagerFragment;
    private TabLayout tab_head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);

        pagerFragment = findViewById(R.id.tablayout_pager);
        pagerFragment.setAdapter(new MyFragmentAdapter(this));

        tab_head = findViewById(R.id.tablayout_tab);
        TabLayoutMediator tab_medi = new TabLayoutMediator(tab_head, pagerFragment, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                String[] title = {getResources().getString(R.string.tab_1), getResources().getString(R.string.tab_2), getResources().getString(R.string.tab_3)};
                tab.setText(title[position]);
            }
        });
        tab_medi.attach();
    }

    private class MyFragmentAdapter extends FragmentStateAdapter{
        public MyFragmentAdapter(@NonNull FragmentActivity fragmentActivity){
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position){
            switch (position){
                case 0:
                    return Fragment_BookList.newInstance();
                case 1:
                    return Fragment_Webview.newInstance();
                default:
                    return Fragment_Map.newInstance();
            }
        }

        @Override
        public int getItemCount(){
            return 3;
        }
    }
}