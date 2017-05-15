package com.example.ericsmith.nflteams;

import android.media.Image;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import java.util.UUID;

/**
 * Created by Eric Smith on 2/3/2017.
 */

public class NFLTeam extends FragmentActivity {

    private UUID mId;
    private String mTeamName;
    private String mTeamShortName;
    private String mLogoImage;
    private String mConference;
    private String mDivision;
    private String mStadium;

    public String getTeamName() {
        return mTeamName;
    }

    public void setTeamName(String teamName) {
        mTeamName = teamName;
    }

    public String getTeamShortName() {
        return mTeamShortName;
    }

    public void setTeamShortName(String teamShortName) {
        mTeamShortName = teamShortName;
    }

    public String getLogoImage() {
        return mLogoImage;
    }

    public void setLogoImage(String logoImage) {
        mLogoImage = logoImage;
    }

    public String getDivision() {
        return mDivision;
    }

    public void setDivision(String division) {
        mDivision = division;
    }

    public String getConference() {
        return mConference;
    }

    public void setConference(String conference) {
        mConference = conference;
    }

    public String getStadium() {
        return mStadium;
    }

    public void setStadium(String stadium) {
        mStadium = stadium;
    }

    public String getLatitude() {
        return mLatitude;
    }

    public void setLatitude(String latitude) {
        mLatitude = latitude;
    }

    public String getLongitude() {
        return mLongitude;
    }

    public void setLongitude(String longitude) {
        mLongitude = longitude;
    }

    private String mLatitude;
    private String mLongitude;




    public NFLTeam(String teamName, String teamShortName, String logoImage, String conference, String division, String stadium, String latitude, String longitude) {


        mId = UUID.randomUUID();
        mTeamName = teamName;
        mTeamShortName = teamShortName;
        mLogoImage = logoImage;
        mConference = conference;
        mDivision = division;
        mStadium = stadium;
        mLatitude = latitude;
        mLongitude = longitude;


    }//End constructor

    public UUID getId() {
        return mId;
    }
}