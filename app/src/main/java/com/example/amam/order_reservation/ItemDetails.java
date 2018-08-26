package com.example.amam.order_reservation;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ItemDetails extends AppCompatActivity {

    TextView item_name, item_price, item_category, item_description;
    ImageView item_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
/*    FloatingActionButton btnCart;*/
    ElegantNumberButton numberButton;
    FloatingActionButton btnCart;

    String ItemID = "";
    FirebaseDatabase database;
    DatabaseReference items;

    Item currentItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        database = FirebaseDatabase.getInstance();
        items = database.getReference("Items");
        btnCart = (FloatingActionButton) findViewById(R.id.btnCart);
        numberButton = (ElegantNumberButton)findViewById(R.id.number_button);
/*        btnCart = (FloatingActionButton)findViewById(R.id.btnCart);*/

        item_description = (TextView)findViewById(R.id.itemDescription);
        item_name = (TextView)findViewById(R.id.itemName);
        item_price = (TextView)findViewById(R.id.itemPrice);
        item_category = (TextView)findViewById(R.id.itemCat);

        item_image = (ImageView)findViewById(R.id.img_item);

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addToCart(new Order(
                    ItemID,
                        currentItem.getName(),
                        numberButton.getNumber(),
                        currentItem.getPrice()

                ));

                Toast.makeText(ItemDetails.this, "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });
        if(getIntent()!=null){
            ItemID = getIntent().getStringExtra("ItemID");
            items.child(ItemID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    currentItem = dataSnapshot.getValue(Item.class);

                    Picasso.with(getBaseContext()).load(currentItem.getImage()).into(item_image);

                    collapsingToolbarLayout.setTitle(currentItem.getName());

                    item_name.setText(currentItem.getName());
                    item_price.setText(currentItem.getPrice());
                    item_category.setText(currentItem.getCategory());
                    item_description.setText(currentItem.getDescription());


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
