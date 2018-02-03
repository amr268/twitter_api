package com.amr.twitter.cells;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.amr.twitter.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Amr on 9/18/2017.
 */

public class LoadViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.pb_load_more)
    public ProgressBar progressBar;

    public LoadViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}