package codeOholix.covid19.State_District;

import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import codeOholix.covid19.Home.Horizontal_Card_Adapter;
import codeOholix.covid19.Home.Horizontal_Card_Attr;
import codeOholix.covid19.Home.States_Adapter;
import codeOholix.covid19.Home.States_Attrs;
import codeOholix.covid19.R;
import codeOholix.covid19.Util.UI.Dots_Maker;

public class State_District_Wise extends Fragment
{

        String state;

        private District_Header_Attrs values;
        private List<District_Header_Attrs> card_attrs;
        private RecyclerView cards;

        private District_Header_Adapter header_adapter;


        private TextView state_head;
        private String[] state_data = new String[4];

        private District_Detail_Attrs attrs;
        private List<District_Detail_Attrs> temp_data,states_attrs;
        private RecyclerView data;
        private String data_json, district_json;
        private District_Detail_Adapter states_adapter;
        private View v1;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            v1 = inflater.inflate(R.layout.fragment_home_district,null);
            loadJson();

            setCardValues();

            cards = v1.findViewById(R.id.cards);
            data = v1.findViewById(R.id.district_values);

            cards.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
            cards.setHasFixedSize(true);
            cards.setAdapter(header_adapter);
            cards.setOverScrollMode(View.OVER_SCROLL_NEVER);

            // pager indicator
            cards.addItemDecoration(new Dots_Maker());

            state_head = v1.findViewById(R.id.state_header);
            setDefaults();
            state_head.setText(state);

            return v1;
        }

        private void setDefaults()
        {
            temp_data = new ArrayList<>();
            states_attrs = new ArrayList<>();


            try {
                JSONArray array = new JSONArray(district_json);
                for (int i=0;i<array.length();i++)
                {
                    JSONObject states = array.getJSONObject(i);
                    //Toast.makeText(this, ""+states.getString("state"), Toast.LENGTH_SHORT).show();
                    if (state.equalsIgnoreCase(states.getString("state")))
                    {
                        JSONArray district_array = states.getJSONArray("districtData");
                        for (int j=0;j<district_array.length();j++)
                        {
                            JSONObject district = district_array.getJSONObject(j);
                            attrs = new District_Detail_Attrs(district.getString("district"),district.getString("confirmed"));

                            temp_data.add(attrs);
                        }
                    }
                }
                states_attrs.addAll(temp_data);
                states_adapter = new District_Detail_Adapter(getContext(), states_attrs);
                states_adapter.notifyDataSetChanged();

                data.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                data.setHasFixedSize(true);
                data.setAdapter(states_adapter);
                data.setOverScrollMode(View.OVER_SCROLL_NEVER);

            }
            catch (Exception e)
            {
                Log.d("Error :",e.toString());
            }
        }

        private void setCardValues()
        {
            card_attrs = new ArrayList<>();

            // Value 1
            values = new District_Header_Attrs();
            values.setCount(state_data[0]);
            values.setTitle("Confirmed");
            card_attrs.add(values);



            // Value 2
            values = new District_Header_Attrs();
            values.setCount(state_data[1]);
            values.setTitle("Active");
            card_attrs.add(values);

            // Value 3
            values = new District_Header_Attrs();
            values.setCount(state_data[2]);
            values.setTitle("Recovered");
            card_attrs.add(values);

            // Value 4
            values = new District_Header_Attrs();
            values.setCount(state_data[3]);
            values.setTitle("Deceased");
            card_attrs.add(values);

            header_adapter = new District_Header_Adapter(getContext(),card_attrs);
            header_adapter.notifyDataSetChanged();
        }

        private void loadJson()
        {

            Bundle bundle = getArguments();
            state = bundle.getString("state");

            SharedPreferences sharedPreferences  = getContext().getSharedPreferences("dcim",0);
            district_json = sharedPreferences.getString("District_Json",null);
            data_json =  sharedPreferences.getString("State_Json",null);

            // For Cards
            try {
                JSONObject json_data = new JSONObject(data_json);
                JSONArray state_json = json_data.getJSONArray("statewise");
                for (int i=0;i<state_json.length();i++)
                {
                    JSONObject states = state_json.getJSONObject(i);
                    if (state.equalsIgnoreCase(states.getString("state")))
                    {
                        state_data[0] = states.getString("confirmed");
                        state_data[1] = states.getString("active");
                        state_data[2] = states.getString("recovered");
                        state_data[3] = states.getString("deaths");
                    }
                }

            }
            catch (Exception e)
            {
                Toast.makeText(getContext(), ""+e, Toast.LENGTH_SHORT).show();
            }
        }
}


