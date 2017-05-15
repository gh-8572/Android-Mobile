package edu.iupui.jamcanno.listenup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import edu.iupui.jamcanno.listenup.model.Subscription;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Eric Smith on 5/1/2017.
 */

public class SubscriptionsFragment {

    private RecyclerView mSubscriptionRecyclerView;
    private SubAdapter mSubAdapter;
    private List<Subscription> mSubscriptionsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //INFLATE LAYOUT
        View view = inflater.inflate(R.layout.fragment_subscriptions, container, false);

        //WIRE UP RECYCLER VIEW
        mSubscriptionRecyclerView = (RecyclerView) view.findViewById(R.id.sub_recycler_view);
        mSubscriptionRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity));

        //MAKE CONNECTION TO THE API
        GPodderAPI client = ServiceGenerator.createService(GPodderAPI.class, "samtrissler", "284ujenrs");

        //GET LIST OF SUBSCRIPTIONS FROM API
        Call<String> call = client.getSubscriptions("samtrissler");

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("LISTENUP", "Success!");
                Log.d("LISTENUP", String.valueOf(response.code()) + String.valueOf(response));
                Log.d("LISTENUP", String.valueOf(response.body()));
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("LISTENUP", "womp womp on subscriptions");
            }
        });

        return view;

    }

    private void updateUI(Subscription[] subscriptions) {

        mSubAdapter = new SubAdapter(Arrays.asList(subscriptions));
        mSubscriptionRecyclerView.setAdapter(mSubAdapter);

    }

    private class SubListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Subscription mSubscription;
        public TextView mSubscriptionTextView;

        public SubListHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mSubscriptionTextView = (TextView) itemView;
        }

        public void bindSubscription(Subscription subscription) {
            mSubscription = subscription;
            this.mSubscriptionTextView.setText(subscription.getTitle());
        }

        @Override
        public void onClick(View v) {

            Intent intent = SubscriptionsActivity.newIntent(getActivity(), mSubscription.getTitle());
            startActivity(intent);

            Toast.makeText(getActivity(), mSubscription.getTitle() + " clicked!", Toast.LENGTH_SHORT).show();
        }
    }

    private class SubAdapter extends RecyclerView.Adapter<SubListHolder> {

        private List<Subscription> mSubscriptionList;

        public SubAdapter(List<Subscription> allSubscriptions) {
            mSubscriptionList = allSubscriptions;
        }

        @Override
        public SubListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return new SubListHolder(view);
        }

        @Override
        public void onBindViewHolder(SubListHolder holder, int position) {
            Subscription thisSubscription = mSubscriptionsList.get(position);
            holder.mSubscriptionTextView.setText(thisSubscription.getTitle());
            holder.bindSubscription(thisSubscription);

        }

        @Override
        public int getItemCount() {
            return mSubscriptionList.size();
        }



    }


}
