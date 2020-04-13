package codeOholix.covid19.Sym_Checker;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.Handler;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import codeOholix.covid19.R;

public class Sym_Checker_Choice_Adapter extends RecyclerView.Adapter<Sym_Checker_Choice_Adapter.MyViewHolder>{

    private String[] choices;
    public ImageView nxt,prev;
    private Context context;
    private LinearLayout Viewa;
    private int Scroll_Width=0;

    private boolean isClicked=true;
    int index;
    Question_Answer mCallbacks;

    private boolean isCardSelected =false;

    @NonNull
    @Override
    public Sym_Checker_Choice_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_checker_choices, parent, false);

        return new Sym_Checker_Choice_Adapter.MyViewHolder(itemView);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView _choice;
        public CardView card;

        public MyViewHolder(View view) {

            super(view);

             card = view.findViewById(R.id._checker_choice);
            _choice = view.findViewById(R.id.choice);

        }
    }

    public Sym_Checker_Choice_Adapter(Context context,String[] choices,Question_Answer mCallbacks)
    {
        this.context = context;
        this.choices = null;
        this.choices = choices;
        this.mCallbacks = mCallbacks;
    }

    /*public Sym_Checker_Choice_Adapter(Question_Answer listener)
    {
        mCallbacks=listener;
    }*/

    @Override
    public void onBindViewHolder(@NonNull final Sym_Checker_Choice_Adapter.MyViewHolder holder,final int i) {

        final String choice = choices[i];
        holder._choice.setText(choice);

        holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.White));
        holder._choice.setTextColor(context.getResources().getColor(R.color.card2));

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.card2));
                holder._choice.setTextColor(context.getResources().getColor(R.color.White));
                mCallbacks.getAnswer(choice);

            }
        });
    }

    @Override
    public int getItemCount() {
        return choices.length;
    }

    public interface Question_Answer<String> {
        void getAnswer(String output);
    }
}
