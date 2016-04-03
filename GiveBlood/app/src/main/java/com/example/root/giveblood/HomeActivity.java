package com.example.root.giveblood;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;


//import com.makeinfo.giveblood.Fragments.AboutFragment;
//import com.makeinfo.giveblood.Fragments.Credits;
//import com.makeinfo.giveblood.Fragments.bPositive;
import com.example.root.cm.example.root.FragmentMainActivity_For_All.FragmentMainActivity_For_A_Negative;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class HomeActivity extends Activity {

    Fragment fragment  = new bPositive();
    Fragment fragment1  = new bPositive();
    Fragment fragment2 = new bPositive();
    Fragment fragment3 = new bPositive();
    Fragment fragment4 = new bPositive();
    Fragment fragment5 = new bPositive();
    Fragment fragment6 = new bPositive();
    Fragment fragment7 = new bPositive();
    Fragment about     = new AboutFragment();
    Fragment credits   = new Credits();

    Test test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("Give Blood,Save Life");
        getFragmentManager().beginTransaction().replace(R.id.fragid, about).commit();
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withDrawerWidthPx(550)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .withTranslucentStatusBar(false)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("A+").withIcon(R.drawable.logo11),
                        new PrimaryDrawerItem().withName("A-").withIcon(R.drawable.logo11),
                        new PrimaryDrawerItem().withName("AB+").withIcon(R.drawable.logo11),
                        new PrimaryDrawerItem().withName("AB-").withIcon(R.drawable.logo11),
                        new PrimaryDrawerItem().withName("B+").withIcon(R.drawable.logo11),
                        new PrimaryDrawerItem().withName("B-").withIcon(R.drawable.logo11),
                        new PrimaryDrawerItem().withName("O+").withIcon(R.drawable.logo11),
                        new PrimaryDrawerItem().withName("O-").withIcon(R.drawable.logo11),

                        new DividerDrawerItem(),

                        new SecondaryDrawerItem().withName("About"),
                        new SecondaryDrawerItem().withName("Credits"),
                         new SecondaryDrawerItem().withName("Exit")
        )
                                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                switch (i) {
                    case 0:
                        new Test(i);
                        //  getFragmentManager().beginTransaction().replace(R.id.fragid, fragment).commit();
                        Intent intent = new Intent(HomeActivity.this, FragmentMainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case 1:
                        new Test(i);
                       // getFragmentManager().beginTransaction().replace(R.id.fragid, fragment1).commit();
                        Intent intent1 = new Intent(HomeActivity.this, FragmentMainActivity.class);
                        startActivity(intent1);
                        finish();
                        break;
                    case 2:
                        new Test(i);
                        //getFragmentManager().beginTransaction().replace(R.id.fragid, fragment2).commit();
                        Intent intent2 = new Intent(HomeActivity.this, FragmentMainActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                    case 3:
                        new Test(i);
                       // getFragmentManager().beginTransaction().replace(R.id.fragid, fragment3).commit();
                        Intent intent3 = new Intent(HomeActivity.this, FragmentMainActivity.class);
                        startActivity(intent3);
                        finish();
                        break;
                    case 4:
                        new Test(i);
                       /// getFragmentManager().beginTransaction().replace(R.id.fragid, fragment4).commit();
                        Intent intent4 = new Intent(HomeActivity.this, FragmentMainActivity.class);
                        startActivity(intent4);
                        finish();
                        break;

                    case 5:
                        new Test(i);
                       // getFragmentManager().beginTransaction().replace(R.id.fragid, fragment5).commit();
                        Intent intent5 = new Intent(HomeActivity.this, FragmentMainActivity.class);
                        startActivity(intent5);
                        finish();
                        break;

                    case 6:
                        new Test(i);
                       // getFragmentManager().beginTransaction().replace(R.id.fragid, fragment6).commit();
                        Intent intent6 = new Intent(HomeActivity.this, FragmentMainActivity.class);
                        startActivity(intent6);
                        finish();
                        break;

                    case 7:
                        new Test(i);
                        //getFragmentManager().beginTransaction().replace(R.id.fragid, fragment7).commit();
                        Intent intent7 = new Intent(HomeActivity.this, FragmentMainActivity.class);
                        startActivity(intent7);
                        finish();
                        break;
                    case 9:
                        new Test(i);
                        getFragmentManager().beginTransaction().replace(R.id.fragid, about).commit();
//                        Intent intent8 = new Intent(HomeActivity.this, FragmentMainActivity.class);
//                        startActivity(intent8);
                        break;
                    case 10:
                         new Test(i);
                        getFragmentManager().beginTransaction().replace(R.id.fragid, credits).commit();
//                        Intent intent9 = new Intent(HomeActivity.this, FragmentMainActivity.class);
//                        startActivity(intent9);
                        break;
                    case 11:
                        // new Test(i);
                        finish();
                        break;

                }
                return false;
            }
        }).build();
        result.openDrawer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
