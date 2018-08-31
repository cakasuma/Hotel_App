package com.example.amam.order_reservation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.EditText;

public class ServiceReceipt extends AppCompatActivity {

    EditText itemname,ordernum,qauntity,cperson,roomn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_receipt);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Receipt");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ordernum = (EditText)findViewById(R.id.ordernum);
        itemname = (EditText)findViewById(R.id.item);
        qauntity = (EditText)findViewById(R.id.quantity);
        cperson = (EditText)findViewById(R.id.person);
        roomn = (EditText)findViewById(R.id.room);

        String item, person, room, quantity;
        item = person = room = quantity = "";


        ordernum.setText("Order: #" + System.currentTimeMillis());
        ordernum.setEnabled(false);
        ordernum.setGravity(Gravity.CENTER_HORIZONTAL);

        itemname.setText("Item Ordered:                       " + getIntent().getStringExtra("itemsel"));
        itemname.setEnabled(false);

        qauntity.setText("Qauntity Ordered:                   " + getIntent().getStringExtra("quantity"));
        qauntity.setEnabled(false);

        cperson.setText("Contact Person:                       " + getIntent().getStringExtra("person"));
        cperson.setEnabled(false);


        roomn.setText("Room Number:                        " + getIntent().getStringExtra("roomnumber"));
        roomn.setEnabled(false);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
