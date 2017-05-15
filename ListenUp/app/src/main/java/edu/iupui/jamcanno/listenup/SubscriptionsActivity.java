package edu.iupui.jamcanno.listenup;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by Eric Smith on 5/1/2017.
 */

public class SubscriptionsActivity {


    public static final String EXTRA_SUB_ID="edu/iupui/strissle/sub_id";

    public static Intent newIntent(Context packageContext, String id) {
        Intent intent = new Intent(packageContext, SubscriptionsActivity.class);
        intent.putExtra(EXTRA_SUB_ID, id);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new SubscriptionsFragment();

    }


}
