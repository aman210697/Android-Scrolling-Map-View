package com.aman.mapscrollview;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    List<Location> list = new ArrayList<>();
    ViewPager viewPager;
    List<Marker> markerList= new ArrayList<>();
    ViewPagerAdapter viewPagerAdapter;
    int oldPosition=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setPageMargin(15);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        list=getLocationList();
        initMarkers();
        viewPagerAdapter= new ViewPagerAdapter(getSupportFragmentManager(),list);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                updateView(i);

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    private void updateView(int position) {
        markerList.get(position).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_selected));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(list.get(position).getLatLng(), 15));


        markerList.get(oldPosition).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_unselected));
        oldPosition=position;
    }

    private void initMarkers() {

        for (int i = 0; i < list.size(); i++) {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(list.get(i).getLatLng());
            if (i == 0)
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_selected));
            else
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_unselected));
            markerList.add(mMap.addMarker(markerOptions));
        }

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(list.get(0).getLatLng(), 15));

    }

    private List<Location> getLocationList() {
        List<Location> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new Location("location" + i, new LatLng(getCoordinate(), getCoordinate())));
        }

        return list;
    }

    private double getCoordinate() {
        return 21 + (Math.random() * (77 - 21));
    }
}
