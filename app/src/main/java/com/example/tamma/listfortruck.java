package com.example.tamma;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import androidx.annotation.NonNull;
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

import android.util.SparseBooleanArray;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

public class listfortruck extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    CardView confirmbook;
    String ValueHolder,m1,m2;
    ListView listview;
    NavigationView navigationView;
    DrawerLayout drawer;
    Intent ntent;
    String[] goodlist={"Furniture/Home Furnishing",
            "Pharmacy/Medical/Fitness Equipment",
            "Electronics/Home Appliances",
            "Plywood/Timber",
            "Construction/Building",
            "Catering/Restaurant/Event Management",
            "Textile/Garments/Fashion Accessories",
            "House Shifting",
            "Paper/Packaging/Printed Material",
            "Ceramics/hardware",
            "Chemicals/Paints",
            "Logistics services provider/Packers and Moovers",
            "Food Product",
            "Plastics/Rubber"
    };
    SparseBooleanArray sparseBooleanArray;
    AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listfortruck);
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
        ntent = new Intent(listfortruck.this, truckpayment.class);
        Intent receiveIntent = getIntent();
        m1 = receiveIntent.getStringExtra("PICK");
        m2 = receiveIntent.getStringExtra("DELIVER");
        Toast.makeText(getApplicationContext(), m1 + " " + m2, Toast.LENGTH_SHORT).show();


        confirmbook=findViewById(R.id.confirmbook);

        listview = (ListView)findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (listfortruck.this,
                        android.R.layout.simple_list_item_multiple_choice,
                        android.R.id.text1, goodlist );

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                sparseBooleanArray = listview.getCheckedItemPositions();

                ValueHolder = "" ;

                int i = 0 ;

                while (i < sparseBooleanArray.size()) {

                    if (sparseBooleanArray.valueAt(i)) {

                        ValueHolder += goodlist [ sparseBooleanArray.keyAt(i) ] + ",";
                    }

                    i++ ;
                }

                ValueHolder = ValueHolder.replaceAll("(,)*$", "");

                Toast.makeText(listfortruck.this, "ListView Selected Values = " + ValueHolder, Toast.LENGTH_LONG).show();

            }
        });

        confirmbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ntent = new Intent(listfortruck.this, truckpayment.class);
                ntent.putExtra("PICK", m1);
                ntent.putExtra("DELIVER", m2);
                ntent.putExtra("goods", ValueHolder);
                startActivity(ntent);
            }});
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            startActivity(new Intent(listfortruck.this, previosbooking.class));
        }
        DrawerLayout drawer=(DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return  true;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.option, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                startActivity(new Intent(listfortruck.this, signupp.class));
                                Toast.makeText(listfortruck.this, "User Signed Out", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        return true;

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
