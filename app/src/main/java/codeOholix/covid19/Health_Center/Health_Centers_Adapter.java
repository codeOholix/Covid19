package codeOholix.covid19.Health_Center;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

public class Health_Centers_Adapter extends RecyclerView.Adapter<Health_Centers_Adapter.MyViewHolder>{

private List<String> center,city;
private CardView card;
private Context context;

@NonNull
@Override
public Health_Centers_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.fragment_health_centers, parent, false);


        return new Health_Centers_Adapter.MyViewHolder(itemView);
        }


public class MyViewHolder extends RecyclerView.ViewHolder {

    public TextView states;
    public MyViewHolder(View view) {

        super(view);

        card = view.findViewById(R.id.center_card);
        states = view.findViewById(R.id.health_centers);

    }
}

    public Health_Centers_Adapter(Context context, List<String> center,List<String> city)
    {
        this.context = context;
        this.center = new ArrayList<>();
        this.center = center;
        this.city = new ArrayList<>();
        this.city = city;
    }

    @Override
    public void onBindViewHolder(@NonNull final Health_Centers_Adapter.MyViewHolder holder, int i) {


        final String loc = center.get(i);
        //final String loc2 = city.get(i);

        holder.states.setText(loc);

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String urlAddress = "http://maps.google.com/maps?q="+loc;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlAddress));
                intent.setPackage("com.google.android.apps.maps");
                    context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return center.size();
    }
}
