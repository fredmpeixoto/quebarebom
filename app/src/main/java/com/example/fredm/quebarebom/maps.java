package com.example.fredm.quebarebom;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class maps extends FragmentActivity implements OnMapReadyCallback {
    private LocationManager locationManager;
    private double latitudeStr;
    private double longitudeStr;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapas);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Listener listener = new Listener();
        long tempoAtualizacao = 0;
        float distancia = 0;



        locationManager = (LocationManager)
                this.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                tempoAtualizacao, distancia, listener);
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                tempoAtualizacao, distancia, listener);

    }

    private class Listener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
             latitudeStr = location.getLatitude();
             longitudeStr = location.getLongitude();
            //    provedor.setText(location.getProvider());
            //   latitude.setText(latitudeStr);
            //  longitude.setText(longitudeStr);


        }

        @Override
        public void onStatusChanged(String provider, int status,Bundle extras) {}

        @Override
        public void onProviderEnabled(String provider) {}

        @Override
        public void onProviderDisabled(String provider) {}
    }

    @Override
    public void onMapReady(GoogleMap map) {
        Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
     /*   map.addMarker(new MarkerOptions()
                .position(new LatLng(Double.parseDouble(latitudeStr), Double.parseDouble(longitudeStr)))
                .title("Marker"));*/
    }
}

/**
 * Manipulates the map once available.
 * This callback is triggered when the map is ready to be used.
 * This is where we can add markers or lines, add listeners or move the camera. In this case,
 * we just add a marker near Sydney, Australia.
 * If Google Play services is not installed on the device, the user will be prompted to install
 * it inside the SupportMapFragment. This method will only be triggered once the user has
 * installed Google Play services and returned to the app.
 */