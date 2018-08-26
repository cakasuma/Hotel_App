package com.example.amam.order_reservation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by amam on 27/8/2018.
 */

public class TableViewHolder extends RecyclerView.ViewHolder {

    public TextView txtTableId, txtTableDate, txtTableTime, txtTableMail, txtTableQuantity, txtTableContactPerson, txtTableRoomNo;

    public TableViewHolder(View itemView) {
        super(itemView);

        txtTableId = (TextView)itemView.findViewById(R.id.table_id);
        txtTableDate = (TextView)itemView.findViewById(R.id.table_date);
        txtTableTime = (TextView)itemView.findViewById(R.id.table_time);
        txtTableMail = (TextView)itemView.findViewById(R.id.table_mail);
        txtTableQuantity = (TextView)itemView.findViewById(R.id.table_total_guest);
        txtTableContactPerson = (TextView)itemView.findViewById(R.id.table_contact_person);
        txtTableRoomNo = (TextView)itemView.findViewById(R.id.table_room_no);
    }
}
