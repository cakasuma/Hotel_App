package com.example.amam.order_reservation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class TableReservationMenu extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;
    FloatingActionButton fabTable;

    FirebaseRecyclerAdapter<Table, TableViewHolder> adapter;

    FirebaseDatabase database;
    DatabaseReference tables;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_reservation_menu);

        database = FirebaseDatabase.getInstance();
        tables = database.getReference("Tables");

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        fabTable = (FloatingActionButton)findViewById(R.id.fabtable);

        fabTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addtableintent = new Intent(TableReservationMenu.this, AddTableReservation.class);
                startActivity(addtableintent);
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.tablerecycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Query queries = tables.orderByChild("userId").equalTo(mUser.getUid());
        queries.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {


                }
                else{
                    TextView txtmessage = (TextView)findViewById(R.id.tablemessage);
                    txtmessage.setVisibility(View.VISIBLE);
                    Toast.makeText(TableReservationMenu.this,"Click + button to add table booking",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        adapter = new FirebaseRecyclerAdapter<Table, TableViewHolder>(
                Table.class,
                R.layout.card_table,
                TableViewHolder.class,
                tables.orderByChild("userId").equalTo(mUser.getUid())
        ) {
            @Override
            protected void populateViewHolder(TableViewHolder viewHolder, Table model, int position) {
                viewHolder.txtTableId.setText("#"+adapter.getRef(position).getKey());
                viewHolder.txtTableDate.setText(model.getDateReserve());
                viewHolder.txtTableTime.setText(model.getTimeReserve());
                viewHolder.txtTableMail.setText(mUser.getEmail());
                viewHolder.txtTableQuantity.setText(model.getGuestQuantity());
                viewHolder.txtTableContactPerson.setText(model.getContactPerson());
                viewHolder.txtTableRoomNo.setText(model.getRoomNo());
            }
        };
        recyclerView.setAdapter(adapter);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnavmenu);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_restaurant:
                        Intent ordermenuintent = new Intent(TableReservationMenu.this, OrderMenu.class);
                        startActivity(ordermenuintent);
                        break;
                    case R.id.ic_table_reservation:
                        Intent tablereservationintent = new Intent(TableReservationMenu.this, TableReservationMenu.class);
                        startActivity(tablereservationintent);
                        break;
                    case R.id.ic_room_service:
                        break;
                    case R.id.ic_hotel_info:
                        break;
                    case R.id.ic_logout:
                        FirebaseAuth.getInstance().signOut();
                        Intent loginintent = new Intent(TableReservationMenu.this, MainActivity.class);
                        startActivity(loginintent);
                        break;
                }
                return false;
            }
        });
    }
}
