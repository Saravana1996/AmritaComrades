package com.example.prabhu.amritacomrades.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.prabhu.amritacomrades.In;
import com.example.prabhu.amritacomrades.R;

/**
 * Created by PRABHU on 21-04-2017.
 */

public class InboxViewHolder extends RecyclerView.ViewHolder {

    public TextView nameView;
    public TextView cgpaView;
    public TextView technicalView;
    public TextView titleView;
    public TextView departmentView;
    public Button acceptbutton;
    public Button declinebutton;
    public TextView requestingView;
    public InboxViewHolder(View itemView) {

        super(itemView);
        titleView = (TextView) itemView.findViewById(R.id.post_title1);
        cgpaView = (TextView) itemView.findViewById(R.id.post_cgp1);
        technicalView = (TextView) itemView.findViewById(R.id.post_techni1);
        nameView = (TextView) itemView.findViewById(R.id.post_name1);
        departmentView = (TextView) itemView.findViewById(R.id.post_dep1);
        requestingView = (TextView) itemView.findViewById(R.id.post_requestfor1);
    }

    public void bindtoPost(In in) {
        titleView.setText(in.title);
        nameView.setText(in.name);
        cgpaView.setText(in.cgpa);
        technicalView.setText(in.technical);
        departmentView.setText(in.department);
        requestingView.setText("Requesting for - "+in.requesting);
    }
}