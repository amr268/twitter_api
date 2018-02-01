package com.amr.twitter.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amr.twitter.R;
import com.bumptech.glide.Glide;
import com.twitter.sdk.android.core.models.User;

import java.util.ArrayList;

/**
 * Created by amr on 01/02/18.
 */

public class FollowersAdapter extends RecyclerView.Adapter<FollowerViewHolder> {

    private ArrayList<User> followers;
    private Context context;

    public FollowersAdapter(Context context) {
        this.context = context;
    }

    public void setFollowers(ArrayList<User> followers) {
        this.followers = followers;
    }

    @Override
    public FollowerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_follower, parent, false);
        return new FollowerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FollowerViewHolder holder, int position) {
        User follower = followers.get(position);
        Glide.with(holder.imgProfile.getContext()).load(follower.profileImageUrl).into(holder.imgProfile);
        holder.tvName.setText(follower.name);
        holder.tvBio.setText(follower.description);
    }

    @Override
    public int getItemCount() {
        return followers.size();
    }
}
