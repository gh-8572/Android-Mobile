package com.example.ericsmith.nflteams;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import java.util.UUID;

public class NFLTeamDetailActivity extends SingleFragmentActivity{

  private  static final String EXTRA_NFLTEAM_ID = "com.example.ericsmith.nflteams.nflteam_id";


  @Override
    protected Fragment createFragment() {

      //return new NFLTeamDetailFragment();

      UUID nflTeamID = (UUID) getIntent().getSerializableExtra(EXTRA_NFLTEAM_ID);

      return NFLTeamDetailFragment.newInstance(nflTeamID);
  }

    public static Intent newIntent(Context packageContext, UUID nflTeamID){

    Intent intent = new Intent(packageContext, NFLTeamDetailActivity.class);
    intent.putExtra(EXTRA_NFLTEAM_ID, nflTeamID);
    return intent;
  }
  }

