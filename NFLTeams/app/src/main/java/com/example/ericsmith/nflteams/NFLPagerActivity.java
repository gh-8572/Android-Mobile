package com.example.ericsmith.nflteams;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.UUID;


/**
 * Created by Eric Smith on 2/20/2017.
 */

public class NFLPagerActivity extends FragmentActivity {
    private static final String EXTRA_NFLTEAM_ID = "com.example.ericsmith.nflteams";

    private ViewPager mViewPager;
    private List<NFLTeam> mNFLTeams;

    public static Intent newIntent(Context packageContext, UUID nflTeamID){
        Intent intent = new Intent(packageContext, NFLPagerActivity.class);
        intent.putExtra(EXTRA_NFLTEAM_ID, nflTeamID);
        return intent;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nfl_detail_pager);

        UUID nflTeamId = (UUID) getIntent().getSerializableExtra(EXTRA_NFLTEAM_ID);

        mViewPager = (ViewPager) findViewById(R.id.nfl_detail_pager_view_pager);

        mNFLTeams = NFLTeamBucket.get(this).getNFLTeams();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager){

            @Override
            public Fragment getItem(int position){
                NFLTeam team = mNFLTeams.get(position);

                return NFLTeamDetailFragment.newInstance(team.getId());
            }

            @Override
            public int getCount() { return mNFLTeams.size();}
        });


        for(int i = 0; i< mNFLTeams.size(); i++){
            if (mNFLTeams.get(i).getId().equals(nflTeamId)) {

                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }


}
