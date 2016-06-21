package org.buildmlearn.mconference.conference;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.buildmlearn.mconference.R;
import org.buildmlearn.mconference.adapters.SponsorRecyclerView;
import org.buildmlearn.mconference.constant.Constants;
import org.buildmlearn.mconference.database.Database;
import org.buildmlearn.mconference.model.SponsorDetails;

import java.util.ArrayList;

public class Sponsor extends Fragment implements Constants {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sponsor, container, false);

        // boolean webPageprovided = false;

        RecyclerView sponsorRecyclerView = (RecyclerView) view.findViewById(R.id.sponsor_recycler_view);
        sponsorRecyclerView.setHasFixedSize(true);
        sponsorRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Database db = new Database(view.getContext());
        ArrayList<SponsorDetails> sponsors = db.getSponsors();

        SponsorRecyclerView sponsorAdapter = new SponsorRecyclerView(sponsors);
        sponsorRecyclerView.setAdapter(sponsorAdapter);

        return view;
    }

}
