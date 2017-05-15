package edu.iupui.strissle.farbucks;

import android.support.v4.app.Fragment;

public class MenuActivity extends SingleFragmentActivity {


    protected Fragment createFragment() {
        return new MenuFragment();
    }
}
