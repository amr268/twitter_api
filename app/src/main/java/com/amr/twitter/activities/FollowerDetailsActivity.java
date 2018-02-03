package com.amr.twitter.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.amr.twitter.R;
import com.amr.twitter.fragments.FollowerDetailsFragment;
import com.amr.twitter.moduls.Follower;

public class FollowerDetailsActivity extends AppCompatActivity {

    private Follower follower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follower_details);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            follower = (Follower) bundle.getSerializable(FollowerDetailsFragment.ITEM_FOLLOWER);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_follower_details, FollowerDetailsFragment.newInstance(follower))
                    .commit();
        }
    }
}
