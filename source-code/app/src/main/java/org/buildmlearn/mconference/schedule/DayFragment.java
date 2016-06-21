package org.buildmlearn.mconference.schedule;


import android.content.Context;
import android.os.AsyncTask;
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
import org.buildmlearn.mconference.constant.Constants;
import org.buildmlearn.mconference.database.Database;
import org.buildmlearn.mconference.model.TalkDetails;

import java.util.ArrayList;

public class DayFragment extends Fragment implements SearchView.OnQueryTextListener, Constants {

    private ArrayList<TalkDetails> talks;
    private DayRecyclerView dayAdapter;
    RecyclerView dayRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_day, container, false);
        dayRecyclerView = (RecyclerView) view.findViewById(R.id.day_recycler_view);
        dayRecyclerView.setHasFixedSize(true);
        dayRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        new populateFragment().execute(view.getContext());
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
                Log.d("Reached", "Menu Collapse menu");
                dayAdapter.setFilter(talks);

                return true;
            }
        });
    }

    @Override
    public boolean onQueryTextChange(String query) {
        ArrayList<TalkDetails> filterList = filter(talks, query.toLowerCase());
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

    private class populateFragment extends AsyncTask<Context, Void, Void> {

        protected Void doInBackground(Context... contexts) {
            Bundle args = new Bundle();
            long startDayMilli = args.getLong(DAY_KEY);

            Log.d("jai new Thread", "populateFragment");
            Database db = new Database(contexts[0]);
            talks = db.getTalks(startDayMilli);
            return null;
        }

        @Override

        protected void onPostExecute(Void aVoid) {
            Log.d("jai done Thread", "populateFragment");
            dayAdapter = new DayRecyclerView(talks);
            dayRecyclerView.setAdapter(dayAdapter);
        }
    }
}
