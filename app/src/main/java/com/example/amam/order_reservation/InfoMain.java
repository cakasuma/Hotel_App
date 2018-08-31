package com.example.amam.order_reservation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class InfoMain extends AppCompatActivity {
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_main);



        cardView = (CardView)findViewById(R.id.cardv);


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itemlistintent = new Intent(InfoMain.this, ImageMain.class);
                startActivity(itemlistintent);
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnavmenu);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_restaurant:
                        Intent ordermenuintent = new Intent(InfoMain.this, OrderMenu.class);
                        startActivity(ordermenuintent);
                        finish();
                        break;
                    case R.id.ic_table_reservation:
                        Intent tablereservationintent = new Intent(InfoMain.this, TableReservationMenu.class);
                        startActivity(tablereservationintent);
                        finish();
                        break;
                    case R.id.ic_room_service:
                        Intent roomservice = new Intent(InfoMain.this, RoomService.class);
                        startActivity(roomservice);
                        finish();
                        break;
                    case R.id.ic_hotel_info:
                        break;
                    case R.id.ic_logout:
                        FirebaseAuth.getInstance().signOut();
                        Intent loginintent = new Intent(InfoMain.this, MainActivity.class);
                        startActivity(loginintent);
                        finish();
                        break;
                }
                return false;
            }
        });
    }
}
