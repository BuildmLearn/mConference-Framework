package org.buildmlearn.mconference.adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.buildmlearn.mconference.R;
import org.buildmlearn.mconference.model.TalkDetails;

import java.util.ArrayList;

/**
 * Created by jai on 4/6/16.
 */
public class DayRecyclerView extends RecyclerView.Adapter<DayRecyclerView.TalkDetailsObject> {

    private ArrayList<TalkDetails> talks;

    public static class TalkDetailsObject extends RecyclerView.ViewHolder {

        TextView talkName;
        ImageView talkImage;
        TextView talkTime;
        TextView talkLocation;
        TextView talkShortDesc;

        public TalkDetailsObject(View view) {
            super(view);
            talkName = (TextView) view.findViewById(R.id.talk_name);
            talkImage= (ImageView) view.findViewById(R.id.talk_image);
            talkTime = (TextView) view.findViewById(R.id.talk_time);
            talkLocation = (TextView) view.findViewById(R.id.talk_location);
            talkShortDesc = (TextView) view.findViewById(R.id.talk_desc_short);
        }
    }

    public DayRecyclerView(ArrayList<TalkDetails> talks) {
        this.talks = talks;
    }

    @Override
    public TalkDetailsObject onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_card_view, parent, false);

        TalkDetailsObject talkDetailsObject = new TalkDetailsObject(view);
        return talkDetailsObject;
    }

    @Override
    public void onBindViewHolder(TalkDetailsObject holder, int position) {
        Picasso.with(holder.talkImage.getContext())
                .load(Uri.parse(talks.get(position).getImageURL()))
                .error(R.drawable.placeholder_1)
                .fit().centerCrop().into(holder.talkImage);
        holder.talkName.setText(talks.get(position).getName());
        holder.talkTime.setText(talks.get(position).getTime());
        holder.talkLocation.setText(talks.get(position).getLocation());
        holder.talkShortDesc.setText(talks.get(position).getShortDesc());
    }

    @Override
    public int getItemCount() {
        return talks.size();
    }
}
