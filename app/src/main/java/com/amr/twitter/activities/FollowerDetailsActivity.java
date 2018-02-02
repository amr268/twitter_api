package com.amr.twitter.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.amr.twitter.R;
import com.amr.twitter.fragments.FollowerDetailsFragment;
import com.twitter.sdk.android.core.models.User;

public class FollowerDetailsActivity extends AppCompatActivity {

    private User follower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follower_details);

        //if ()

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.item_detail_container, FollowerDetailsFragment.newInstance(follower))
                    .commit();
        }
    }
}
