package com.amr.twitter.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amr.twitter.R;
import com.amr.twitter.moduls.Follower;
import com.bumptech.glide.Glide;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthException;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FollowerDetailsFragment extends Fragment {

    public static final String ITEM_FOLLOWER = "ITEM_FOLLOWER";
    private OnFragmentInteractionListener mListener;
    private Follower follower;
    @BindView(R.id.img_follower_profile)
    ImageView imgFollowerProfile;
    @BindView(R.id.tv_follower_name)
    TextView tvFollowerName;
    @BindView(R.id.tv_follower_hash)
    TextView tvFollowerHash;
    @BindView(R.id.tv_follower_bio)
    TextView tvFollowerBio;
    @BindView(R.id.rv_tweets)
    RecyclerView rvTweets;

    public FollowerDetailsFragment() {
    }

    public static FollowerDetailsFragment newInstance(Follower
                                                              user) {
        FollowerDetailsFragment fragment = new FollowerDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ITEM_FOLLOWER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            follower = (Follower) getArguments().getSerializable(ITEM_FOLLOWER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_follower_details, container, false);
        ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    private void initView() {
        Glide.with(imgFollowerProfile.getContext()).load(follower.getProfile_image_url()).into(imgFollowerProfile);
        tvFollowerBio.setText(follower.getDescription());
        tvFollowerName.setText(follower.getName());
        tvFollowerHash.setText("@"+follower.getScreen_name());
        getTweets(follower.getId());
    }

    private void getTweets(Long id) {
        UserTimeline userTimeline = new UserTimeline.Builder().userId(id).build();
        final Callback<Tweet> actionCallback = new Callback<Tweet>() {
            @Override
            public void success(Result<Tweet> result) {
                // Intentionally blank
            }

            @Override
            public void failure(TwitterException exception) {
                if (exception instanceof TwitterAuthException) {
                    //startActivity(TwitterCoreMainActivity.newIntent(getActivity()));
                }
            }
        };
        TweetTimelineRecyclerViewAdapter adapter =
                new TweetTimelineRecyclerViewAdapter.Builder(getContext())
                        .setTimeline(userTimeline)
                        .setViewStyle(R.style.tw__TweetLightWithActionsStyle)
                        .setOnActionCallback(actionCallback)
                        .build();
        rvTweets.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvTweets.setHasFixedSize(true);
        rvTweets.setAdapter(adapter);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
