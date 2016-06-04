package org.buildmlearn.mconference.schedule;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.buildmlearn.mconference.R;
import org.buildmlearn.mconference.adapters.DayRecyclerView;
import org.buildmlearn.mconference.model.TalkDetails;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DayFragment extends Fragment {


    public DayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_day, container, false);
        RecyclerView dayRecyclerView = (RecyclerView) view.findViewById(R.id.day_recycler_view);
        dayRecyclerView.setHasFixedSize(true);
        dayRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<TalkDetails> dummyTalks = new ArrayList<>(3);
        dummyTalks.add(new TalkDetails("Talk #1", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/3fe6027858667.55e61091bc63d.jpg",
                "10:00 AM - 11:00 AM", "ALbert Hall", getResources().getString(R.string.lorem)));
        dummyTalks.add(new TalkDetails("Talk #2", "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSJqpA4Kx0817SYkXx4eZKRTOKXBjZOhHIc4mIkevic2qyaqvco",
                "1:00 PM - 2:00 PM", "Nehru Hall", getResources().getString(R.string.lorem)));
        dummyTalks.add(new TalkDetails("Talk #3", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/3fe6027858667.55e61091bc63d.jpg",
                "3:00 PM - 5:00 AM", "Gandhi Hall", getResources().getString(R.string.lorem)));

        DayRecyclerView dayAdapter = new DayRecyclerView(dummyTalks);
        dayRecyclerView.setAdapter(dayAdapter);

        return view;
    }

}
