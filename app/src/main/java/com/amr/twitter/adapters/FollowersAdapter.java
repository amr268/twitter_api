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
import com.amr.twitter.cells.FollowerViewHolder;
import com.amr.twitter.cells.LoadViewHolder;
import com.amr.twitter.fragments.FollowerDetailsFragment;
import com.amr.twitter.moduls.Follower;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by amr on 01/02/18.
 */

public class FollowersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private ArrayList<Follower> followers;
    private Context context;
    private RecyclerView recyclerView;

    private OnLoadMoreListener mOnLoadMoreListener;
    private FollowerDetailsFragment.OnFragmentInteractionListener openFollowerDetailsFragment;

    private boolean isLoading;
    private boolean mTwoPane;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    public FollowersAdapter(Context context, ArrayList<Follower> followers, RecyclerView recyclerView) {
        this.followers = followers;
        this.context = context;
        this.recyclerView = recyclerView;
        /*final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();

                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (mOnLoadMoreListener != null) {
                        mOnLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });*/
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    public void setOpenFollowerDetailsFragment(FollowerDetailsFragment.OnFragmentInteractionListener openFollowerDetailsFragment) {
        this.openFollowerDetailsFragment = openFollowerDetailsFragment;
    }

    public void setTwoPane(boolean mTwoPane) {
        this.mTwoPane = mTwoPane;
    }

    public FollowersAdapter(Context context) {
        this.context = context;
    }

    public void setFollowers(ArrayList<Follower> followers) {
        this.followers = followers;
    }

    @Override
    public int getItemViewType(int position) {
        return followers.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_follower, parent, false);
            return new FollowerViewHolder(itemView);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FollowerViewHolder) {
            FollowerViewHolder viewHolder = (FollowerViewHolder) holder;
            final Follower follower = followers.get(position);
            Glide.with(viewHolder.imgProfile.getContext()).load(follower.getProfile_image_url()).into(viewHolder.imgProfile);
            viewHolder.tvName.setText(follower.getName());
            viewHolder.tvBio.setText(follower.getDescription());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mTwoPane) {
                        openFollowerDetailsFragment.openFollowerDetailsFragment(follower);
                    } else {
                        Intent intent = new Intent(context, FollowerDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(FollowerDetailsFragment.ITEM_FOLLOWER, follower);
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    }
                }
            });
        } else {
            LoadViewHolder viewHolder = (LoadViewHolder) holder;
            viewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return followers == null ? 0 : followers.size();
    }

    public void setLoaded() {
        isLoading = false;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}
