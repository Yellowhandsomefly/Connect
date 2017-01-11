package com.ehappy.connect;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.location.LocationManager;
import android.widget.Toast;

public class Connect extends AppCompatActivity {
    public static final int LOCATION_UPDATE_MIN_DISTANCE = 10;
    public static final int LOCATION_UPDATE_MIN_TIME = 5000;
    private TextView locationDisplay;
    private LocationManager locationManger;
    String result = " ";

    double x = 1.0;
    double y = 1.0;
    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                Log.v("loca",String.format("%f, %f", location.getLatitude(), location.getLongitude()));
                x = location.getLatitude();
                y = location.getLongitude();

            } else {
                Log.v("loca","Location is null");
            }
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        locationDisplay = (TextView) findViewById(R.id.result);
        locationManger = (LocationManager)getSystemService(LOCATION_SERVICE);
        Intent intent = this.getIntent();
        result = intent.getStringExtra("result");
    }

    private void getCurrentLocation() {
        boolean isGPSEnabled = locationManger.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkEnabled = locationManger.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        Location location = null;
        if (!(isGPSEnabled || isNetworkEnabled)) {
            Toast.makeText(this,"error_location_provider",Toast.LENGTH_LONG).show();
        }else {
            if (isNetworkEnabled) {
                locationManger.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, LOCATION_UPDATE_MIN_TIME, LOCATION_UPDATE_MIN_DISTANCE, mLocationListener);
                location = locationManger.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }

            if (isGPSEnabled) {
                locationManger.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_UPDATE_MIN_TIME, LOCATION_UPDATE_MIN_DISTANCE, mLocationListener);
                location = locationManger.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
        }if (location != null)
            x  = location.getLatitude();
            y = location.getLongitude();
    }

    public void quit(View view){
        Intent intent = new Intent(Connect.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void startFind(View view){
        getCurrentLocation();
        String position = "( " + Double.toString(x) + ", " + Double.toString(y) + " )";
        locationDisplay.setText(position);
        Toast.makeText(this,position,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationManger.removeUpdates(mLocationListener);
    }

}
