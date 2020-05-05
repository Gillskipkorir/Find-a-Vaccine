package com.kip.gillz.vaccine_guide_system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.balysv.materialripple.MaterialRippleLayout;
import com.kip.gillz.vaccine_guide_system.Search.Search_Vaccine;

public class MainActivity extends AppCompatActivity {
    MaterialRippleLayout search,vaccinedet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = findViewById(R.id.searchh);
        vaccinedet=findViewById(R.id.vdet);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Search_Vaccine.class);
                startActivity(intent);

            }
        });

        vaccinedet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Vaccine_Details.class);
                startActivity(intent);

            }
        });
    }



}
