package codeOholix.covid19.Sym_Checker;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import codeOholix.covid19.R;

public class Sym_Checker_Ques_Adapter extends RecyclerView.Adapter<Sym_Checker_Ques_Adapter.MyViewHolder> implements Sym_Checker_Choice_Adapter.Question_Answer{


    private String[] questions;
    private Context context;
    private Sym_Checker_Choice_Adapter symCheckerChoiceAdapter;
    private int Scroll_Width;
    private static int pos;
    Ans_Pos listener;
    private String answer;

    public List<Answer_Sets> answer_sets;

    private static int position;

    @Override
    public void getAnswer(Object output) {
        answer = (String) output;
        listener.getAns(answer);
        listener.getPos(++position);
    }

    @NonNull
    @Override
    public Sym_Checker_Ques_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_checker_questions, parent, false);

        return new Sym_Checker_Ques_Adapter.MyViewHolder(itemView);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView ques;
        public RecyclerView choices;

        public MyViewHolder(View view) {

            super(view);

            ques = view.findViewById(R.id.ques);
            choices = view.findViewById(R.id.choices);

        }
    }

    public Sym_Checker_Ques_Adapter(Context context,String[] questions,Ans_Pos listener)
    {
        this.context = context;
        this.questions = null;
        this.questions = questions;
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull final Sym_Checker_Ques_Adapter.MyViewHolder holder, int i) {

        final String ques = questions[i];
        holder.ques.setText(ques);

        switch (i)
        {
            case 0:
                symCheckerChoiceAdapter = new Sym_Checker_Choice_Adapter(context,Question_Choices_Sets.ques_set1,this);
                holder.choices.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
                holder.choices.setHasFixedSize(true);
                holder.choices.setAdapter(symCheckerChoiceAdapter);
                holder.choices.setOverScrollMode(View.OVER_SCROLL_NEVER);
                break;
            case 1:
                symCheckerChoiceAdapter = new Sym_Checker_Choice_Adapter(context,Question_Choices_Sets.ques_set2,this);
                holder.choices.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
                holder.choices.setHasFixedSize(true);
                holder.choices.setAdapter(symCheckerChoiceAdapter);
                holder.choices.setOverScrollMode(View.OVER_SCROLL_NEVER);
                break;
            case 2:
                symCheckerChoiceAdapter = new Sym_Checker_Choice_Adapter(context,Question_Choices_Sets.ques_set3,this);
                holder.choices.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
                holder.choices.setHasFixedSize(true);
                holder.choices.setAdapter(symCheckerChoiceAdapter);
                holder.choices.setOverScrollMode(View.OVER_SCROLL_NEVER);
                break;
            case 3:
                symCheckerChoiceAdapter = new Sym_Checker_Choice_Adapter(context,Question_Choices_Sets.ques_set4,this);
                holder.choices.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
                holder.choices.setHasFixedSize(true);
                holder.choices.setAdapter(symCheckerChoiceAdapter);
                holder.choices.setOverScrollMode(View.OVER_SCROLL_NEVER);
                break;
            case 4:
                symCheckerChoiceAdapter = new Sym_Checker_Choice_Adapter(context,Question_Choices_Sets.ques_set5,this);
                holder.choices.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
                holder.choices.setHasFixedSize(true);
                holder.choices.setAdapter(symCheckerChoiceAdapter);
                holder.choices.setOverScrollMode(View.OVER_SCROLL_NEVER);
                break;
            default:
                symCheckerChoiceAdapter = new Sym_Checker_Choice_Adapter(context,Question_Choices_Sets.default_set,this);
                holder.choices.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
                holder.choices.setHasFixedSize(true);
                holder.choices.setAdapter(symCheckerChoiceAdapter);
                holder.choices.setOverScrollMode(View.OVER_SCROLL_NEVER);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return questions.length;
    }

    public interface Ans_Pos
    {
        void getPos(int pos);
        void getAns(String ans);
    }
}
