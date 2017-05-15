package edu.iupui.strissle.farbucks.model;

/**
 * Created by Samantha on 3/6/2017.
 */

import android.app.Application;
import android.content.Context;

import java.util.List;

import edu.iupui.strissle.farbucks.FarbucksApp;

public class FarbucksBucket {

    // CREATE A STATIC VARIABLE THAT HOLDS A REFERENCE TO THE ONE AND ONLY OBJECT OF THIS CLASS

    private static FarbucksBucket sFarbucksBucket;

    private LocationDao mLocationDao;
    private MenuItemDao mMenuItemDao;
    private DaoSession mDaoSession;


    // CONSTRUCTOR
    private FarbucksBucket(Context context) {
        mDaoSession = ((FarbucksApp) context).getDaoSession();
        mLocationDao = mDaoSession.getLocationDao();
        mMenuItemDao = mDaoSession.getMenuItemDao();
    }


    // RETRIEVE THE SINGLE INSTANCE OF FarbucksBucket
    public static FarbucksBucket getInstance(Context context) {
        if (sFarbucksBucket == null) {
            sFarbucksBucket = new FarbucksBucket(context);
        }
        return sFarbucksBucket;
    }

    // RETURN A LIST OF ALL LOCATIONS IN THE DATABASE
    public List<Location> getLocations() {
        List<Location> allLocations = mLocationDao.loadAll();
        return allLocations;
    }

    public List<MenuItem> getMenuItems() {
        List<MenuItem> allMenuItems = mMenuItemDao.loadAll();
        return allMenuItems;
    }


    public Location getFarbucksLocation(long farbucksID) {
        for (Location location : mLocationDao.loadAll()) {
            if (location.getId().equals(farbucksID)) {
                return location;
            }
        }
        return null;
    }

}

