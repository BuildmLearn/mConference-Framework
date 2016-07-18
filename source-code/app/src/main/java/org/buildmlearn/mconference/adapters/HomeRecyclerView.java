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
import org.buildmlearn.mconference.model.ConferenceMeta;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by jai on 16/7/16.
 */

public class HomeRecyclerView extends RecyclerView.Adapter<HomeRecyclerView.ConferenceMetaObject> {
    private ArrayList<ConferenceMeta> conferences;

    public static class ConferenceMetaObject extends RecyclerView.ViewHolder {
        public ImageView conferenceImage;
        public TextView conferenceName;
        public TextView conferenceDate;
        public TextView conferenceVenue;

        public  ConferenceMetaObject(View view) {
            super(view);
            conferenceImage = (ImageView) view.findViewById(R.id.conference_card_img);
            conferenceName = (TextView) view.findViewById(R.id.conference_card_name);
            conferenceDate = (TextView) view.findViewById(R.id.conference_card_date);
            conferenceVenue = (TextView) view.findViewById(R.id.conference_card_venue);
        }
    }

    public HomeRecyclerView(ArrayList<ConferenceMeta> conferences) {
        this.conferences = conferences;
    }

    @Override
    public ConferenceMetaObject onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_card_view, parent, false);

        ConferenceMetaObject conferenceMetaObject = new ConferenceMetaObject(view);
        return conferenceMetaObject;
    }

    @Override
    public void onBindViewHolder(ConferenceMetaObject holder, int position) {
        Picasso.with(holder.conferenceImage.getContext())
                .load(Uri.parse(conferences.get(position).getLogoURL()))
                .error(R.drawable.placeholder_1)
                .fit().centerCrop().into(holder.conferenceImage);
        holder.conferenceName.setText(conferences.get(position).getName());

        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM''yy");
        String conferenceDateText = dateFormat.format(conferences.get(position).getDate());
        holder.conferenceDate.setText(conferenceDateText);

        holder.conferenceVenue.setText(conferences.get(position).getVenue());
    }

    @Override
    public int getItemCount() {
        return conferences.size();
    }
}