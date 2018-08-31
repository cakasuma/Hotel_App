package com.example.amam.order_reservation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

public class RoomService extends AppCompatActivity {

    ElegantNumberButton txtQuantityTable;
    MaterialEditText txtContactTable, txtRoomNoTable;
    Button butAddTable;
    Spinner service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_service);

        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"Pillow", "Blanket", "Soap"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


        txtQuantityTable = (ElegantNumberButton) findViewById(R.id.txtTableQuantity);
        txtContactTable = (MaterialEditText) findViewById(R.id.txtTableContact);
        txtRoomNoTable = (MaterialEditText) findViewById(R.id.txtTableRoomNo);
        butAddTable = (Button)findViewById(R.id.buttonAddTable);
        service = (Spinner)findViewById(R.id.spinner1);

        butAddTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!validateForm()){
                    return;
                }
                Log.w("Alert", "Here###");
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(RoomService.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(RoomService.this);
                }
                builder.setTitle("Service Order")
                        .setMessage("Are you sure you want add this order?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(RoomService.this, ServiceReceipt.class);
                                intent.putExtra("itemsel", service.getSelectedItem().toString());
                                intent.putExtra("quantity", txtQuantityTable.getNumber().toString());
                                intent.putExtra("person", txtContactTable.getText().toString());
                                intent.putExtra("roomnumber", txtRoomNoTable.getText().toString());
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_menu_add)
                        .show();
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnavmenu);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_restaurant:
                        Intent ordermenuintent = new Intent(RoomService.this, OrderMenu.class);
                        startActivity(ordermenuintent);
                        finish();
                        break;
                    case R.id.ic_table_reservation:
                        Intent tablereservationintent = new Intent(RoomService.this, TableReservationMenu.class);
                        startActivity(tablereservationintent);
                        finish();
                        break;
                    case R.id.ic_room_service:
                        break;
                    case R.id.ic_hotel_info:
                        Intent hotelinfo = new Intent(RoomService.this, InfoMain.class);
                        startActivity(hotelinfo);
                        finish();
                        break;
                    case R.id.ic_logout:
                        FirebaseAuth.getInstance().signOut();
                        Intent loginintent = new Intent(RoomService.this, MainActivity.class);
                        startActivity(loginintent);
                        finish();
                        break;
                }
                return false;
            }
        });

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
