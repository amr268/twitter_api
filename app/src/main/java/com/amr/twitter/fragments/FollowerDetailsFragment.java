package com.amr.twitter.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amr.twitter.R;
import com.bumptech.glide.Glide;
import com.twitter.sdk.android.core.models.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FollowerDetailsFragment extends Fragment {

    public static final String ITEM_FOLLOWER = "ITEM_FOLLOWER";
    private OnFragmentInteractionListener mListener;
    private User follower;
    @BindView(R.id.img_follower_profile)
    ImageView imgFollowerProfile;
    @BindView(R.id.tv_follower_name)
    TextView tvFollowerName;
    @BindView(R.id.tv_follower_hash)
    TextView tvFollowerHash;
    @BindView(R.id.tv_follower_bio)
    TextView tvFollowerBio;

    public FollowerDetailsFragment() {
    }

    public static FollowerDetailsFragment newInstance(User user) {
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
            follower = (User) getArguments().getSerializable(ITEM_FOLLOWER);
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
        Glide.with(imgFollowerProfile.getContext()).load(follower.profileImageUrl).into(imgFollowerProfile);
        tvFollowerBio.setText(follower.description);
        tvFollowerName.setText(follower.name);
        tvFollowerHash.setText(follower.url);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
