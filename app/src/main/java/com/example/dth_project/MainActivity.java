package com.example.dth_project;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

//import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebViewFragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dth_project.PojoPack.DetailsPojo;
import com.example.dth_project.helper.DatabaseHandler;
import com.example.dth_project.helper.Functions;
import com.example.dth_project.helper.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import java.util.HashMap;
import java.util.Map;


import org.json.JSONArray;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener,BottomNavigationView.OnNavigationItemSelectedListener {

    private EditText ed1;
    TextView tv1;
    private Button checkbutton;
    private TextView txtEmail, txtName;
    private ImageButton noti_btn;
    ImageButton profile;
    private ImageButton about_btn;
    private ImageButton btnLogout;
    private ProgressDialog pDialog;
    private HashMap user = new HashMap<>();
    private SessionManager session;
    private DatabaseHandler db;
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private NavigationView mDrawerList;
    private ActionBarDrawerToggle toggle;

//    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        txtName = findViewById(R.id.title_name);
//        txtEmail = findViewById(R.id.title_email);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//  setSupportActionBar(toolbar);
        if (savedInstanceState == null) {
            Fragment newFragment = new Home();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.content_frame, newFragment);
            ft.addToBackStack(null);
            ft.commit();
        }

//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
////                int id = menuItem.getItemId();
////                Fragment fragment = null;
////                Bundle bundle = new Bundle();
////                if (id == R.id.receipt) {
////                    fragment = new Receipt();
////                } else if (id == R.id.cust_bal) {
////                    fragment = new Customerbal();
////                } else if (id == R.id.statement) {
////                    fragment = new Statement();
////                }
//                int id1 = item.getItemId();
//                Fragment fragment1 = null;
//                Bundle bundle = new Bundle();
//                if(id1==R.id.action_recents){
//                    fragment1=new Customerbal();
//                }
////                switch (item.getItemId()) {
////                    case R.id.action_recents:
////
////
////                        break;
////                    case R.id.action_favorites:
////                        fragment1 = new Customerbal();
//////                        Toast.makeText(MainActivity.this, "Favorites", Toast.LENGTH_SHORT).show();
////                        break;
////                    case R.id.action_nearby:
////                        fragment1 = new Statement();
////                       /* Toast.makeText(MainActivity.this, "Nearby", Toast.LENGTH_SHORT).show();*/
////                        break;
////                }
//                return true;
//            }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
       toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.left_drawer);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        profile=(ImageButton)findViewById(R.id.menu_icon);

        profile.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
   DrawerLayout drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
   drawerLayout.openDrawer(GravityCompat.START);
    }
});btnLogout = findViewById(R.id.logout);


//        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);


//        mDrawerList.setAdapter(adapter);
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
////        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
//        mDrawerList = (NavigationView) findViewById(R.id.left_drawer);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        db = new DatabaseHandler(getApplicationContext());
        user = db.getUserDetails();
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }


//        String email = (String) user.get("email");
//        txtEmail.setText(email);




        String name = (String) user.get("uname");
        txtName.setText("User Id:"+name);
//        txtName.setTextColor("#fff");
//        txtName.setHighlightColor(R.color.topnamecolor);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        init();
    }

    private void init() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });


    }

    private void logoutUser() {
        session.setLogin(false);
        // Launching the login activity
        Functions logout = new Functions();
        logout.logoutUser(getApplicationContext());
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
        finish();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.

        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        Fragment fragment = null;
        Bundle bundle = new Bundle();


       if (id == R.id.receipt) {
//           Intent i=new Intent(MainActivity.this,Sample.class);
//           startActivity(i);
            fragment = new Receipt();
        } else if (id == R.id.cust_bal) {
            fragment = new Customerbal();
        } else if (id == R.id.statement) {
            fragment = new Statement();
        }
       else if(id==R.id.home_page){
          fragment=new Home();
       }
       else if (id==R.id.new_col){
           fragment=new NewCollection();
       }
       else if (id == R.id.navigation_home) {
           fragment = new AddNewReceipt();
       } else if (id == R.id.navigation_dashboard) {

           fragment = new Statement();
       } else if (id == R.id.navigation_notifications) {
           fragment = new Customerbal();
       }
//        } else if (id == R.id.fb) {
//            bundle.putString("url", "https://www.facebook.com/androidhungerAH");
//            fragment = new WebViewFragment();
//            fragment.setArguments(bundle);
//        } else if (id == R.id.gplus) {
//            bundle.putString("url", "https://plus.google.com/+Androidhunger");
//            fragment = new WebViewFragment();
//            fragment.setArguments(bundle);
//        } else if (id == R.id.twitter) {
//            bundle.putString("url", "https://www.twitter.com/android_hunger");
//            fragment = new WebViewFragment();
//            fragment.setArguments(bundle);
//        } else if (id == R.id.github) {
//            bundle.putString("url", "https://github.com/avinashn/androidhunger.com");
//            fragment = new WebViewFragment();
//            fragment.setArguments(bundle);
//        } else if (id == R.id.youtube) {
//            bundle.putString("url", "https://www.youtube.com/androidhunger");
//            fragment = new WebViewFragment();
//            fragment.setArguments(bundle);
//        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    protected Map<String, String> getParams() {
        // Posting parameters to login url
        Map<String, String> params = new HashMap<String, String>();

        params.put("type", "2");

        return params;
    }


}





//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//}
