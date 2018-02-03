package com.amr.twitter.cells;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amr.twitter.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by amr on 01/02/18.
 */

public class FollowerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.img_follower_profile)
    public ImageView imgProfile;
    @BindView(R.id.tv_follower_name)
    public TextView tvName;
    @BindView(R.id.tv_follower_bio)
    public TextView tvBio;

    public FollowerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
