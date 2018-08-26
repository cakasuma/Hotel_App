package com.example.amam.order_reservation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by amam on 23/8/2018.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView itemName;
    public ImageView itemImage;
    public TextView itemPrice;
    public TextView itemCategory;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public ItemViewHolder(View itemView) {
        super(itemView);

        itemName = (TextView)itemView.findViewById(R.id.item_name);
        itemImage = (ImageView) itemView.findViewById(R.id.item_image);
        itemPrice = (TextView)itemView.findViewById(R.id.item_price);
        itemCategory = (TextView)itemView.findViewById(R.id.item_cat);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }
}
