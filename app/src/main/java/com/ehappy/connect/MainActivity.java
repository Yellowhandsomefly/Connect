package com.ehappy.connect;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.support.v4.app.Fragment;

public class MainActivity extends Fragment {

    private TextView t1;
    private TextView t2;
    private TextView t3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.activity_main, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        t1 = (TextView) this.getView().findViewById(R.id.t1);
        t2 = (TextView) this.getView().findViewById(R.id.t2);
        t3 = (TextView) this.getView().findViewById(R.id.t3);

        FirebaseUser user  = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUri = user.getPhotoUrl();
            String uid = user.getUid();

            t1.setText(name);
            t2.setText(email);
            t3.setText(uid);
        }
    }
}
