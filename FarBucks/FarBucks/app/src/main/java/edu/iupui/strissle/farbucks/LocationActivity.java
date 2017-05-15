package edu.iupui.strissle.farbucks;

import android.support.v4.app.Fragment;

public class LocationActivity extends SingleFragmentActivity {

    protected Fragment createFragment() {
        return new LocationFragment();
    }
}
