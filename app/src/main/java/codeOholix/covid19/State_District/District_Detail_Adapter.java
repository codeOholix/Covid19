package codeOholix.covid19.State_District;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import codeOholix.covid19.R;

public class District_Detail_Adapter  extends RecyclerView.Adapter<District_Detail_Adapter.MyViewHolder>{

    private List<District_Detail_Attrs> list;
    private Context context;
    private int FLAG;

    @NonNull
    @Override
    public District_Detail_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_district_details, parent, false);


        return new District_Detail_Adapter.MyViewHolder(itemView);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView states,status;

        public MyViewHolder(View view) {

            super(view);

            states = view.findViewById(R.id.district);
            status = view.findViewById(R.id.district_confirm);

        }
    }

    public District_Detail_Adapter(Context context,List<District_Detail_Attrs> attrs)
    {
        this.context = context;
        this.list = new ArrayList<>();
        this.list = attrs;
    }

    @Override
    public void onBindViewHolder(@NonNull District_Detail_Adapter.MyViewHolder holder, int i) {


        final District_Detail_Attrs singleEvent = list.get(i);
        holder.states.setText(singleEvent.getDistrict());
        holder.status.setText(singleEvent.getConfirmed());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
