package com.hoang.lvhco.assigmentnc.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hoang.lvhco.assigmentnc.R;

import java.io.IOException;
import java.util.List;

public class MapsFragment extends Fragment implements OnMapReadyCallback {
    EditText mSearchText;
    private GoogleMap mMap;

    private EditText TFaddress;
    private Button Bsearch;
    private Button Btype;
    private ImageView Bzoomin;
    private ImageView Bzoomout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment, container, false);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        final SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);

        TFaddress = (EditText) view.findViewById(R.id.TFaddress);
        Bsearch = (Button) view.findViewById(R.id.Bsearch);
        Btype = (Button) view.findViewById(R.id.Btype);
        Bzoomin =  view.findViewById(R.id.ZoomIn);
        Bzoomout = view.findViewById(R.id.zoomOut);

        Bsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText locationSearch = (EditText)view.findViewById(R.id.TFaddress);
                String location = locationSearch.getText().toString();
                List<Address> addressList = null;

                if (location != null || !location.equals("")) {
                    Geocoder geocoder = new Geocoder(getActivity());
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title(location));
//                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(15).bearing(90).tilt(40).build();
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }

            }
        });
        Bzoomin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
            }
        });
        Bzoomout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.animateCamera(CameraUpdateFactory.zoomOut());
            }
        });
        Btype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL) {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        } else
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

        });
    }


    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);


    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng fptPoly = new LatLng(16.075737, 108.169893);
        LatLng fptPolyTayNguyen = new LatLng(12.687285, 108.054364);
        LatLng fptPolyCanTho = new LatLng(10.027133, 105.757310);
        LatLng fptHCM = new LatLng(10.812026, 106.679903);
        LatLng fptHN = new LatLng(21.035876, 105.765049);
        mMap.addMarker(new MarkerOptions().position(fptPoly).title("FPT POLYTECHNIC DN"));
        mMap.addMarker(new MarkerOptions().position(fptPolyTayNguyen).title("FPT POLYTECHNIC TN"));
        mMap.addMarker(new MarkerOptions().position(fptPolyCanTho).title("FPT POLYTECHNIC CT"));
        mMap.addMarker(new MarkerOptions().position(fptHCM).title("FPT POLYTECHNIC HCM"));
        mMap.addMarker(new MarkerOptions().position(fptHN).title("FPT POLYTECHNIC HN"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(fptPoly));
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
    }
}
