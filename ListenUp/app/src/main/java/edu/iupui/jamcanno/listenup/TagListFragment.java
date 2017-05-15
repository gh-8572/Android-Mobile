package edu.iupui.jamcanno.listenup;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.List;

import edu.iupui.jamcanno.listenup.model.Tag;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;




/**
 * Created by alexc on 4/3/2017.
 */

public class TagListFragment extends Fragment {

    private RecyclerView mTagListRecyclerView;
    private TagAdapter mTagAdapter;
    private Tag[] mAPITags;
    private ImageView mThumbnailImageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){



        //Inflate the layout file
        View view = inflater.inflate(R.layout.fragment_taglist, container, false);

        //Wire up the recycler view
        mTagListRecyclerView = (RecyclerView)view.findViewById(R.id.taglist_recycler_view);
        mTagListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mThumbnailImageView = (ImageView) view.findViewById(R.id.mThumbnail);

        //make a connection to the api
        GPodderAPI client = ServiceGenerator.createService(GPodderAPI.class);

        Call<Tag[]> call = client.getTopTags("20");


        call.enqueue(new Callback<Tag[]>() {
            @Override
            public void onResponse(Call<Tag[]> call, Response<Tag[]> response) {

                Log.d("ListenUp", "Success");
                Tag[] tags = response.body();
                Log.d("ListenUp", String.valueOf(tags.length));
                updateUI(tags);
            }

            @Override
            public void onFailure(Call<Tag[]> call, Throwable t) {
                Log.d("ListenUp", "Sucks to suck");
            }
        });



        return view;
    }

    private void updateUI(Tag[] tags) {


        // ADD LOCATIONS TO ADAPTER
        mTagAdapter = new TagAdapter(Arrays.asList(tags));
        mTagListRecyclerView.setAdapter(mTagAdapter);
    }

    private class TagListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Tag mTag;
        public TextView mTagTextView;
        public ImageView mThumbnail;

        public TagListHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTagTextView = (TextView) itemView;
            mThumbnail = (ImageView) itemView.findViewById(R.id.mThumbnail);
        }

        public void bindTag(Tag tag) {
            mTag = tag;

            this.mTagTextView.setText(tag.getTag());
        }

        @Override
        public void onClick(View v) {

            Intent intent = PodcastListActivity.newIntent(getActivity(), mTag.getTag() );
            startActivity(intent);

            Toast.makeText(getActivity(), mTag.getTag() + " clicked!", Toast.LENGTH_SHORT).show();
        }

    }
        public class TagAdapter extends RecyclerView.Adapter<TagListFragment.TagListHolder>{

            private List<Tag> mTagList;

            public TagAdapter(List<Tag> allTagItems){
                mTagList = allTagItems;
            }

            @Override
            public TagListHolder onCreateViewHolder(ViewGroup parent, int viewType){
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
                return new TagListHolder(view);

            }


            @Override
            public void onBindViewHolder(TagListHolder holder, int position) {
                Tag thisTag = mTagList.get(position);
                holder.bindTag(thisTag);





            }

            @Override
            public int getItemCount() {

                return mTagList.size();
            }








    }
}
