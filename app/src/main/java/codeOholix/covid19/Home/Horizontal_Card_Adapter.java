package codeOholix.covid19.Home;

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
import codeOholix.covid19.R;
import lecho.lib.hellocharts.view.LineChartView;

public class Horizontal_Card_Adapter extends RecyclerView.Adapter<Horizontal_Card_Adapter.MyViewHolder>{

    private List<Horizontal_Card_Attr> list;
    private Context context;

    @NonNull
    @Override
    public Horizontal_Card_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_horizontal_cards, parent, false);


        return new Horizontal_Card_Adapter.MyViewHolder(itemView);
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
            graph = view.findViewById(R.id.chart);

        }
    }

    public Horizontal_Card_Adapter(Context context,List<Horizontal_Card_Attr> attrs)
    {
        this.context = context;
        this.list = new ArrayList<>();
        this.list = attrs;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {


        final Horizontal_Card_Attr singleEvent = list.get(i);
        holder.title.setText(singleEvent.getTitle());
        holder.count.setText(singleEvent.getCount());
        holder.graph.setLineChartData(singleEvent.getData());
        holder.graph.setInteractive(true);

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
/*
    private void scrollListener(){
        cards.setOnTouchListener(new OnSwipeTouchListener(context)
        {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();

            }
        });
    }
*/
}

