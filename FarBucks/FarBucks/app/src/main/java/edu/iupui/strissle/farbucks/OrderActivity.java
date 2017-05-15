package edu.iupui.strissle.farbucks;

import android.support.v4.app.Fragment;

public class OrderActivity extends SingleFragmentActivity {


    protected Fragment createFragment() {
        return new OrderFragment();
    }
}
