package edu.iupui.jamcanno.listenup;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by Eric Smith on 4/26/2017.
 */

public class DeviceListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment(){

        return new DeviceListFragment().newInstance();
    }

    public static Intent newIntent(Context packageContext) {

        Intent intent = new Intent(packageContext, DeviceListActivity.class);
        return intent;
    }

}