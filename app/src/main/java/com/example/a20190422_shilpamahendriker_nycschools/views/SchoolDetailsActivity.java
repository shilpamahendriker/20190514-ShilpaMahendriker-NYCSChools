package com.example.a20190422_shilpamahendriker_nycschools.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.a20190422_shilpamahendriker_nycschools.R;
import com.example.a20190422_shilpamahendriker_nycschools.model.School;
import com.example.a20190422_shilpamahendriker_nycschools.model.SchoolPerformance;
import com.example.a20190422_shilpamahendriker_nycschools.viewmodels.SchoolPerformanceViewModel;
import com.example.a20190422_shilpamahendriker_nycschools.viewmodels.SchoolViewModel;

import java.util.ArrayList;

public class SchoolDetailsActivity extends AppCompatActivity {

    TextView textViewSchoolName;
    TextView textViewSchoolLocation;
    TextView textViewSatScoresLbl;
    TextView textViewCriticalReading;
    TextView textViewMathAvg;
    TextView textViewWritingAvg;

    private String location;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_details);

        // Extracting data received through the intent
        Intent intent = getIntent();
        String dbn = intent.getStringExtra("DBN");
        location = intent.getStringExtra("LOCATION");
        name = intent.getStringExtra("NAME");
        Log.v("myLogs", "DBN " + dbn);

        //Defining textviews to display school data and sat scores

        textViewSchoolName = findViewById(R.id.txtSchoolName);
        textViewSchoolLocation = findViewById(R.id.txtSchoolLocation);
        textViewSatScoresLbl = findViewById(R.id.txtSatScoresLabel);
        textViewCriticalReading = findViewById(R.id.txtCriticalReadingAvg);
        textViewMathAvg = findViewById(R.id.txtMathAvg);
        textViewWritingAvg = findViewById(R.id.txtWritingAvg);

        // Calling the refrofit initialization method from the School model and retrieving the data into arraylist

        final SchoolPerformanceViewModel schoolPerformanceViewModel = ViewModelProviders.of(this).get(SchoolPerformanceViewModel.class);
        schoolPerformanceViewModel.getSchoolDetails(dbn).observe(this, new Observer<ArrayList<SchoolPerformance>>() {
            @Override
            public void onChanged(@Nullable ArrayList<SchoolPerformance> schoolDetails) {

                // Assiging appropriate text if the no sat scores are found for the selected school in the main activity

                if (schoolDetails.isEmpty()){

                    textViewSchoolName.setText(name);
                    location = location.split("\\(")[0];
                    textViewSchoolLocation.setText(location);
                    textViewSatScoresLbl.setText(R.string.no_sat_data);
                    textViewCriticalReading.setVisibility(View.GONE);
                    textViewMathAvg.setVisibility(View.GONE);
                    textViewWritingAvg.setVisibility(View.GONE);


                } else {

                    // Assiging correspoding text  to the textviews when sat scores are found for the selected school in the main activity
                    String schoolName = schoolDetails.get(0).getSchoolName();
                    String criticalReadingAvgScore = schoolDetails.get(0).getSatCriticalReadingAvgScore();
                    String satMathAvgScore = schoolDetails.get(0).getSatMathAvgScore();
                    String satWritingAvgScore = schoolDetails.get(0).getSatWritingAvgScore();
                    textViewSchoolName.setText(schoolName);
                    location = location.split("\\(")[0];
                    textViewSchoolLocation.setText(location);
                    textViewSatScoresLbl.setText(R.string.sat_scores);
                    String criticalReadingLabel = getResources().getString(R.string.critical_reading_label);
                    textViewCriticalReading.setText(getResources().getString(R.string.critical_reading_label) + (criticalReadingAvgScore));
                    textViewMathAvg.setText(getResources().getString(R.string.Math_average_label) + (satMathAvgScore));
                    textViewWritingAvg.setText(getResources().getString(R.string.Writing_average) + (satWritingAvgScore));

                }



            }
        });
    }
}
