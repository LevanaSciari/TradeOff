package com.example.tradeoff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

// Import the required libraries
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;

public class DiagramRegion
        extends AppCompatActivity {


    private DatabaseReference databaseReference;
    private ArrayList<String> ArrRegion = new ArrayList<String>();
    private ArrayList<User> users = new ArrayList<User>();
    double[] count;
    int start =0;
    int end = 4;
    // Create the object of TextView
    // and PieChart class
    TextView r1, r2, r3, r4;
    TextView regio1, regio2, regio3, regio4;
    TextView rg1, rg2, rg3, rg4;
    Button next;
    PieChart pieChart;
    String  userCurrentEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagram_region);
        Bundle extras = getIntent().getExtras();
        userCurrentEmail = extras.getString("email");
        //init data base
        databaseReference = FirebaseDatabase.getInstance().getReference();
        // Link those objects with their
        // respective id's that
        // we have given in .XML file
        r1 = findViewById(R.id.r1);
        r2 = findViewById(R.id.r2);
        r3 = findViewById(R.id.r3);
        r4 = findViewById(R.id.r4);

        regio1 = findViewById(R.id.region1);
        regio2 = findViewById(R.id.region2);
        regio3 = findViewById(R.id.region3);
        regio4 = findViewById(R.id.region4);


        rg1 = findViewById(R.id.rg1);
        rg2 = findViewById(R.id.rg2);
        rg3 = findViewById(R.id.rg3);
        rg4 = findViewById(R.id.rg4);

        next= findViewById(R.id.next);

        pieChart = findViewById(R.id.piechart);

        // Creating a method setData()
        // to set the text in text view and pie chart
        setData();
    }

    private void setData()
    {

        databaseReference.child("Region").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot statis : dataSnapshot.getChildren()) {
                    String region = statis.getValue(String.class);
                    ArrRegion.add(region);
                }
                databaseReference.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot statis : dataSnapshot.getChildren()) {
                            User user = statis.getValue(User.class);
                            users.add(user);
                        }


                        next.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
                                pieChart.clearChart();
                                count=new double[ArrRegion.size()];
                                for(int i=start;i<end&&i<ArrRegion.size();i++) {
                                    String reg = ArrRegion.get(i);
                                    for (User j : users) {
                                        if(reg.equals(j.getRegion())){
                                            count[i]+=1;
                                        }
                                    }
                                }

                                //regio get city from fire base
                                // set to text view
                                //
                                // Set the percentage of language used
                                for(int i=start;i<count.length;i++){
                                    double d= (count[i]/users.size())*100;
                                    int ans=(int)d;
                                    if(i==start){
                                        r1.setText(Integer.toString( ans));
                                        regio1.setText(ArrRegion.get(i));
                                        rg1.setText(ArrRegion.get(i));
                                    }else if(i==start+1){
                                        r2.setText(Integer.toString( ans));
                                        regio2.setText(ArrRegion.get(i));
                                        rg2.setText(ArrRegion.get(i));
                                    }else if(i==start+2){
                                        r3.setText(Integer.toString( ans));
                                        regio3.setText(ArrRegion.get(i));
                                        rg3.setText(ArrRegion.get(i));
                                    }else if(i==start+3){
                                        r4.setText(Integer.toString( ans));
                                        regio4.setText(ArrRegion.get(i));
                                        rg4.setText(ArrRegion.get(i));
                                    }
                                }

                                // Set the data and color to the pie chart
                                pieChart.addPieSlice(
                                        new PieModel(
                                                "Region 1",
                                                Integer.parseInt(r1.getText().toString()),
                                                Color.parseColor("#FFA726")));
                                pieChart.addPieSlice(
                                        new PieModel(
                                                "Region 2",
                                                Integer.parseInt(r2.getText().toString()),
                                                Color.parseColor("#66BB6A")));
                                pieChart.addPieSlice(
                                        new PieModel(
                                                "Region 3",
                                                Integer.parseInt(r3.getText().toString()),
                                                Color.parseColor("#EF5350")));
                                pieChart.addPieSlice(
                                        new PieModel(
                                                "Region 4",
                                                Integer.parseInt(r4.getText().toString()),
                                                Color.parseColor("#29B6F6")));


                                // To animate the pie chart
                                pieChart.startAnimation();
                                start+=4;
                                end+=4;
                            }
                        });
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    public void back(View view) {
        Intent i = new Intent(DiagramRegion.this, Administrator.class);


        i.putExtra("email", userCurrentEmail);
        startActivity(i);

    }
}
