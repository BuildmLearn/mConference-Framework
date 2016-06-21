package org.buildmlearn.mconference.conference;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.buildmlearn.mconference.R;
import org.buildmlearn.mconference.constant.Constants;

import java.net.URI;

public class About extends Fragment implements Constants {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        SharedPreferences sharedPref
                = view.getContext().getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);

        TextView name = (TextView) view.findViewById(R.id.conference_name);
        ImageView aboutImg = (ImageView) view.findViewById(R.id.about_image);
        TextView desc = (TextView) view.findViewById(R.id.conference_desc);

        String conferenceName = sharedPref.getString(NAME_TAG, null);
        String aboutImgURL = sharedPref.getString(ABOUTBG_TAG, null);
        String conferenceDesc = sharedPref.getString(DETAILS_TAG, null);

        name.setText(conferenceName);
        Picasso.with(view.getContext())
                .load(Uri.parse(aboutImgURL))
                .placeholder(R.drawable.placeholder_1)
                .error(R.drawable.placeholder_1)
                .fit().centerCrop().into(aboutImg);
        desc.setText(conferenceDesc);

        return view;
     }
}
