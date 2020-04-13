package codeOholix.covid19.Health_Center;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import codeOholix.covid19.R;

public class Health_Centers extends Fragment {

    private View view;
    private String json,state;
    private List<String> centers,city,center_data;

    private RecyclerView health_center;
    private Health_Centers_Adapter health_centers_adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_centers,null);

        health_center = view.findViewById(R.id.center_loc);
        loadJson();

        health_center.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        health_center.setHasFixedSize(true);
        health_center.setAdapter(health_centers_adapter);
        health_center.setOverScrollMode(View.OVER_SCROLL_NEVER);

        return view;
    }


    private void loadJson() {

        List<String> center_data1 = new ArrayList<>();
        Bundle bundle = getArguments();
        state = bundle.getString("state");

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("dcim", 0);
        json = sharedPreferences.getString("Help Center", null);

        centers = new ArrayList<>();
        //city = new ArrayList<>();

        try {
            JSONObject object = new JSONObject(json);
            JSONObject object1 = object.getJSONObject("data");
            JSONArray array = new JSONArray(object1.getString("medicalColleges"));

            for (int i = 0; i < array.length(); i++) {

                JSONObject center_data = array.getJSONObject(i);
                if (center_data.getString("state").equalsIgnoreCase(state)) {

                    centers.add(center_data.getString("name"));

                } else if (state.equalsIgnoreCase("Andaman and Nicobar Islands")) {
                    if (center_data.getString("state").equalsIgnoreCase("A & N Islands")) {
                        centers.add(center_data.getString("name"));
                        //  city.add(center_data.getString("city"));
                    }
                }
            }
            center_data1.addAll(centers);
            health_centers_adapter = new Health_Centers_Adapter(getContext(),center_data1,city);
            health_centers_adapter.notifyDataSetChanged();

        } catch (Exception e) {
            Log.d("Values ", e.toString());
        }
    }


}
