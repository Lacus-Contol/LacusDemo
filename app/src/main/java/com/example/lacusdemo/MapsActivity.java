package com.example.lacusdemo;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

// Add multiple markers in Sydney and move the camera
        /*LatLng[] markers = {
                new LatLng(-34, 151),
                new LatLng(-35, 152),
                new LatLng(-36, 150),
                new LatLng(-37, 149)
        };

        for (char i = 0; i < markers.length; i++)
            mMap.addMarker(new MarkerOptions().position(markers[i]).title("Marker in Sydney"));*/

        //camera on central point markers[0]
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markers[0], 10));
    }

    @Override
    protected void onStart() {
        super.onStart();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        //addValueEventListener
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    if (i % 2 == 0) {
                        String[] markers = {
                                postSnapshot.child("0").getValue(String.class),  //Marca
                                postSnapshot.child("5").getValue(String.class),  //Status
                                postSnapshot.child("6").getValue(String.class),  //Longitud
                                postSnapshot.child("7").getValue(String.class)   //Latitud
                        };

                        float n = dataSnapshot.getChildrenCount();

                        mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(Float.valueOf(markers[2]), Float.valueOf(markers[3])))
                                .title("Marker in Sydney"));
                    }
                    i++;
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


    }
}
