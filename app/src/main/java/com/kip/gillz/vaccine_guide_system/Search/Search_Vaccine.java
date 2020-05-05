package com.kip.gillz.vaccine_guide_system.Search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.kip.gillz.vaccine_guide_system.R;

public class Search_Vaccine extends AppCompatActivity {
    // initializing all variables
    Spinner vspinner;
    String xxx;
    Button btnsearch;
    ArrayAdapter<CharSequence> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__vaccine);
        //casting as elements from the xml file
        vspinner = findViewById(R.id.spinner);
        btnsearch = findViewById(R.id.searchvaccine);

        // fetching all vaccines and dispalting them on a spinner.
        arrayAdapter= ArrayAdapter.createFromResource(Search_Vaccine.this,R.array.people_names, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        vspinner.setAdapter(arrayAdapter);
        vspinner.setOnItemSelectedListener(
            new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(
                    AdapterView<?> parent, View view, int position, long id) {
                    xxx = parent.getItemAtPosition(position).toString(); // returns the corresponding vaccine

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
        // handles the operations when seach vaccine is clicked..
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Search_Vaccine(); //calling tge fuction
            }
        });
    }
        //function to handles the search operation from the database
    public void Search_Vaccine()
    {
        SearchBacktask aaa = new SearchBacktask(this);
        aaa.execute();
    }

}
