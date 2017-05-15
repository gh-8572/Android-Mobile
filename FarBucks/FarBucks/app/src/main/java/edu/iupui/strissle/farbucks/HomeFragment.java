package edu.iupui.strissle.farbucks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Samantha on 2/24/2017.
 */

public class HomeFragment extends Fragment {

    private TextView mHomeTitleTextView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        mHomeTitleTextView = (TextView) v.findViewById(R.id.home_title_textview);

        return v;
    }
}
