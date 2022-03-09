package com.example.desafio_mobits_android.view;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;

import com.example.desafio_mobits_android.R;
import com.example.desafio_mobits_android.databinding.ActivityMainBinding;
import com.example.desafio_mobits_android.view.adapter.listenerViewPager;
import com.example.desafio_mobits_android.view.adapter.viewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends FragmentActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        viewPagerAdapter adapter = new viewPagerAdapter(getSupportFragmentManager(), getLifecycle(), this);
//        binding.pager.setAdapter(adapter);

        booksFragment books = new booksFragment();
        housesFragment houses = new housesFragment();

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.title_livros)));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.title_casas)));
        setCurrentFragment(books);
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0) {
                    setCurrentFragment(books);
                }else {
                    setCurrentFragment(houses);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void setCurrentFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
        }
}
//    @Override
//    public void getPositionItem(int position) {
//        TabLayout.Tab tabSelected = binding.tabLayout.getTabAt(position);
//        binding.tabLayout.selectTab(tabSelected);
//    }
