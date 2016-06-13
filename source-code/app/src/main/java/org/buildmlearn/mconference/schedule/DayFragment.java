package org.buildmlearn.mconference.schedule;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import org.buildmlearn.mconference.R;
import org.buildmlearn.mconference.adapters.DayRecyclerView;
import org.buildmlearn.mconference.model.TalkDetails;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DayFragment extends Fragment implements SearchView.OnQueryTextListener {

    RecyclerView dayRecyclerView;
    ArrayList<TalkDetails> dummyTalks;
    DayRecyclerView dayAdapter;

    public DayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_day, container, false);
        dayRecyclerView = (RecyclerView) view.findViewById(R.id.day_recycler_view);
        dayRecyclerView.setHasFixedSize(true);
        dayRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        dummyTalks = new ArrayList<>(3);
        dummyTalks.add(new TalkDetails("Talk #1", "http://blogs.gartner.com/smarterwithgartner/files/2015/03/Guy_Kawasaki_1_inline.jpg",
                new Date(1472704200000l), new Date(1472707800000l),"Albert Hall", getResources().getString(R.string.lorem)));
        dummyTalks.add(new TalkDetails("Talk #2", "",
                new Date(1472715000000l), new Date(1472718600000l), "Nehru Hall", getResources().getString(R.string.lorem)));
        dummyTalks.add(new TalkDetails("Talk #3", "http://static.dnaindia.com/sites/default/files/styles/half/public/2016/01/12/413826-swami.jpg",
                new Date(1472722200000l), new Date(1472729400000l), "Gandhi Hall", getResources().getString(R.string.lorem)));

        dayAdapter = new DayRecyclerView(dummyTalks);
        dayRecyclerView.setAdapter(dayAdapter);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.schedule_menu, menu);

        MenuItem item = menu.findItem(R.id.schedule_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextChange(String query) {
        ArrayList<TalkDetails> filterList = filter(dummyTalks, query);
        dayAdapter.setFilter(filterList);
        return  true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private ArrayList<TalkDetails> filter(ArrayList<TalkDetails> talks, String query) {
        query = query.toLowerCase();

        ArrayList<TalkDetails> filteredList = new ArrayList<>();
        for (TalkDetails talkDetails:talks) {
            String name = talkDetails.getName().toLowerCase();
            String location = talkDetails.getLocation().toLowerCase();
            String desc = talkDetails.getDesc().toLowerCase();

            if (name.contains(query) || location.contains(query) || desc.contains(query))
                filteredList.add(talkDetails);
        }

        return  filteredList;
    }
}
