package edu.iupui.strissle.farbucks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.iupui.strissle.farbucks.model.FarbucksBucket;
import edu.iupui.strissle.farbucks.model.Location;

/**
 * Created by Samantha on 2/24/2017.
 */

public class LocationFragment extends Fragment {

    private TextView mLocationTitleTextView;
    private RecyclerView mLocationRecyclerView;
    private LocationAdapter mLocationAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_location, container, false);

        // Wire up controls on layout
        mLocationRecyclerView = (RecyclerView) v.findViewById(R.id.location_recycler_view);
        mLocationRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return v;

    }
    private void updateUI() {

        // QUERY FOR A LIST OF ALL LOCATIONS FROM THE DATABASE
        FarbucksBucket farbucksBucket = FarbucksBucket.getInstance(getActivity().getApplication());
        List<Location> allLocations = farbucksBucket.getLocations();

        // ADD LOCATIONS TO ADAPTER
        mLocationAdapter = new LocationAdapter(allLocations);
        mLocationRecyclerView.setAdapter(mLocationAdapter);
    }

    private class LocationHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView mTitleTextView;
        public Location mLocation;

        public void bindLocation(Location location){
            mLocation = location;
        }

        public LocationHolder(View itemView) {
            super(itemView);

            mTitleTextView = (TextView) itemView;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = LocationDetailActivity.newIntent(getActivity(), mLocation.getId() );
            startActivity(intent);
        }

    }

    private class LocationAdapter extends RecyclerView.Adapter<LocationHolder> {

        private List<Location> mLocationList;

        public LocationAdapter(List<Location> allLocations) {
            mLocationList = allLocations;
        }

        @Override
        public LocationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return new LocationHolder(view);
        }

        @Override
        public void onBindViewHolder(LocationHolder holder, int position) {
            Location thisLocation = mLocationList.get(position);
            holder.mTitleTextView.setText(thisLocation.getName());
            holder.bindLocation(thisLocation);

        }



        @Override
        public int getItemCount() {
            return mLocationList.size();
        }

    }

}
