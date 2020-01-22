package com.example.tamma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView TextView;
    ProgressBar pgBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView=findViewById(R.id.text_view);

        TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("com.example.dech_login_status",MODE_PRIVATE);
                String check=sharedPreferences.getString("login_status","off");
                if(check.equals("on"))
                {
                    startActivity(new Intent(MainActivity.this,signupp.class));
                }
                else {
                    Intent intent = new Intent(MainActivity.this, signupp.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


    }
}
