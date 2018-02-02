package com.amr.twitter.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.amr.twitter.R;
import com.amr.twitter.adapters.FollowersAdapter;
import com.amr.twitter.moduls.ResponseData;
import com.amr.twitter.twitter_api.MyTwitterApiClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.rv_followers)
    RecyclerView rvFollowers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Twitter.initialize(this);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        final TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        getUserDetails(session);
    }

    private void getUserDetails(final TwitterSession twitterSession) {
        TwitterCore.getInstance().getApiClient(twitterSession).getAccountService().verifyCredentials(true, true, true).enqueue(new Callback<User>() {
            @Override
            public void success(Result<User> userResult) {
                try {
                    User user = userResult.data;
                    String fullName = user.name;
                    long twitterID = user.getId();

                    getUserFollowers(twitterID, twitterSession);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(TwitterException e) {
            }
        });
    }

    private void getUserFollowers(long twitterId, TwitterSession twitterSession) {

        MyTwitterApiClient myTwitterApiClient = new MyTwitterApiClient(twitterSession);
        myTwitterApiClient.getCustomService().getFollowers(twitterId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void success(Result<ResponseBody> result) {
                Log.e("list", result.data.toString());
                Reader isr = new InputStreamReader(result.data.byteStream());
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();

                try {
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Gson gson = new Gson();
                Type type = new TypeToken<ResponseData>() {}.getType();
                ResponseData responseData = gson.fromJson(sb.toString(), type);
                ArrayList<User> followers = responseData.getUsers();
                setRecyclerView(followers);

            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("error", exception.toString());
            }
        });
    }

    private void setRecyclerView(ArrayList<User> followers) {
        FollowersAdapter adapter = new FollowersAdapter(this);
        adapter.setFollowers(followers);
        rvFollowers.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvFollowers.setHasFixedSize(true);
        rvFollowers.setAdapter(adapter);
    }
}
