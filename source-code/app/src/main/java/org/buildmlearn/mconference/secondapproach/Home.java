package org.buildmlearn.mconference.secondapproach;

import android.os.AsyncTask;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import org.buildmlearn.mconference.R;
import org.buildmlearn.mconference.constant.Constants;

import java.util.ArrayList;

public class Home extends AppCompatActivity implements SearchView.OnQueryTextListener, Constants{

    private ArrayList<ConferenceMeta> conferences;
    private HomeRecyclerView homeAdapter;
    RecyclerView homeRecyclerView;
    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);

        bar = (ProgressBar) this.findViewById(R.id.progressBar_home);

        new populateActivity().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
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
                homeAdapter.setFilter(conferences);
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        ArrayList<ConferenceMeta> filterList = filter(conferences, query.toLowerCase());
        homeAdapter.setFilter(filterList);
        return  true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private ArrayList<ConferenceMeta> filter(ArrayList<ConferenceMeta> conferences, String query) {

        ArrayList<ConferenceMeta> filteredList = new ArrayList<>();
        for (ConferenceMeta conferenceMeta:conferences) {
            String name = conferenceMeta.getName().toLowerCase();
            String location = conferenceMeta.getVenue().toLowerCase();

            if (name.contains(query) || location.contains(query))
                filteredList.add(conferenceMeta);
        }

        return filteredList;
    }

    private class populateActivity extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute(){
            bar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                conferences = IndexParser.parseIndex();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            bar.setVisibility(View.GONE);
            homeRecyclerView = (RecyclerView) findViewById(R.id.home_recycler_view);
            homeRecyclerView.setHasFixedSize(true);
            homeRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));
            homeAdapter = new HomeRecyclerView(conferences);
            homeRecyclerView.setAdapter(homeAdapter);
        }
    }
}
