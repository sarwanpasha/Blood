package com.example.root.giveblood;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.root.SQLITE_Local_Database.Contact;
import com.example.root.SQLITE_Local_Database.DatabaseHandler;
import com.example.root.Upload_User_Detail.Upload_Detail;
import com.example.root.com.example.root.ExtraActivities.About_Us;
import com.example.root.com.example.root.ExtraActivities.Contact_Us;
import com.example.root.com.example.root.ExtraActivities.Our_Policy;
import com.example.root.com.example.root.Internet_Connection_Detector.ConnectionDetector;
import com.example.root.com.example.root.Registration.DatabaseHelperClass;
import com.example.root.com.example.root.Registration.Login;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class Select_Donor_Or_Receptor_3 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button getBlood,becomeDonor,logout,delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__donor__or__receptor_3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getBlood = (Button) findViewById(R.id.btn_Get_Blood);
        becomeDonor = (Button) findViewById(R.id.btn_donor);
        delete = (Button) findViewById(R.id.Delete);

        logout = (Button) findViewById(R.id.Logout);
        logout.setVisibility(View.INVISIBLE);
        delete.setVisibility(View.INVISIBLE);

        boolean isChecked;
        SharedPreferences settings1 = getSharedPreferences("PREFS_NAME", 0);
        isChecked = settings1.getBoolean("isChecked", false);
        if (isChecked) {
            logout.setVisibility(View.VISIBLE);
           // delete.setVisibility(View.VISIBLE);

        }
        else{
            logout.setVisibility(View.INVISIBLE);
//            delete.setVisibility(View.INVISIBLE);
           // logout.setVisibility(View.VISIBLE);
         //   delete.setVisibility(View.VISIBLE);

        }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteComplaint();

            }
        });

        getBlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
                Boolean isInternetPresent = cd.isConnectingToInternet(); // true or false
                if (isInternetPresent == true) {

                    //  Intent i = new Intent(Select_Donor_Or_Receptor_3.this, HomeActivity.class);
                    Intent i = new Intent(Select_Donor_Or_Receptor_3.this, FragmentMainActivity.class);
                    startActivity(i);
                    finish();
                } else if (isInternetPresent == false) {
                    Internetnotification();
                }

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                public static void clearUserName(Context ctx)
//                {
                    SharedPreferences.Editor editor = getSharedPreferences("PREFS_NAME",0).edit();
                    editor.clear(); //clear all stored data
                    editor.commit();

                SharedPreferences settings = getSharedPreferences("PREFS_NAME", 0);
                SharedPreferences.Editor editor1 = settings.edit();
                editor1.putBoolean("isChecked", false);
                editor1.commit();


                Intent i = new Intent(Select_Donor_Or_Receptor_3.this, Login.class);
                startActivity(i);
                finish();
           //     }
            }
        });

        becomeDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Select_Donor_Or_Receptor_3.this, Upload_Detail.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void  DeleteComplaint(){
        Parse.initialize(this, "Iiav8rG2tGa1ExUQZiuYEC0c3I54v1d29RSS1tZS", "W5GaHHTdR18tEK2SGmDgFWcdPOD1savHG4zwS4e1");

        Contact cn=new Contact();
        DatabaseHandler a= new DatabaseHandler(this);
        a.GetNAme_Bloodgroup();
        String DB_Name=null,DB_BloodGroup=null;
             DB_Name = a.name.toString();
             DB_BloodGroup = a.blood.toString();



        DatabaseHelperClass name=new DatabaseHelperClass();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("BloodBank");
        // query.whereEqualTo("Name", audioName );
        query.whereEqualTo("Donor_Name", DB_Name);
        query.whereEqualTo("Blood_Group", DB_BloodGroup);

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> invites, ParseException e) {
                if (e == null) {
                    // iterate over all messages and delete them
                    for (ParseObject invite : invites) {
                        invite.deleteInBackground();
                        Toast.makeText(getApplicationContext(), "Record Deleted Successfully", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Error in deleting", Toast.LENGTH_SHORT).show();
                }
            }
        });
        DatabaseHandler delete_Local_Table = new DatabaseHandler(this);
        delete_Local_Table.Deletetable();
        Toast.makeText(getApplicationContext(), "Local database Deleted Successfully", Toast.LENGTH_SHORT).show();

        delete.setVisibility(View.INVISIBLE);

//        Intent i = new Intent(Select_Donor_Or_Receptor_3.this, Select_Donor_Or_Receptor_3.class);
//        startActivity(i);
//        finish();
    }

    private void Internetnotification() {
        //  mp.setLooping(true);
        //mp.start();
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Internet Connectivity"); // Set Alert dialog title
        // here
        alert.setMessage("Unable to find internet connection,Please Check your connectivity");

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();

    }
    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
         //   super.onBackPressed();
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            logout.setVisibility(View.INVISIBLE);
                            delete.setVisibility(View.INVISIBLE);
                            finish();
                            //  super.onBackPressed();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.select__donor__or__receptor_3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(Select_Donor_Or_Receptor_3.this, Contact_Us.class);
            startActivity(i);
            //finish();
//            ActionBar actionBar = getActionBar();
//            actionBar.setSubtitle("mytest");
//            actionBar.setTitle("vogella.com");


            return true;
        }
//        if (id == R.id.blood_settings) {
//            return true;
//        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent i = new Intent(Select_Donor_Or_Receptor_3.this, Upload_Detail.class);
            startActivity(i);
            finish();
        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(Select_Donor_Or_Receptor_3.this, HomeActivity.class);
            startActivity(i);
            finish();

        } else if (id == R.id.nav_slideshow) {
            //our policy
            Intent i = new Intent(Select_Donor_Or_Receptor_3.this, Our_Policy.class);
            startActivity(i);
           // finish();

        } else if (id == R.id.nav_manage) {
            //about us
            Intent i = new Intent(Select_Donor_Or_Receptor_3.this, About_Us.class);
            startActivity(i);
            // finish();

        } else if (id == R.id.nav_share) {
            //contact us
            Intent i = new Intent(Select_Donor_Or_Receptor_3.this, Contact_Us.class);
            startActivity(i);
            // finish();

        } else if (id == R.id.nav_send) {
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
