package edu.iupui.strissle.farbucks;

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
import edu.iupui.strissle.farbucks.model.MenuItem;

/**
 * Created by Samantha on 2/24/2017.
 */

public class MenuFragment extends Fragment {

    private TextView mMenuTitleTextView;
    private RecyclerView mMenuRecyclerView;
    private MenuAdapter mMenuAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, container, false);

//        mMenuTitleTextView = (TextView) v.findViewById(R.id.menu_title_textview);
//
//        return v;

        // Wire up controls on layout
        mMenuRecyclerView = (RecyclerView) v.findViewById(R.id.menu_recycler_view);

        mMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return v;

    }

    private void updateUI() {

        // QUERY FOR A LIST OF ALL MANUITEMS FROM THE DATABASE
        FarbucksBucket farbucksBucket = FarbucksBucket.getInstance(getActivity().getApplication());
        List<MenuItem> allMenuItems = farbucksBucket.getMenuItems();

        // ADD MenuItems TO ADAPTER
        mMenuAdapter = new MenuFragment.MenuAdapter(allMenuItems);
        mMenuRecyclerView.setAdapter(mMenuAdapter);
    }

    private class MenuHolder extends RecyclerView.ViewHolder {

        public TextView mTitleTextView;

        public MenuHolder(View itemView) {
            super(itemView);

            mTitleTextView = (TextView) itemView;
        }

    }

    private class MenuAdapter extends RecyclerView.Adapter<MenuFragment.MenuHolder> {

        private List<MenuItem> mMenuList;

        public MenuAdapter(List<MenuItem> allMenuItems) {
            mMenuList = allMenuItems;
        }

        @Override
        public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return new MenuHolder(view);
        }

        @Override
        public void onBindViewHolder(MenuHolder holder, int position) {
            MenuItem thisMenuItem = mMenuList.get(position);
            holder.mTitleTextView.setText(thisMenuItem.getName());
        }

        @Override
        public int getItemCount() {
            return mMenuList.size();
        }

    }

}
