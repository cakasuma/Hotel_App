package com.example.amam.order_reservation;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ItemList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference itemList;

    String itemType = "";

    FirebaseRecyclerAdapter<Item, ItemViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        itemType = getIntent().getStringExtra("itemtype");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(itemType);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        firebaseDatabase = FirebaseDatabase.getInstance();
        itemList = firebaseDatabase.getReference("Items");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_items);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


            adapter = new FirebaseRecyclerAdapter<Item, ItemViewHolder>(Item.class,
                    R.layout.card_item,
                    ItemViewHolder.class,
                    itemList.orderByChild("Type").equalTo(itemType)) {
                @Override
                protected void populateViewHolder(ItemViewHolder viewHolder, Item model, int position) {
                    viewHolder.itemName.setText(model.getName());
                    Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.itemImage);
                    viewHolder.itemPrice.setText("$ "+model.getPrice());
                    viewHolder.itemCategory.setText(model.getCategory());

                    final Item local = model;
                    viewHolder.setItemClickListener(new ItemClickListener() {
                        @Override
                        public void onClick(View view, int position, boolean isLongClick) {
                            Intent itemdetailintent = new Intent(ItemList.this, ItemDetails.class);
                            itemdetailintent.putExtra("ItemID", adapter.getRef(position).getKey());
                            startActivity(itemdetailintent);
                        }
                    });
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
