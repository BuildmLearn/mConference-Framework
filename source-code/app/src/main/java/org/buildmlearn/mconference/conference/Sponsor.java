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
import org.buildmlearn.mconference.model.SponsorDetails;

import java.util.ArrayList;

public class Sponsor extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sponsor, container, false);

        // boolean webPageprovided = false;

        RecyclerView sponsorRecyclerView = (RecyclerView) view.findViewById(R.id.sponsor_recycler_view);
        sponsorRecyclerView.setHasFixedSize(true);
        sponsorRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<SponsorDetails> dummySponsors = new ArrayList<>(3);
        dummySponsors.add(new SponsorDetails("Sponsor #1","https://mir-s3-cdn-cf.behance.net/project_modules/disp/3fe6027858667.55e61091bc63d.jpg"));
        dummySponsors.add(new SponsorDetails("Sponsor #2" , "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSJqpA4Kx0817SYkXx4eZKRTOKXBjZOhHIc4mIkevic2qyaqvco"));
        dummySponsors.add(new SponsorDetails("Sponsor #3", "https://developers.google.com/open-source/gsoc/images/gsoc2016-sun-373x373.png"));

        SponsorRecyclerView sponsorAdapter = new SponsorRecyclerView(dummySponsors);
        sponsorRecyclerView.setAdapter(sponsorAdapter);

        return view;
    }

}
