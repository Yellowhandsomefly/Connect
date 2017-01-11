package com.ehappy.connect;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Connect extends AppCompatActivity {
    public static final int LOCATION_UPDATE_MIN_DISTANCE = 10;
    public static final int LOCATION_UPDATE_MIN_TIME = 5000;
    private TextView locationDisplay;
    private TextView tl;
    private TextView d;
    private TextView ddm;
    private LocationManager locationManger;
    String result = " ";
    private DatabaseReference mDatabase;
    private FirebaseUser user;
    double x = 1.0;
    double y = 1.0;
    double md = 10000.0;
    User u;
    User mu;
    int nopeople = 1;

    Intent intent;
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
        tl = (TextView) findViewById(R.id.tl);
        d = (TextView) findViewById(R.id.dddname);
        ddm = (TextView) findViewById(R.id.ddm);
        locationManger = (LocationManager)getSystemService(LOCATION_SERVICE);
        intent = this.getIntent();
        result = intent.getStringExtra("result");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();

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
        Bundle bundle = new Bundle();
        bundle.putString("score",result);
        intent.putExtras(bundle);
        setResult(8888, intent); //requestCode需跟A.class的一樣
        finish();

    }

    public void startFind(View view){
        getCurrentLocation();
        String position = "( " + Double.toString(x) + ", " + Double.toString(y) + " )";
        locationDisplay.setText(position);
        Toast.makeText(this,position,Toast.LENGTH_LONG).show();
        if (user != null) {
            String uid = user.getUid();
            String name = user.getDisplayName();
            String email = user.getEmail();
            u = new User(name, result , email, x ,y);
            mDatabase.child("member").child(uid).setValue(u);
        }

        mDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.d("FireBaseTraining", " snapshot.getValue() = " + snapshot.getValue());
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Log.d("FireBaseTraining", " snapshot.getValue() = " + dataSnapshot1.getValue());
                        User user = dataSnapshot1.getValue(User.class);
                        if(Integer.parseInt(user.getKey()) == (0-Integer.parseInt(result))){
                            Log.d("FireBaseTraining",  ""+ (0-Integer.parseInt(result)));
                            double d = Math.pow((user.getX() - u.getX()),2) - Math.pow((user.getY() - u.getY()),2);
                            Log.d("FireBaseTraining",  "" + d);
                            if(d < md){
                                md = d;
                                mu = user;
                            }
                            nopeople = 1;
                        }
                        else {
                            nopeople = 0;
                        }
                        Log.d("FireBaseTraining", "name = " + user );
                    }
                }
                if(nopeople == 1) {
                    tl.setText(mu.userName);
                    d.setText(mu.getEmail());
                    if ((mu.getX() - u.getX()) >= 0 && (mu.getY() - u.getY()) >= 0) {
                        ddm.setText("前右方");
                    } else if ((mu.getX() - u.getX()) >= 0 && (mu.getY() - u.getY()) < 0) {
                        ddm.setText("前左方");
                    } else if ((mu.getX() - u.getX()) < 0 && (mu.getY() - u.getY()) >= 0) {
                        ddm.setText("後右方");
                    } else {
                        ddm.setText("後左方");
                    }
                }else{
                    tl.setText("找不到");
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
                Log.d("FireBaseTraining", "The read failed: " + firebaseError.getMessage());
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationManger.removeUpdates(mLocationListener);
    }

}
