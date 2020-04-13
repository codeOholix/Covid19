package codeOholix.covid19;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;

import android.os.Bundle;

import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import codeOholix.covid19.Util.Network.AsyncResponse1;
import codeOholix.covid19.Util.Network.AsyncResponse2;
import codeOholix.covid19.Util.Network.AsyncResponse3;
import codeOholix.covid19.Util.Network.NetworkStateListener;
import codeOholix.covid19.Util.Network.Url_Json_Parsing1;
import codeOholix.covid19.Util.Network.Url_Json_Parsing2;
import codeOholix.covid19.Util.Network.Url_Json_Parsing3;

public class splash extends AppCompatActivity implements NetworkStateListener.NetworkStateReceiverListener {

    private Url_Json_Parsing1 obj;
    private Url_Json_Parsing2 obj2;
    private Url_Json_Parsing3 obj3;

    private String json_response;
    private String district_json;

    private String centers;
    private NetworkStateListener networkStateReceiver;

    private boolean isConnected;

    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //if (isConnected) {
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("dcim", 0);
            editor = sharedPreferences.edit();

            networkStateReceiver = new NetworkStateListener(getApplicationContext());
            networkStateReceiver.addListener(this);
            this.registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));


            obj = new Url_Json_Parsing1(getApplicationContext(), new AsyncResponse1() {

                @Override
                public void onSuccess(Object output) {
                    json_response = (String) output;
                    editor.putString("State_Json", json_response);
                    editor.commit();

                    Intent intent = new Intent(splash.this, MainActivity.class);
                    intent.putExtra("KEY", 1);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Exception e) {

                }
            });
            obj.execute();

            obj2 = new Url_Json_Parsing2(getApplicationContext(), new AsyncResponse2() {

                @Override
                public void onSuccess(Object output) {
                    district_json = (String) output;
                    editor.putString("District_Json", district_json);
                    editor.commit();
                }

                @Override
                public void onFailure(Exception e) {

                }
            });
            obj2.execute();

            obj3 = new Url_Json_Parsing3(getApplicationContext(), new AsyncResponse3() {

                @Override
                public void onSuccess(Object output) {
                    centers = (String) output;
                    editor.putString("Help Center", centers);
                    editor.commit();
                }

                @Override
                public void onFailure(Exception e) {

                }
            });
            obj3.execute();

            ImageView i1 = findViewById(R.id.imageView);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) i1.getLayoutParams();
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            i1.setLayoutParams(layoutParams);
        //}
    }

    @Override
    public void onNetworkAvailable() {
        setContentView(R.layout.activity_splash);
        isConnected = true;

    }

    @Override
    public void onNetworkUnavailable() {
        setContentView(R.layout.activity_offline_card);
        isConnected = false;
    }
}
