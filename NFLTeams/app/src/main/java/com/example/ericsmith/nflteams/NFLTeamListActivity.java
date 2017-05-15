package com.example.ericsmith.nflteams;

import android.support.v4.app.Fragment;


/**
 * Created by Eric Smith on 2/6/2017.
 */

public class NFLTeamListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new NFLTeamListFragment();
    }
}
