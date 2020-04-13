package codeOholix.covid19.Home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import codeOholix.covid19.MainActivity;
import codeOholix.covid19.R;
import codeOholix.covid19.State_District.State_District_Wise;

public class States_Adapter extends RecyclerView.Adapter<States_Adapter.MyViewHolder>{

    private List<States_Attrs> list;
    private CardView card;
    public String stateClicked;
    private Context context;
    private int FLAG;

    @NonNull
    @Override
    public States_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_home_states, parent, false);


        return new States_Adapter.MyViewHolder(itemView);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView states,status;

        public MyViewHolder(View view) {

            super(view);

            card = view.findViewById(R.id.card_container);
            states = view.findViewById(R.id.states);
            status = view.findViewById(R.id.data_stats);

        }
    }

    public States_Adapter(Context context,List<States_Attrs> attrs)
    {
        this.context = context;
        this.list = new ArrayList<>();
        this.list = attrs;
    }

    public States_Adapter()
    {

    }

    @Override
    public void onBindViewHolder(@NonNull final States_Adapter.MyViewHolder holder, int i) {


        final States_Attrs singleEvent = list.get(i);
        //Toast.makeText(context, ""+, Toast.LENGTH_SHORT).show();

        switch (singleEvent.getFLAG())
        {
            case 1:
                holder.states.setText(singleEvent.getStates());
                holder.status.setText(singleEvent.getConfirmed());
                break;
            case 2:
                holder.states.setText(singleEvent.getStates());
                holder.status.setText(singleEvent.getActive());
                break;
            case 3:
                holder.states.setText(singleEvent.getStates());
                holder.status.setText(singleEvent.getRecovered());
                break;
            case 4:
                holder.states.setText(singleEvent.getStates());
                holder.status.setText(singleEvent.getDeceased());
                break;
                default:
                    holder.states.setText(singleEvent.getStates());
                    holder.status.setText(singleEvent.getConfirmed());
        }

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle  = new Bundle();
                bundle.putString("state",singleEvent.getStates());

                MainActivity main = (MainActivity) v.getContext();
                Fragment myFragment = new State_District_Wise();
                myFragment.setArguments(bundle);
                main.getSupportFragmentManager().beginTransaction().replace(R.id.frameManager, myFragment).addToBackStack(null).commit();

            }
        });
    }

    public String getState()
    {
        return stateClicked;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
