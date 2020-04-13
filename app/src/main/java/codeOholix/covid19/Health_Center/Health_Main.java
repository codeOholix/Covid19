package codeOholix.covid19.Health_Center;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import codeOholix.covid19.R;

public class Health_Main extends Fragment {

    private View v1;
    private Health_State_Adapter health_state_adapter;
    private RecyclerView states_view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v1 = inflater.inflate(R.layout.activity_health,null);
        states_view = v1.findViewById(R.id.healthState);
        loadStates();
        return v1;
    }

    private void loadStates()
    {
        String[] states = getContext().getResources().getStringArray(R.array.india_states);

        health_state_adapter = new Health_State_Adapter(getContext(),states);
        health_state_adapter.notifyDataSetChanged();

        states_view.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        states_view.setHasFixedSize(true);
        states_view.setAdapter(health_state_adapter);
        states_view.setOverScrollMode(View.OVER_SCROLL_NEVER);

    }
}
