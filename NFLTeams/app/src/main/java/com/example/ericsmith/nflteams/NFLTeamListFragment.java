package com.example.ericsmith.nflteams;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.tv.TvContract;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DrawableUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import static android.R.attr.id;
import static android.R.attr.targetPackage;
import static com.example.ericsmith.nflteams.R.id.Frank;
import static com.example.ericsmith.nflteams.R.id.TeamDivision;
import static com.example.ericsmith.nflteams.R.id.image;
import static com.example.ericsmith.nflteams.R.id.list_item;
import static com.example.ericsmith.nflteams.R.id.mNFLTeamNameTextView;
import static com.example.ericsmith.nflteams.R.id.mThumbnail;
import static com.example.ericsmith.nflteams.R.id.useLogo;


/**
 * Created by Eric Smith on 2/6/2017.
 */

public class NFLTeamListFragment extends Fragment {

//Member variables

    private RecyclerView mNFLTeamRecyclerView;
    private NFLTeamAdapter mNFLTeamAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState){

        //Wire up the recycler view
        View view = inflater.inflate(R.layout.fragment_nflteam_list, container, false);

        mNFLTeamRecyclerView = (RecyclerView)view.findViewById(R.id.nflteam_recycler_view);

        mNFLTeamRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;

    }

    private void updateUI(){
        NFLTeamBucket teamBucket = NFLTeamBucket.get(getActivity());
        List<NFLTeam> allTeams = teamBucket.getNFLTeams();

        mNFLTeamAdapter = new NFLTeamAdapter(allTeams);
        mNFLTeamRecyclerView.setAdapter(mNFLTeamAdapter);

    }

    private class NFLTeamHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener{

        private NFLTeam mNFLTeam;

        private TextView mNFLTeamNameTextView;
        private TextView mNFLTeamDivision;
        public ImageView mThumbnail;


        //public TextView mNFLTeamNameTextView;

            public NFLTeamHolder(View itemView) {
            super(itemView);



                itemView.setOnClickListener(this);




            //Wire up the built in text view for the item view
           mNFLTeamNameTextView = (TextView) itemView.findViewById(R.id.Frank);
                mNFLTeamDivision = (TextView) itemView.findViewById(R.id.TeamDivision);
                mThumbnail = (ImageView) itemView.findViewById(R.id.mThumbnail);


        }

        //Robs code we didn't implement from the book


        public void bindNFLTeam(NFLTeam nflTeam){
            mNFLTeam = nflTeam;

            mNFLTeamNameTextView.setText(nflTeam.getTeamName());
            mNFLTeamDivision.setText(nflTeam.getDivision());
        }


    @Override
    public void onClick(View v) {
       // Toast.makeText(getActivity(),
             //   mNFLTeamNameTextView.getText() + " clicked!", Toast.LENGTH_SHORT)
             //   .show();

       // Intent intent = new Intent(getActivity(), NFLTeamDetailActivity.class);

                //Intent intent = NFLTeamDetailActivity.newIntent(getActivity(), mNFLTeam.getId());
        Intent intent = NFLPagerActivity.newIntent(getActivity(), mNFLTeam.getId());
                    startActivity(intent);
    }
    }

    private class NFLTeamAdapter extends RecyclerView.Adapter<NFLTeamHolder>{

        private List<NFLTeam> mNFLTeamList;

        //constructor
        public NFLTeamAdapter(List<NFLTeam> NFLTeams){
            mNFLTeamList = NFLTeams;

        }

        @Override
        public NFLTeamHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_team, parent, false);


            return new NFLTeamHolder(view);


        }

        @Override
        public void onBindViewHolder(NFLTeamHolder holder, int position){
            NFLTeam team = mNFLTeamList.get(position);
            //holder.mNFLTeamNameTextView.setText(team.getTeamName());
            //holder.mNFLTeamDivision.setText(team.getDivision());
            holder.bindNFLTeam(team);

            String uri = "@drawable/" + team.getTeamShortName();
            int imageResource = getResources().getIdentifier(uri, null, getContext().getPackageName()  );
            Drawable res = getResources().getDrawable(imageResource);

            holder.mThumbnail.setImageDrawable(res);


            Glide.with(getContext()).load(imageResource).into(holder.mThumbnail);








        }




        @Override
        public int getItemCount(){
            return  mNFLTeamList.size();
        }



    }

}
