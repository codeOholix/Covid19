package codeOholix.covid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import codeOholix.covid19.Health_Center.Health_Main;
import codeOholix.covid19.Home.Home_Main;
import codeOholix.covid19.Social_Hub.Social_Main;
import codeOholix.covid19.Sym_Checker.Sym_Checker;
import codeOholix.covid19.Util.Network.NetworkStateListener;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements NetworkStateListener.NetworkStateReceiverListener {

    boolean isConnected;

    @Override
    public void onNetworkAvailable() {
        loadFragment();

        if (navigation != null) {
            if (navigation.getMenu().getItem(0).isChecked())
                loadFragment(new Home_Main());
            if (navigation.getMenu().getItem(1).isChecked())
                loadFragment(new Social_Main());
            if (navigation.getMenu().getItem(3).isChecked())
                loadFragment(new Health_Main());
        }
        isConnected=true;


    }

    @Override
    public void onNetworkUnavailable() {
        loadFragment(new Offline());
        isConnected=false;
    }

    private NetworkStateListener networkStateReceiver;


    private LinearLayout header_state;
    private  BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        header_state = (LinearLayout) findViewById(R.id.network_state);


        networkStateReceiver = new NetworkStateListener(getApplicationContext());
        networkStateReceiver.addListener(this);
        this.registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));


        navigation= (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Intent intent = getIntent();
        int key = intent.getIntExtra("KEY",1);

        if (key == 3)
        {
            loadFragment(new Sym_Checker());
        }
        else
        {
            loadFragment(new Home_Main());
        }

    }

    private void loadFragment()
    {

        mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

            Fragment fragment=null;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.navigation_home:

                        if (!isConnected)
                            fragment = new Offline();
                        else
                            fragment = new Home_Main();
                        break;

                    case R.id.navigation_social:

                        if (!isConnected)
                            fragment = new Offline();
                        else
                            fragment = new Social_Main();
                        break;

                    case R.id.navigation__health_center:

                        if (!isConnected)
                            fragment = new Offline();
                        else
                            fragment = new Health_Main();
                        break;

                    case R.id.navigation_checkup:

                        if (isConnected)
                            fragment = new Sym_Checker();
                        else
                            fragment = new Sym_Checker();
                        break;

                        default:
                            if (!isConnected)
                                fragment = new Offline();
                            else
                                fragment = new Home_Main();
                            break;
                }
                return loadFragment(fragment);
            }
        };
    }

    private boolean loadFragment (Fragment frag){

        if (frag != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameManager, frag)
                    .commit();
            return true;
        }
        return false;
    }

}


