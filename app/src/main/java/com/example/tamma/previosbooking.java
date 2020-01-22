package com.example.tamma;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class previosbooking extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    NavigationView navigationView;
    DrawerLayout drawer;
TextView txtResult;
    TextView textview1,textview2, textview3,textview4;
    String msg3,msg4,msg5,msg6;
    AppBarConfiguration mAppBarConfiguration;
    StringBuffer stringBuffer;
   @SuppressLint("SetTextI18n")
   @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previosbooking);
       FragmentManager manager=getSupportFragmentManager();
        final androidx.fragment.app.FragmentTransaction t=manager.beginTransaction();
       //final Myfragment m4=new Myfragment();
       drawer = findViewById(R.id.drawer_layout);
       CardView okk;
       Toolbar toolbar = findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);

       navigationView = (NavigationView) findViewById(R.id.nav_view);
       navigationView.setNavigationItemSelectedListener(this);
       okk=findViewById(R.id.okk);
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

       textview1 = findViewById(R.id.pickupaddress);
       textview2=findViewById(R.id.deliveraddress);
       textview3=findViewById(R.id.product);
       textview4=findViewById(R.id.Rupees);


          textview1.setText("Pickup Address: "+msg3);
       textview2.setText("Delivery Address: "+msg4);
       textview3.setText("selected product: "+msg5);
       textview4.setText("Rupees: "+msg6);
       okk.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent ntent = new Intent(previosbooking.this, signupp.class);

               ntent.putExtra("PICK", msg3);
               ntent.putExtra("DELIVER", msg4);
               ntent.putExtra("goods", msg5);
               ntent.putExtra("Rupees", msg6);
               startActivity(ntent);

           }
       });

   }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            startActivity(new Intent(previosbooking.this, previosbooking.class));
        }
        DrawerLayout drawer=(DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return  true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
