package codeOholix.covid19.Health_Center;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import codeOholix.covid19.MainActivity;
import codeOholix.covid19.R;

public class Health_State_Adapter extends RecyclerView.Adapter<Health_State_Adapter.MyViewHolder>{

    private String[] list;
    private CardView card;
    private Context context;

    @NonNull
    @Override
    public Health_State_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_health_states, parent, false);


        return new Health_State_Adapter.MyViewHolder(itemView);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView states;
        public MyViewHolder(View view) {

            super(view);

            card = view.findViewById(R.id.center_card);
            states = view.findViewById(R.id.health_states);

        }
    }

    public Health_State_Adapter(Context context,String[] attrs)
    {
        this.context = context;
        this.list = attrs;
    }

    @Override
    public void onBindViewHolder(@NonNull final Health_State_Adapter.MyViewHolder holder, int i) {


        final String singleEvent = list[i];
        holder.states.setText(singleEvent);

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle  = new Bundle();
                bundle.putString("state",singleEvent);

                MainActivity main = (MainActivity) v.getContext();
                Fragment myFragment = new Health_Centers();
                myFragment.setArguments(bundle);
                main.getSupportFragmentManager().beginTransaction().replace(R.id.frameManager, myFragment).addToBackStack(null).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.length;
    }
}
