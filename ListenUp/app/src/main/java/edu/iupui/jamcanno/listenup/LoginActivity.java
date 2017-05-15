package edu.iupui.jamcanno.listenup;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by Eric Smith on 4/26/2017.
 */

public class LoginActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return LoginFragment.newInstance();
    }

}