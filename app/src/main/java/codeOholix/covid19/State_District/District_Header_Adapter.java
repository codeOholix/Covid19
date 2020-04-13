package codeOholix.covid19.State_District;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import codeOholix.covid19.Home.Horizontal_Card_Adapter;
import codeOholix.covid19.Home.Horizontal_Card_Attr;
import codeOholix.covid19.R;
import lecho.lib.hellocharts.view.LineChartView;

public class District_Header_Adapter extends RecyclerView.Adapter<District_Header_Adapter.MyViewHolder>{

    private List<District_Header_Attrs> list;
    private Context context;

    @NonNull
    @Override
    public District_Header_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_district_cards, parent, false);


        return new District_Header_Adapter.MyViewHolder(itemView);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public LineChartView graph;
        public TextView title,count;

        public MyViewHolder(View view) {

            super(view);

            cardView = view.findViewById(R.id.container);
            title = view.findViewById(R.id.heading);
            count = view.findViewById(R.id.count);

        }
    }

    public District_Header_Adapter(Context context,List<District_Header_Attrs> attrs)
    {
        this.context = context;
        this.list = new ArrayList<>();
        this.list = attrs;
    }

    @Override
    public void onBindViewHolder(@NonNull District_Header_Adapter.MyViewHolder holder, int i) {


        final District_Header_Attrs singleEvent = list.get(i);
        holder.title.setText(singleEvent.getTitle());
        holder.count.setText(singleEvent.getCount());

        switch (i)
        {
            case 0 :
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.card2));
                break;

            case 1 :
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.card1));
                break;

            case 2 :
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.card4));
                break;

            case 3 :
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.card3));
                break;
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}

