package org.buildmlearn.mconference.schedule;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.buildmlearn.mconference.R;
import org.buildmlearn.mconference.adapters.DayRecyclerView;
import org.buildmlearn.mconference.model.TalkDetails;

import java.util.ArrayList;
import java.util.Date;

public class DayFragment extends Fragment implements SearchView.OnQueryTextListener {

    private ArrayList<TalkDetails> dummyTalks;
    private DayRecyclerView dayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_day, container, false);
        RecyclerView dayRecyclerView = (RecyclerView) view.findViewById(R.id.day_recycler_view);
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


        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Log.d("Reached","Menu Collapse menu");
                dayAdapter.setFilter(dummyTalks);

                return true;
            }
        });
    }

    @Override
    public boolean onQueryTextChange(String query) {
        ArrayList<TalkDetails> filterList = filter(dummyTalks, query.toLowerCase());
        dayAdapter.setFilter(filterList);
        return  true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private ArrayList<TalkDetails> filter(ArrayList<TalkDetails> talks, String query) {

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
