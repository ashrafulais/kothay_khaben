package com.example.kothaykhaben;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class HomepageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final TextView areaName = (TextView) findViewById(R.id.areaName);
        Button findFood = (Button) findViewById(R.id.findButton);

        findFood.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String tempName = areaName.getText().toString();

                if(tempName.isEmpty()) {
                    Toast.makeText(HomepageActivity.this, "Please type an area name.", Toast.LENGTH_LONG).show();
                } else {
                    tempName = tempName.substring(0,1).toUpperCase() + tempName.substring(1).toLowerCase();

                    //Toast.makeText(HomepageActivity.this, tempName, Toast.LENGTH_LONG).show();

                    Intent intentTour = new Intent(HomepageActivity.this, RestaurantListActivity.class);
                    intentTour.putExtra("PLACE_NAME", tempName);
                    startActivity(intentTour);

                }

            }
        });
    }
}
