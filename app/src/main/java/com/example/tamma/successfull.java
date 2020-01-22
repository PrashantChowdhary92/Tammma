package com.example.tamma;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class successfull extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    AppBarConfiguration mAppBarConfiguration;
    CardView ok;
    NavigationView navigationView;
    DrawerLayout drawer;
    String msg3,msg4,msg5,msg6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successfull);
        ok=findViewById(R.id.okk);
        drawer = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        DrawerLayout drawer=(DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Intent receiveIntent = getIntent();
        msg3 = receiveIntent.getStringExtra("PICK");
        msg4=receiveIntent.getStringExtra("DELIVER");
        msg5=receiveIntent.getStringExtra("goods");
        msg6=receiveIntent.getStringExtra("Rupees");
        Toast.makeText(getApplicationContext(), msg3+" "+msg4+" "+msg5, Toast.LENGTH_SHORT).show();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ntent = new Intent(successfull.this, previosbooking.class);

                ntent.putExtra("PICK", msg3);
               ntent.putExtra("DELIVER", msg4);
                ntent.putExtra("goods", msg5);
                ntent.putExtra("Rupees", msg6);
                startActivity(ntent);

            }
        });

       // showStartDialog();
        }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            startActivity(new Intent(successfull.this, previosbooking.class));
        }
        DrawerLayout drawer=(DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return  true;
    }


   // private void showStartDialog() {
       // new AlertDialog.Builder(this)
               // .setTitle("BOOKING SUCCESFULLY")
               // .setMessage("Thank you !! for booking ")
              //  .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                //    @Override
                //    public void onClick(DialogInterface dialog, int which) {
                 //       dialog.dismiss();
                 //   }
               // })
              //  .create().show();

        //SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        //SharedPreferences.Editor editor = prefs.edit();
        //editor.putBoolean("firstStart", false);
        //editor.apply();
   // }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    }

