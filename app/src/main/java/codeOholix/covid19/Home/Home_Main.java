package codeOholix.covid19.Home;

import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import codeOholix.covid19.MainActivity;
import codeOholix.covid19.Offline;
import codeOholix.covid19.R;
import codeOholix.covid19.Util.Network.NetworkStateListener;
import codeOholix.covid19.Util.UI.Dots_Maker;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;

public class Home_Main extends Fragment implements NetworkStateListener.NetworkStateReceiverListener{

    private View v1;
    private boolean isConnected;
    private NetworkStateListener networkStateReceiver;

    @Override
    public void onNetworkAvailable() {
        isConnected = true;
    }

    @Override
    public void onNetworkUnavailable() {
        isConnected = false;
    }

    private String[] key;

    int overallXScroll=0;
    private int Scroll_Width;

    private LineChartData confirmed,active,recovered,deceased;

    private Horizontal_Card_Attr values;
    private List<Horizontal_Card_Attr> card_attrs;
    private List<String> cards_data;
    private RecyclerView cards;

    private String[] card_data =  new String[4];

    private Horizontal_Card_Adapter horizontal_card_adapter;


    private States_Attrs attrs;
    private TextView data_head;
    private List<States_Attrs> temp_data,states_attrs;
    private RecyclerView data;
    private String json;
    private States_Adapter states_adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v1 = inflater.inflate(R.layout.fragment_home,null);

        /*networkStateReceiver = new NetworkStateListener(getContext());
        networkStateReceiver.addListener(this);
        getContext().registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));

        MainActivity main = (MainActivity) getContext();
        Fragment myFragment = new Offline();
        main.getSupportFragmentManager().beginTransaction().replace(R.id.frameManager, myFragment).addToBackStack(null).commit();

        if (isConnected)

        else
            v1 = inflater.inflate(R.layout.activity_offline_card,null);*/

        loadJson();

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Scroll_Width = size.x;

        cards_data = new ArrayList();
        setCardValues();

        cards = v1.findViewById(R.id.cards);
        data = v1.findViewById(R.id.states_values);
        data_head = v1.findViewById(R.id.status);

        cards.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        cards.setHasFixedSize(true);
        cards.setAdapter(horizontal_card_adapter);
        cards.setOverScrollMode(View.OVER_SCROLL_NEVER);

        setDefault();

        // add pager behavior
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(cards);

        // pager indicator
        cards.addItemDecoration(new Dots_Maker());

        cards.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                overallXScroll = overallXScroll + dx;

                if (overallXScroll == 0)
                {
                    setConfirmed();
                    data_head.setText("Confirm");
                }
                if (overallXScroll == Scroll_Width)
                {
                    setActive();
                    data_head.setText("Active");
                }
                if (overallXScroll == Scroll_Width*2)
                {
                    setRecovered();
                    data_head.setText("Recovered");
                }
                if (overallXScroll == Scroll_Width*3)
                {
                    setDeaths();
                    data_head.setText("Deceased");
                }

            }
        });


        return v1;
    }

    private void setDefault()
    {
        temp_data = new ArrayList<>();
        states_attrs = new ArrayList<>();

        key = getResources().getStringArray(R.array.india_states);

        for (int i = 0; i < key.length; i++) {

            attrs = new States_Attrs();
            attrs.setStates(key[i]);


            // Setting Data According To JSON
            try {
                JSONObject obj = new JSONObject(json);
                JSONArray array = obj.getJSONArray("statewise");

                for (int j = 0; j < array.length(); j++) {
                    JSONObject data = array.getJSONObject(j);

                    if (key[i].equalsIgnoreCase(data.getString("state"))) {

                        attrs.setFLAG(1);
                        attrs.setActive(data.getString("active"));
                        attrs.setConfirmed(data.getString("confirmed"));
                        attrs.setRecovered(data.getString("recovered"));
                        attrs.setDeceased(data.getString("deaths"));
                    }
                }
            } catch (Exception e) {
                Log.d("Values ", e.toString());
            }
            temp_data.add(attrs);
        }

        states_attrs.addAll(temp_data);


        states_adapter = new States_Adapter(getContext(), states_attrs);
        states_adapter.notifyDataSetChanged();

        data.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        data.setHasFixedSize(true);
        data.setAdapter(states_adapter);
        data.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    private void setConfirmed()
    {
        temp_data = new ArrayList<>();
        states_attrs = new ArrayList<>();

        key = getResources().getStringArray(R.array.india_states);

        for (int i = 0; i < key.length; i++) {

            attrs = new States_Attrs();
            attrs.setStates(key[i]);


            // Setting Data According To JSON
            try {
                JSONObject obj = new JSONObject(json);
                JSONArray array = obj.getJSONArray("statewise");

                for (int j = 0; j < array.length(); j++) {
                    JSONObject data = array.getJSONObject(j);

                    if (key[i].equalsIgnoreCase(data.getString("state"))) {

                        attrs.setFLAG(1);
                        attrs.setActive(data.getString("active"));
                        attrs.setConfirmed(data.getString("confirmed"));
                        attrs.setRecovered(data.getString("recovered"));
                        attrs.setDeceased(data.getString("deaths"));
                    }
                }
            } catch (Exception e) {
                Log.d("Values ", e.toString());
            }
            temp_data.add(attrs);
        }

        states_attrs.addAll(temp_data);


        states_adapter = new States_Adapter(getContext(), states_attrs);
        states_adapter.notifyDataSetChanged();

        data.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        data.setHasFixedSize(true);
        data.setAdapter(states_adapter);
        data.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    private void setActive()
    {
        temp_data = new ArrayList<>();
        states_attrs = new ArrayList<>();

        key = getResources().getStringArray(R.array.india_states);

        for (int i = 0; i < key.length; i++) {

            attrs = new States_Attrs();
            attrs.setStates(key[i]);


            // Setting Data According To JSON
            try {
                JSONObject obj = new JSONObject(json);
                JSONArray array = obj.getJSONArray("statewise");

                for (int j = 0; j < array.length(); j++) {
                    JSONObject data = array.getJSONObject(j);

                    if (key[i].equalsIgnoreCase(data.getString("state"))) {

                        attrs.setFLAG(2);
                        attrs.setActive(data.getString("active"));
                    }
                }
            } catch (Exception e) {
                Log.d("Values ", e.toString());
            }
            temp_data.add(attrs);
        }

        states_attrs.addAll(temp_data);

        states_adapter = new States_Adapter(getContext(), states_attrs);
        states_adapter.notifyDataSetChanged();

        data.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        data.setHasFixedSize(true);
        data.setAdapter(states_adapter);
        data.setOverScrollMode(View.OVER_SCROLL_NEVER);

    }

    private void setRecovered()
    {
        temp_data = new ArrayList<>();
        states_attrs = new ArrayList<>();

        key = getResources().getStringArray(R.array.india_states);

        for (int i = 0; i < key.length; i++) {

            attrs = new States_Attrs();
            attrs.setStates(key[i]);


            // Setting Data According To JSON
            try {
                JSONObject obj = new JSONObject(json);
                JSONArray array = obj.getJSONArray("statewise");

                for (int j = 0; j < array.length(); j++) {
                    JSONObject data = array.getJSONObject(j);

                    if (key[i].equalsIgnoreCase(data.getString("state"))) {

                        attrs.setFLAG(3);
                        attrs.setRecovered(data.getString("recovered"));
                    }
                }
            } catch (Exception e) {
                Log.d("Values ", e.toString());
            }
            temp_data.add(attrs);
        }

        states_attrs.addAll(temp_data);



        states_adapter = new States_Adapter(getContext(), states_attrs);
        states_adapter.notifyDataSetChanged();

        data.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        data.setHasFixedSize(true);
        data.setAdapter(states_adapter);
        data.setOverScrollMode(View.OVER_SCROLL_NEVER);

    }

    private void setDeaths()
    {
        temp_data = new ArrayList<>();
        states_attrs = new ArrayList<>();

        key = getResources().getStringArray(R.array.india_states);

        for (int i = 0; i < key.length; i++) {

            attrs = new States_Attrs();
            attrs.setStates(key[i]);


            // Setting Data According To JSON
            try {
                JSONObject obj = new JSONObject(json);
                JSONArray array = obj.getJSONArray("statewise");

                for (int j = 0; j < array.length(); j++) {
                    JSONObject data = array.getJSONObject(j);

                    if (key[i].equalsIgnoreCase(data.getString("state"))) {

                        attrs.setFLAG(4);
                        attrs.setDeceased(data.getString("deaths"));
                    }
                }
            } catch (Exception e) {
                Log.d("Values ", e.toString());
            }
            temp_data.add(attrs);
        }

        states_attrs.addAll(temp_data);



        states_adapter = new States_Adapter(getContext(), states_attrs);
        states_adapter.notifyDataSetChanged();

        data.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        data.setHasFixedSize(true);
        data.setAdapter(states_adapter);
        data.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    private void setCardValues()
    {
        card_attrs = new ArrayList<>();

        // Value 1
        values = new Horizontal_Card_Attr();
        values.setCount(card_data[0]);
        values.setData(confirmed);
        values.setTitle("Confirmed");
        card_attrs.add(values);



        // Value 2
        values = new Horizontal_Card_Attr();
        values.setCount(card_data[1]);
        values.setData(active);
        values.setTitle("Active");
        card_attrs.add(values);

        // Value 3
        values = new Horizontal_Card_Attr();
        values.setCount(card_data[2]);
        values.setData(recovered);
        values.setTitle("Recovered");
        card_attrs.add(values);

        // Value 4
        values = new Horizontal_Card_Attr();
        values.setCount(card_data[3]);
        values.setData(deceased);
        values.setTitle("Deceased");
        card_attrs.add(values);

        horizontal_card_adapter = new Horizontal_Card_Adapter(getContext(),card_attrs);
        horizontal_card_adapter.notifyDataSetChanged();
    }

    private void loadJson()
    {
        SharedPreferences sharedPreferences  = getContext().getSharedPreferences("dcim",0);
        json = sharedPreferences.getString("State_Json",null);
        confirmed = new LineChartData();
        active = new LineChartData();
        deceased = new LineChartData();
        recovered = new LineChartData();

        try {
            JSONObject obj = new JSONObject(json);
            JSONArray array_for_data = obj.getJSONArray("statewise");

            // For Horizontal Card Data
            JSONObject data = array_for_data.getJSONObject(0);

            card_data[0] = data.getString("confirmed");
            card_data[1] = data.getString("active");
            card_data[2] = data.getString("recovered");
            card_data[3] = data.getString("deaths");

            // For Horizontal Data Graph

            List<PointValue> confirmed_values = new ArrayList<>();
            List<PointValue> active_values = new ArrayList<>();
            List<PointValue> recovered_values = new ArrayList<>();
            List<PointValue> deceased_values = new ArrayList<>();
            JSONArray array_for_graph =  obj.getJSONArray("cases_time_series");
            for (int i=0;i<array_for_graph.length();i++)
            {
                JSONObject array = array_for_graph.getJSONObject(i);
                int date = Integer.parseInt(array.getString("date").substring(0,2));
                String month = array.getString("date").substring(2);

                if (!(month.equalsIgnoreCase("January") && month.equalsIgnoreCase("February"))) {

                    int confirm = Integer.parseInt(array.getString("totalconfirmed"));
                    int recover = Integer.parseInt(array.getString("totalrecovered"));
                    int decease = Integer.parseInt(array.getString("totaldeceased"));
                    int activ = confirm - (recover + decease);

                    confirmed_values.add(new PointValue(i, confirm));
                    recovered_values.add(new PointValue(i, recover));
                    deceased_values.add(new PointValue(i, decease));
                    active_values.add(new PointValue(i, activ));
                }

            }

            //For Confirmed
            Line line = new Line(confirmed_values).setColor(getResources().getColor(R.color.White));
            List<Line> lines = new ArrayList<Line>();
            lines.add(line);
            confirmed.setLines(lines);

            //For Active
            Line line2 = new Line(active_values).setColor(getResources().getColor(R.color.White));
            List<Line> lines2 = new ArrayList<Line>();
            lines2.add(line2);
            active.setLines(lines2);

            //For Recovered
            Line line3 = new Line(active_values).setColor(getResources().getColor(R.color.White));
            List<Line> lines3 = new ArrayList<Line>();
            lines3.add(line3);
            recovered.setLines(lines3);

            //For Deceased
            Line line4 = new Line(active_values).setColor(getResources().getColor(R.color.White));
            List<Line> lines4 = new ArrayList<Line>();
            lines4.add(line4);
            deceased.setLines(lines4);

        }
        catch(Exception e)
        {
            Log.d("Values ",e.toString());
        }
    }
}


