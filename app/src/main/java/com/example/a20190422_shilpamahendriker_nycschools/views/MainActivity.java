package com.example.a20190422_shilpamahendriker_nycschools.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.a20190422_shilpamahendriker_nycschools.R;
import com.example.a20190422_shilpamahendriker_nycschools.adapter.SchoolAdapter;
import com.example.a20190422_shilpamahendriker_nycschools.model.School;
import com.example.a20190422_shilpamahendriker_nycschools.viewmodels.SchoolViewModel;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SchoolAdapter adapter;

    private final ArrayList<School>  schoolList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        setupRecyclerView();


    // Calling the refrofit initialization method from the School model and retrieving the data into arraylist
        final SchoolViewModel schoolViewModel = ViewModelProviders.of(this).get(SchoolViewModel.class);
        schoolViewModel.getSchools().observe(this, new Observer<ArrayList<School>>() {
            @Override
            public void onChanged(@Nullable ArrayList<School> schools) {
                schoolList.addAll(schools);
                adapter.notifyDataSetChanged();

            }
        });


    }


    // Method to setup recycler view wit the help of School Adapter

    private void setupRecyclerView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager
                (this, LinearLayoutManager.VERTICAL, false);
        if (adapter == null) {
            adapter = new SchoolAdapter(this, schoolList);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
        } else {
            adapter.notifyDataSetChanged();
        }
    }


    }










