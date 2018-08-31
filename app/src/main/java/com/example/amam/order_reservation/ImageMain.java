package com.example.amam.order_reservation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class ImageMain extends AppCompatActivity {


    GalleryAdapter mAdapter;
    RecyclerView mRecyclerView;

    ArrayList<ImageModel> data = new ArrayList<>();

    public static String IMGS[] = {
            "https://images.unsplash.com/photo-1499407225606-41ab9e4903d1?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=9b02e71a482466bc44a9385f675b7348&auto=format&fit=crop&w=300&q=300",
            "https://images.unsplash.com/photo-1496417263034-38ec4f0b665a?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=42f8d3c9b192ce7064bf7c3f3064613c&auto=format&fit=crop&w=300&q=300",
            "https://images.unsplash.com/photo-1488345979593-09db0f85545f?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=0f9da20aa63c1d45837cba56451e27db&auto=format&fit=crop&w=300&q=300",
            "https://images.unsplash.com/photo-1503003378590-66f89f543bc6?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=b3be7bfbc9c5252bea9654eb9a9b6641&auto=format&fit=crop&w=300&q=300",
            "https://images.unsplash.com/photo-1440558953273-969c107f78a4?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=5eaa0f30a1c37f38a4a365bd0c7d54ea&auto=format&fit=crop&w=300&q=300",
            "https://images.unsplash.com/photo-1437651025703-2858c944e3eb?dpr=2&fit=crop&auto=format&fit=crop&w=300&q=300",
            "https://images.unsplash.com/photo-1431538510849-b719825bf08b?dpr=2&fit=crop&auto=format&fit=crop&w=300&q=300",
            "https://images.unsplash.com/photo-1446944987594-eb9bb99c6e22?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=d8cc23e498ab0997e45abd6ac20ea70e&auto=format&fit=crop&w=300&q=300",
            "https://images.unsplash.com/photo-1439396087961-98bc12c21176?dpr=2&fit=crop&auto=format&fit=crop&w=300&q=300",
            "https://images.unsplash.com/photo-1433616174899-f847df236857?dpr=2&fit=crop&auto=format&fit=crop&w=300&q=300",
            "https://images.unsplash.com/photo-1438480478735-3234e63615bb?dpr=2&fit=crop&auto=format&fit=crop&w=300&q=300",
            "https://images.unsplash.com/photo-1438027316524-6078d503224b?dpr=2&fit=crop&auto=format&fit=crop&w=300&q=300"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_main);


        for (int i = 0; i < IMGS.length; i++) {

            ImageModel imageModel = new ImageModel();
            imageModel.setName("Image " + i);
            imageModel.setUrl(IMGS[i]);
            data.add(imageModel);

        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setHasFixedSize(true);


        mAdapter = new GalleryAdapter(ImageMain.this, data);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
                new RecyclerItemClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {

                        Intent intent = new Intent(ImageMain.this, DetailActivity.class);
                        intent.putParcelableArrayListExtra("data", data);
                        intent.putExtra("pos", position);
                        startActivity(intent);

                    }
                }));

    }

}
