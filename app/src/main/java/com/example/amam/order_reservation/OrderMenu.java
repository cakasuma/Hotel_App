package com.example.amam.order_reservation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class OrderMenu extends AppCompatActivity {

    CardView foodButton;
    CardView drinkButton;
    CardView historyButton;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_menu);

        foodButton = (CardView)findViewById(R.id.foodbut);
        drinkButton = (CardView)findViewById(R.id.drinkbut);
        historyButton = (CardView)findViewById(R.id.historybut);
        fab = (FloatingActionButton)findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartIntent = new Intent(OrderMenu.this, ShowCart.class);
                startActivity(cartIntent);
            }
        });

        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyintent = new Intent(OrderMenu.this, ShowHistory.class);

                startActivity(historyintent);
            }
        });

        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itemlistintent = new Intent(OrderMenu.this, ItemList.class);
                itemlistintent.putExtra("itemtype", "Foods");
                startActivity(itemlistintent);
            }
        });

        drinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itemlistintent = new Intent(OrderMenu.this, ItemList.class);
                itemlistintent.putExtra("itemtype", "Drinks");
                startActivity(itemlistintent);
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnavmenu);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_restaurant:
                        Intent ordermenuintent = new Intent(OrderMenu.this, OrderMenu.class);
                        startActivity(ordermenuintent);
                        break;
                    case R.id.ic_table_reservation:
                        Intent tablereservationintent = new Intent(OrderMenu.this, TableReservationMenu.class);
                        startActivity(tablereservationintent);
                        break;
                    case R.id.ic_room_service:
                        break;
                    case R.id.ic_hotel_info:
                        break;
                    case R.id.ic_logout:
                        FirebaseAuth.getInstance().signOut();
                        Intent loginintent = new Intent(OrderMenu.this, MainActivity.class);
                        startActivity(loginintent);
                        break;
                }
                return false;
            }
        });
    }
}
