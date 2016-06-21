package org.buildmlearn.mconference.conference;

import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.buildmlearn.mconference.R;
import org.buildmlearn.mconference.constant.Constants;
import org.buildmlearn.mconference.util.PlayServicesUnavailableDialogFragment;

import java.io.IOException;
import java.util.Locale;

public class Venue extends Fragment implements Constants {

    private MapView mMapView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_venue, container,
                false);
        MapsInitializer.initialize(getActivity().getApplicationContext());
        mMapView = (MapView) v.findViewById(R.id.mapView);
        TextView address = (TextView) v.findViewById(R.id.venue_detail);


        SharedPreferences sharedPref
                = getActivity().getApplicationContext().getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        String venueAddress = sharedPref.getString(VENUE_TAG, null);
        address.setText(venueAddress);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();// needed to get the map to display immediately

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity().getApplicationContext());

        GoogleMap googleMap = mMapView.getMap();


        double latitude = Double.longBitsToDouble(sharedPref.getLong(LAT, 0));
        double longitude = Double.longBitsToDouble(sharedPref.getLong(LONG, 0));
        Address returnedAddress;

        if (latitude == 0 && longitude == 0) {
            returnedAddress = getAddressFromLocation(v.getContext(), venueAddress);
            SharedPreferences.Editor editor = sharedPref.edit();

            if (returnedAddress != null) {
                editor.putLong(LAT, Double.doubleToLongBits(returnedAddress.getLatitude()));
                editor.putLong(LONG, Double.doubleToLongBits(returnedAddress.getLongitude()));

                latitude = returnedAddress.getLatitude();
                longitude = returnedAddress.getLongitude();
            }
        }

            if (status == ConnectionResult.SUCCESS) {
                // create marker
                MarkerOptions marker = new MarkerOptions().position(
                        new LatLng(latitude, longitude)).title(venueAddress);

                // Changing marker icon
                marker.icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

                // adding marker
                googleMap.addMarker(marker);
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(latitude, longitude)).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory
                        .newCameraPosition(cameraPosition));
            } else {
                DialogFragment dialogFragment = new PlayServicesUnavailableDialogFragment();
                dialogFragment.show(getActivity().getFragmentManager(), "Play Service Problem");
            }

            return v;
    }

    public Address getAddressFromLocation (Context context, String locationAddress) {
        Address address = null;
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            address = geocoder.getFromLocationName(locationAddress, 1).get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return address;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}