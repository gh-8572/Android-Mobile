package edu.iupui.strissle.farbucks;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.OnMapReadyCallback;


import edu.iupui.strissle.farbucks.model.FarbucksBucket;
import edu.iupui.strissle.farbucks.model.Location;


/**
 * Created by Samantha on 3/22/2017.
 */

public class LocationDetailFragment extends Fragment implements OnMapReadyCallback {

    private static final String ARG_FARBUCKS_ID = "farbucks_location_id";

    private Location mFarbucksLocation;

    private TextView mFarbucksNameTextView;
    private TextView mAddressTextView;
    private TextView mCityTextView;
    private TextView mStateTextView;
    private TextView mZipText;
    public ImageView mmthumbnail;


    private MapView mFarbucksMapView;

    private GoogleMap mGoogleMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        long farbucksID = (long) getArguments().getSerializable(ARG_FARBUCKS_ID);

        mFarbucksLocation = FarbucksBucket.getInstance(getActivity()).getFarbucksLocation(farbucksID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_location_detail, container, false);

        mFarbucksNameTextView = (TextView) v.findViewById(R.id.farbucks_name_textview);
        mAddressTextView = (TextView) v.findViewById(R.id.address_textview);
        mCityTextView = (TextView) v.findViewById(R.id.city_textview);
        mStateTextView = (TextView) v.findViewById(R.id.state_textview);
        mZipText = (TextView) v.findViewById(R.id.zip_textview);
        mmthumbnail = (ImageView) v.findViewById(R.id.mmthumbnail);
        mFarbucksMapView = (MapView) v.findViewById(R.id.farbucks_mapview);

        mFarbucksNameTextView.setText(mFarbucksLocation.getName());
        mAddressTextView.setText(mFarbucksLocation.getAddress());
        mCityTextView.setText(mFarbucksLocation.getCity());
        mStateTextView.setText(mFarbucksLocation.getState());
        mZipText.setText((mFarbucksLocation.getZipcode()));
        mmthumbnail.setImageDrawable(mmthumbnail.getDrawable());

        mFarbucksMapView.onCreate(savedInstanceState);
        mFarbucksMapView.getMapAsync(this);


        // IMAGE STUFF

        return v;


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("FARBUCKS", "Map ready triggered.");

        mGoogleMap = googleMap;

        LatLng locCoordinates = new LatLng(
                Double.parseDouble(mFarbucksLocation.getLatitude()),
                Double.parseDouble(mFarbucksLocation.getLongitude())
        );

        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);

        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locCoordinates, 17));

        mFarbucksMapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFarbucksMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mFarbucksMapView.onDestroy();
    }

    /////////////////////////////////

    public static LocationDetailFragment newInstance(Long farbucksID) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_FARBUCKS_ID, farbucksID);

        LocationDetailFragment fragment = new LocationDetailFragment();
        fragment.setArguments(args);
        return fragment;


    }
}
