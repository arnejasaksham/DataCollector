package com.example.saksham.datacollector;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

public class idViewHolder extends RecyclerView.ViewHolder {

    EditText id;

    public idViewHolder(View view) {
        super(view);
        id = (EditText) view.findViewById(R.id.idOne);
    }
}
