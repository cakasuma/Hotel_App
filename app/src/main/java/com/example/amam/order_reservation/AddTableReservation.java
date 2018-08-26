package com.example.amam.order_reservation;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class AddTableReservation extends AppCompatActivity {

    DatePicker txtDateTable;
    TimePicker txtTimeTable;
    ElegantNumberButton txtQuantityTable;
    MaterialEditText txtContactTable, txtRoomNoTable;
    Button butAddTable;

    FirebaseDatabase database;
    DatabaseReference tables;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_table_reservation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Table Reservation");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        database = FirebaseDatabase.getInstance();
        tables = database.getReference("Tables");

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        txtDateTable = (DatePicker) findViewById(R.id.txtTableDate);
        txtTimeTable = (TimePicker) findViewById(R.id.txtTableTime);
        txtTimeTable.setIs24HourView(true);
        txtQuantityTable = (ElegantNumberButton) findViewById(R.id.txtTableQuantity);
        txtContactTable = (MaterialEditText) findViewById(R.id.txtTableContact);
        txtRoomNoTable = (MaterialEditText) findViewById(R.id.txtTableRoomNo);
        butAddTable = (Button)findViewById(R.id.buttonAddTable);

        butAddTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!validateForm()){
                    return;
                }

                String DateTable = txtDateTable.getDayOfMonth()+"/"+
                        (txtDateTable.getMonth() + 1)+
                        "/"+txtDateTable.getYear();


                int hour, minute;
                String am_pm;
                if (Build.VERSION.SDK_INT >= 23 ){
                    hour = txtTimeTable.getHour();
                    minute = txtTimeTable.getMinute();

                }
                else{
                    hour = txtTimeTable.getCurrentHour();
                    minute = txtTimeTable.getCurrentMinute();
                }
                if(hour > 12) {
                    am_pm = "PM";
                    hour = hour - 12;
                }
                else
                {
                    am_pm="AM";
                }


                String TimeTable = hour +":"+ minute+" "+am_pm;

                String QuantityTable = txtQuantityTable.getNumber();

                String ContactTable = txtContactTable.getText().toString();

                String RoomNoTable = txtRoomNoTable.getText().toString();

                Table table = new Table(mUser.getUid(),DateTable,TimeTable,QuantityTable,ContactTable,RoomNoTable);
                tables.push().setValue(table);
                Toast.makeText(AddTableReservation.this, "Booking has been made, show the receipt to the receptionist", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private boolean validateForm() {
        boolean valid = true;

        String contact = txtContactTable.getText().toString();
        if (TextUtils.isEmpty(contact)) {
            txtContactTable.setError("Required.");
            valid = false;
        } else {
            txtContactTable.setError(null);
        }

        String roomno = txtRoomNoTable.getText().toString();
        if (TextUtils.isEmpty(roomno)) {
            txtRoomNoTable.setError("Required.");
            valid = false;
        } else {
            txtRoomNoTable.setError(null);

        }

        return valid;
    }
}
