package org.buildmlearn.mconference.conference;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.buildmlearn.mconference.R;
import org.buildmlearn.mconference.activity.Schedule;

public class About extends Fragment {

    Button scheduleButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        scheduleButton = (Button) view.findViewById(R.id.schedule_button);

        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),Schedule.class);
                startActivity(i);
            }
        });

        return view;
     }
}
