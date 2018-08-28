package io.travelaid.ui;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;

import io.travelaid.R;
import io.travelaid.widget.BaselineGridTextView;

public class SearchActivity extends AppCompatActivity {

    private BaselineGridTextView display_Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            //getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        display_Date = findViewById(R.id.display_date);

    }


    //method for picking date for trip
    public void pickDate(View view) {
        //To show current date in the datePicker
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(SearchActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                //text view to display chosen date
                display_Date.setText(day + "/" + (month + 1) + "/" + year);
            }
        },year,month,dayOfMonth);

        //prevents the user from choosing past dates
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());

        //display the datePicker dialog
        datePickerDialog.show();

     }

     public void searchBus(View view){
        //do nothing
     }


}
