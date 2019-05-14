package com.example.a20190422_shilpamahendriker_nycschools.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a20190422_shilpamahendriker_nycschools.R;
import com.example.a20190422_shilpamahendriker_nycschools.model.School;
import com.example.a20190422_shilpamahendriker_nycschools.views.SchoolDetailsActivity;

import java.util.ArrayList;

public class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder> {
    //this context used to inflate the layout
    private Context context;

    //storing all the Locations in a list
    private ArrayList<School> Schools;

    //getting the context and Locations list with constructor
    public SchoolAdapter(Context context, ArrayList<School> Schools) {

        this.context = context;
        this.Schools = Schools;

    }
    @NonNull
    @Override
    public SchoolAdapter.SchoolViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //inflating and returning the view holder

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.recycler_view_inner_layout, viewGroup,false);
        return new SchoolViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolAdapter.SchoolViewHolder schoolViewHolder,  final int i) {
        //getting the location of the specified position
        final School School = Schools.get(i);

        //binding the data with the view holder views
        schoolViewHolder.textSchoolName.setText(School.getSchoolName());
        schoolViewHolder.textSchoolBorough.setText("Borough: " + School.getBorough());




        //Creating on click listener for the item selected on the view holder
        schoolViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                School selection = Schools.get(i);

                // Sending DBN, Location and school name through the intent to school details activity
                Intent intent = new Intent(context, SchoolDetailsActivity.class);
                intent.putExtra("DBN", selection.getDbn());
                intent.putExtra("LOCATION", selection.getLocation());
                intent.putExtra("NAME",selection.getSchoolName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Schools.size();
    }

    class SchoolViewHolder extends RecyclerView.ViewHolder {
        TextView textSchoolName,textSchoolBorough;


        /*constructor that accepts the entire item row and lookups to find each subview*/

        public SchoolViewHolder(View itemView) {
            super(itemView);
            textSchoolName = itemView.findViewById(R.id.txtSchoolName);
            textSchoolBorough = itemView.findViewById(R.id.txtBorough);


        }
    }
}
