package app.aaman007.com.tablayout;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final int REQUEST_CALL = 1331;

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    public static Context context;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;


        viewPager = findViewById(R.id.viewPagerId);
        tabLayout = findViewById(R.id.tabLayoutId);
        drawerLayout = findViewById(R.id.drawerLayoutId);
        toolbar = findViewById(R.id.toolBarId);

        // Setting toolBar
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.nav_drawer_open,R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Setting NavigationDrawer Listeners
        NavigationView navigationView = findViewById(R.id.navigationId);
        navigationView.setNavigationItemSelectedListener(this);


        // Creating Adapter for ViewPager
        FragmentManager fragmentManager = getSupportFragmentManager();
        ViewPagerAdapter adapter = new ViewPagerAdapter(fragmentManager);

        //Adding Fragments
        adapter.AddFragment(new FoodMenuFragment(),"Menu");
        adapter.AddFragment(new DrinksFragment(),"Drinks");
        adapter.AddFragment(new TopRatedFragment(),"Top Food");

        //Setting Adapter and ViewPager
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
    // Contains without matching case
    public static boolean containsCaseInsensitive(String s, ArrayList<String> l){
        for (String string : l){
            if (string.equalsIgnoreCase(s)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        //Toast.makeText(MainActivity.this,"Please Log Out",Toast.LENGTH_SHORT).show();
        if(containsCaseInsensitive(logIn.userName,logIn.adminsID)) {
            Intent intent = new Intent(getApplicationContext(), AdminLayout.class);
            WelcomeScreen.lastActivity = 2;
            startActivity(intent);
            finish();
        }
        else {
            WelcomeScreen.lastActivity = 1;
            finish();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.cartMenuId)
        {
            drawerLayout.closeDrawers();
            Intent intent = new Intent(this,CartActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.logOutMenuId)
        {
            drawerLayout.closeDrawers();
            if(containsCaseInsensitive(logIn.userName,logIn.adminsID) == false) {
                Intent intent = new Intent(getApplicationContext(), logIn.class);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(getApplicationContext(), AdminLayout.class);
                startActivity(intent);
            }
            WelcomeScreen.lastActivity = 0;
            finish();
        }
        else if(item.getItemId() == R.id.aboutUsId)
        {
            drawerLayout.closeDrawers();
            Intent intent = new Intent(this,AboutUs.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.feedbackId)
        {
            drawerLayout.closeDrawers();
            Intent intent = new Intent(this,Feedback.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.shareMenuId)
        {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String subject = "FoodVille App";
            String text = "FoodVille is the best app for online food ordering in Sylhet city.\n\nDownload Now : app.aaman007.com.tablayout";
            intent.putExtra(Intent.EXTRA_TEXT,text);
            intent.putExtra(Intent.EXTRA_SUBJECT,subject);
            startActivity(Intent.createChooser(intent,"Share via"));
        }
        else if(item.getItemId() == R.id.accountId)
        {
            drawerLayout.closeDrawers();
            Intent intent = new Intent(MainActivity.this,MyAccount.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.settingMenuId)
        {
            drawerLayout.closeDrawers();
            Intent intent = new Intent(MainActivity.this,Settings.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.callMenuId)
        {
            drawerLayout.closeDrawers();
            makePhoneCall();
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    private void makePhoneCall()
    {
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:01723167606")));
        else
            ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.CALL_PHONE},REQUEST_CALL);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CALL)
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                makePhoneCall();
    }
}
