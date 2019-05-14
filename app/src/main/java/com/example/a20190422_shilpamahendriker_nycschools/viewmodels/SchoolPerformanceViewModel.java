package com.example.a20190422_shilpamahendriker_nycschools.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;


import com.example.a20190422_shilpamahendriker_nycschools.model.SchoolPerformance;
import com.example.a20190422_shilpamahendriker_nycschools.remote.APIService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SchoolPerformanceViewModel extends ViewModel {

    //this is the data to fetch asynchronously
    private MutableLiveData<ArrayList<SchoolPerformance>> schoolDetailsList;

    //Method to get data
    public LiveData<ArrayList<SchoolPerformance>> getSchoolDetails(String dbn) {
        //if the arraylist is null
        if (schoolDetailsList == null) {
            schoolDetailsList = new MutableLiveData<>();
            //load it asynchronously from server in this method
            loadSchoolDetails(dbn);
        }

        //finally return the arraylist
        return schoolDetailsList;
    }


    //This method is using Retrofit to get the JSON data from URL and supply the query string as dbn
    private void loadSchoolDetails(String dbn) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService api = retrofit.create(APIService.class);
        Call<ArrayList<SchoolPerformance>> call = api.getSchoolDetails(dbn);


        call.enqueue(new Callback<ArrayList<SchoolPerformance>>() {
            @Override
            public void onResponse(Call<ArrayList<SchoolPerformance>> call, Response<ArrayList<SchoolPerformance>> response) {

                //finally set the list to  MutableLiveData

                schoolDetailsList.setValue(response.body());

            }

            @Override
            public void onFailure(Call<ArrayList<SchoolPerformance>> call, Throwable t) {

                schoolDetailsList.setValue(null);


            }
        });
    }
}
