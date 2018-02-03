package com.amr.twitter.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amr.twitter.R;
import com.amr.twitter.activities.FollowerDetailsActivity;
import com.amr.twitter.fragments.FollowerDetailsFragment;
import com.amr.twitter.moduls.Follower;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by amr on 01/02/18.
 */

public class FollowersAdapter extends RecyclerView.Adapter<FollowerViewHolder> {

    private ArrayList<Follower> followers;
    private Context context;

    public FollowersAdapter(Context context) {
        this.context = context;
    }

    public void setFollowers(ArrayList<Follower> followers) {
        this.followers = followers;
    }

    @Override
    public FollowerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_follower, parent, false);
        return new FollowerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FollowerViewHolder holder, int position) {
        final Follower follower = followers.get(position);
        Glide.with(holder.imgProfile.getContext()).load(follower.getProfile_image_url()).into(holder.imgProfile);
        holder.tvName.setText(follower.getName());
        holder.tvBio.setText(follower.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(context, FollowerDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(FollowerDetailsFragment.ITEM_FOLLOWER, follower);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return followers.size();
    }
}
