package com.gafitescu.lab2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class LocationActivity extends AppCompatActivity {
    private static final String TAG = "GafiLocatie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.v(TAG,"Not granted");
            return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if(location == null){
            Log.v(TAG,"Nulll");
        }
        TextView text = (TextView) findViewById(R.id.latitude);
        text.setText("Latitude: " + location.getLatitude());
        text = (TextView)findViewById(R.id.longitude);
        text.setText("Longitude: "+location.getLongitude());
        text = (TextView)findViewById(R.id.altitude);
        text.setText("Altitude: "+location.getAltitude());
        text = (TextView)findViewById(R.id.accuracy);
        text.setText("Accuracy: "+location.getAccuracy());

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            @SuppressLint("SetTextI18n")
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                TextView text = (TextView) findViewById(R.id.latitude);
                text.setText("Latitude: " + location.getLatitude());
                text = (TextView)findViewById(R.id.longitude);
                text.setText(String.format("Longitude: %s", location.getLongitude()));
                text = (TextView)findViewById(R.id.altitude);
                text.setText(String.format("Altitude: %s", location.getAltitude()));
                text = (TextView)findViewById(R.id.accuracy);
                text.setText(String.format("Accuracy: %s", location.getAccuracy()));
                Log.v(TAG,"Gps location found");
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.v(TAG,"Not granted");
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

    }
}
