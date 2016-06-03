package org.buildmlearn.mconference.adapters;

import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.buildmlearn.mconference.R;
import org.buildmlearn.mconference.model.SponsorDetails;

import java.util.ArrayList;

/**
 * Created by jai on 3/6/16.
 */
public class SponsorRecyclerView extends RecyclerView.Adapter<SponsorRecyclerView.SponsorDetailsObject> {

    private ArrayList<SponsorDetails> sponsors;

    public static class SponsorDetailsObject extends RecyclerView.ViewHolder {
        CardView sponsorCardView;
        TextView sponsorName;
        ImageView sponsorImage;

        public SponsorDetailsObject(View view) {
            super(view);
            sponsorCardView = (CardView) view.findViewById(R.id.sponsor_card_view);
            sponsorName = (TextView) view.findViewById(R.id.sponsor_name);
            sponsorImage = (ImageView) view.findViewById(R.id.sponsor_img);
        }
    }

    public SponsorRecyclerView(ArrayList<SponsorDetails> sponsors) {
        this.sponsors = sponsors;
    }

    @Override
    public SponsorDetailsObject onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sponsor_card_view, parent, false);

        SponsorDetailsObject sponsorDetailsObject = new SponsorDetailsObject(view);
        return  sponsorDetailsObject;
    }

    @Override
    public void onBindViewHolder(SponsorDetailsObject holder, int position) {
        holder.sponsorName.setText(sponsors.get(position).getName());
        Picasso.with(holder.sponsorImage.getContext())
                .load(Uri.parse(sponsors.get(position).getLogoURL())).into(holder.sponsorImage);
    }

    @Override
    public int getItemCount() {
        return sponsors.size();
    }
}
