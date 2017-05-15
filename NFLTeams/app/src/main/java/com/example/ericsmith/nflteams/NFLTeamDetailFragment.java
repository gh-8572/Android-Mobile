package com.example.ericsmith.nflteams;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.UUID;


/**
 * Created by Eric Smith on 2/1/2017.
 */

public class NFLTeamDetailFragment extends Fragment {

    private static final String ARG_NFLTEAM_ID = "nfl_team_id";

    private TextView mNFLTeamNameTextView;
    private TextView mDivisionTextView;
    private TextView mStadiumTextView;
    private ImageView mLogoImageView;

    private NFLTeam mNFLTeam;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        UUID nflTeamID = (UUID) getArguments().getSerializable(ARG_NFLTEAM_ID);

        mNFLTeam = NFLTeamBucket.get(getActivity()).getNFLTeam(nflTeamID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_nflteam_detail, container, false);

        //IN fragments we wire up widgets in the onCreateView Method Instead of OnCreate

        mNFLTeamNameTextView = (TextView) v.findViewById(R.id.mNFLTeamNameTextView);
        mDivisionTextView = (TextView) v.findViewById(R.id.mDivisionTextView);
        mStadiumTextView = (TextView) v.findViewById(R.id.mStadiumTextView);
        mLogoImageView = (ImageView) v.findViewById(R.id.mLogoImageView);



        //Add info from the NFLTeam object to the TextView on the layout
        mNFLTeamNameTextView.setText(mNFLTeam.getTeamName());
        mDivisionTextView.setText(mNFLTeam.getDivision());
        mStadiumTextView.setText(mNFLTeam.getStadium());

        String uri ="@drawable/" + mNFLTeam.getTeamShortName();


        int imageResources = getResources().getIdentifier(uri, null, getContext().getPackageName());


        //Drawable res = getResources().getDrawable(imageResources);
        //mLogoImageView.setImageDrawable(res);

        Glide
                .with(getContext())
                .load(imageResources)
                .into(mLogoImageView);


        return v;

    }

    public static NFLTeamDetailFragment newInstance(UUID nflTeamId) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_NFLTEAM_ID, nflTeamId);

        NFLTeamDetailFragment fragment = new NFLTeamDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }







}
