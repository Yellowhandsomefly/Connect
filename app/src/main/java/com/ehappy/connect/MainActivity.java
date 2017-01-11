package com.ehappy.connect;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import static com.ehappy.connect.R.id.connect;


public class MainActivity extends AppCompatActivity {

    private TextView t1;
    private TextView t2;
    private TextView t3;
    String result = "還未做測驗，請先做測驗";
    int requestCode  = 8888;
    private DatabaseReference mDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = (TextView) findViewById(R.id.t1);
        t2 = (TextView) findViewById(R.id.t2);
        t3 = (TextView) findViewById(R.id.t3);
        t3.setText(result);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUri = user.getPhotoUrl();
            String uid = user.getUid();

            t1.setText(name);
            t2.setText(email);
            //t3.setText(uid);
        }
    }

    Intent intent = getIntent();
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch(resultCode){//resultCode是剛剛妳A切換到B時設的resultCode
            case 8888 ://當B傳回來的Intent的requestCode 等於當初A傳出去的話
                result = data.getExtras().getString("score");
                t3.setText("測驗結果：" + result);
                break;

        }

    }

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(MainActivity.this, loginActivity.class);
        startActivity(intent);
        finish();
    }

    public void connect(View view){
        Intent intent = new Intent(MainActivity.this, Connect.class);
        intent.putExtra("result",result);
        startActivity(intent);
    }

    public void test(View view){
        Intent intent = new Intent(MainActivity.this, test.class);
        startActivityForResult(intent,requestCode);

    }
}

