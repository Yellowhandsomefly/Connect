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
import android.view.View;
import android.widget.TextView;
import android.location.LocationManager;
import android.widget.Toast;

public class Connect extends AppCompatActivity implements LocationListener {
    private TextView locationDisplay;
    private LocationManager locationManger;
    String bestProv;
    double x = 1.0;
    double y = 1.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        locationDisplay = (TextView) findViewById(R.id.result);
        locationManger = (LocationManager)getSystemService(LOCATION_SERVICE);
        bestProv = locationManger.getBestProvider(new Criteria(), false);
    }


    public void quit(View view){
        Intent intent = new Intent(Connect.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void startFind(View view){
        bestProv = locationManger.getBestProvider(new Criteria(), false);
        String position = "( " + Double.toString(x) + ", " + Double.toString(y) + " )";
        locationDisplay.setText(position);
        Toast.makeText(this,position,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        x = location.getLatitude();
        y = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Criteria criteria = new Criteria();
        bestProv = locationManger.getBestProvider(criteria, true);
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    protected void onResume(){
        super.onResume();
        if (locationManger.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManger.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManger.requestLocationUpdates(bestProv, 1000, 1, this);
            }
        } else {
            Toast.makeText(this, "", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManger.removeUpdates(this);
        }
    }
}
