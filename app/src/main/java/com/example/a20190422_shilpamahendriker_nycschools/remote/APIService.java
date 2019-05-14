package com.example.a20190422_shilpamahendriker_nycschools.remote;

import com.example.a20190422_shilpamahendriker_nycschools.model.School;
import com.example.a20190422_shilpamahendriker_nycschools.model.SchoolPerformance;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    String BASE_URL = "https://data.cityofnewyork.us/resource/";

    // endpoint to retrieve arraylist for loading in the recyclerview in main activity
    @GET("97mf-9njv.json?$select=borough,dbn,school_name,location")
    Call<ArrayList<School>> getSchools();

    // end point to retrieve arraylist for loading selected schools sat scores
    // from main activity into school details activity
    @GET("734v-jeq5.json?")
    Call<ArrayList<SchoolPerformance>> getSchoolDetails(@Query("dbn") String dbn);


}
