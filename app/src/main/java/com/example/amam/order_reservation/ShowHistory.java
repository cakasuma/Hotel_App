package com.example.amam.order_reservation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShowHistory extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Request, HistoryViewHolder> adapter;

    FirebaseDatabase database;
    DatabaseReference requests;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Order History");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Requests");

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        recyclerView = (RecyclerView)findViewById(R.id.historyrecycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new FirebaseRecyclerAdapter<Request, HistoryViewHolder>(
                Request.class,
                R.layout.card_history,
                HistoryViewHolder.class,
                requests.orderByChild("userID").equalTo(mUser.getUid())
        ) {
            @Override
            protected void populateViewHolder(HistoryViewHolder viewHolder, Request model, int position) {
                viewHolder.txtHistoryId.setText("#"+adapter.getRef(position).getKey());
                viewHolder.txtDateTime.setText(model.getDatetime());
                viewHolder.txtEmail.setText(mUser.getEmail());
                viewHolder.txtTotalPrice.setText(model.getTotal());
                viewHolder.txtRoomNo.setText(model.getRoomNo());
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
