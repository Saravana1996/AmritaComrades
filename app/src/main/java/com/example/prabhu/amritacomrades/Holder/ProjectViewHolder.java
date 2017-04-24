package com.example.prabhu.amritacomrades.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.prabhu.amritacomrades.Post;
import com.example.prabhu.amritacomrades.R;

/**
 * Created by PRABHU on 21-04-2017.
 */

public class ProjectViewHolder extends RecyclerView.ViewHolder {

    public TextView titleView;
    public TextView bodyView;
    public TextView domainView;
    public ProjectViewHolder(View itemView) {

        super(itemView);

        titleView = (TextView) itemView.findViewById(R.id.post_title);
        bodyView = (TextView) itemView.findViewById(R.id.post_body);
        domainView = (TextView) itemView.findViewById(R.id.post_domain);
    }

    public void bindToPost(Post post) {
        titleView.setText(post.title);
        bodyView.setText(post.about);
        domainView.setText(post.domain);
    }
}
