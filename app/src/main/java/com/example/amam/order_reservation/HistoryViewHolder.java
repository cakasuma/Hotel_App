package com.example.amam.order_reservation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by amam on 26/8/2018.
 */

public class HistoryViewHolder extends RecyclerView.ViewHolder{

    public TextView txtHistoryId, txtDateTime, txtEmail, txtTotalPrice, txtRoomNo;



    public HistoryViewHolder(View itemView) {
        super(itemView);

        txtHistoryId = (TextView)itemView.findViewById(R.id.history_id);
        txtDateTime = (TextView)itemView.findViewById(R.id.history_datetime);
        txtEmail = (TextView)itemView.findViewById(R.id.history_usermail);
        txtTotalPrice = (TextView)itemView.findViewById(R.id.history_totalprice);
        txtRoomNo = (TextView)itemView.findViewById(R.id.history_roomno);


    }

}
