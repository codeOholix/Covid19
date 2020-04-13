package codeOholix.covid19.Sym_Checker;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import codeOholix.covid19.R;

public class Sym_Checker extends Fragment implements Sym_Checker_Ques_Adapter.Ans_Pos{



    private View view;
    private RecyclerView questions;
    private Sym_Checker_Ques_Adapter adapter;
    int overallXScroll=0,Scroll_Width;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_checker,null);

        questions = view.findViewById(R.id.questions);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Scroll_Width = size.x;

        adapter = new Sym_Checker_Ques_Adapter(getContext(),Question_Choices_Sets.questions,this);
        questions.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        questions.setHasFixedSize(true);
        questions.setAdapter(adapter);
        questions.setOverScrollMode(View.OVER_SCROLL_NEVER);

        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(questions);

        questions.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                overallXScroll = overallXScroll + dx;

               /* if (overallXScroll == 0)
                    Toast.makeText(getContext(), ""+overallXScroll, Toast.LENGTH_SHORT).show();
                else if (overallXScroll == Scroll_Width)
                    Toast.makeText(getContext(), ""+overallXScroll, Toast.LENGTH_SHORT).show();
                else if (overallXScroll == Scroll_Width*2)
                    Toast.makeText(getContext(), ""+overallXScroll, Toast.LENGTH_SHORT).show();
                else if (overallXScroll == Scroll_Width*3)
                    Toast.makeText(getContext(), ""+overallXScroll, Toast.LENGTH_SHORT).show();
                else if (overallXScroll == Scroll_Width*4)
                    Toast.makeText(getContext(), ""+overallXScroll, Toast.LENGTH_SHORT).show();
                else if (overallXScroll == Scroll_Width*5)
                    Toast.makeText(getContext(), ""+overallXScroll, Toast.LENGTH_SHORT).show();
                else if (overallXScroll == Scroll_Width*6)
                    Toast.makeText(getContext(), ""+overallXScroll, Toast.LENGTH_SHORT).show();
                else if (overallXScroll == Scroll_Width*7)
                    Toast.makeText(getContext(), ""+overallXScroll, Toast.LENGTH_SHORT).show();
                else if (overallXScroll == Scroll_Width*8)
                    Toast.makeText(getContext(), ""+overallXScroll, Toast.LENGTH_SHORT).show();
                else if (overallXScroll == Scroll_Width*9)
                    Toast.makeText(getContext(), ""+overallXScroll, Toast.LENGTH_SHORT).show();
                else if (overallXScroll == Scroll_Width*10)
                    Toast.makeText(getContext(), ""+overallXScroll, Toast.LENGTH_SHORT).show();
                else if (overallXScroll == Scroll_Width*11)
                    Toast.makeText(getContext(), ""+overallXScroll, Toast.LENGTH_SHORT).show();
                else if (overallXScroll == Scroll_Width*12)
                    Toast.makeText(getContext(), ""+overallXScroll, Toast.LENGTH_SHORT).show();
                else if (overallXScroll == Scroll_Width*13)
                    Toast.makeText(getContext(), ""+overallXScroll, Toast.LENGTH_SHORT).show();*/
            }
        });

        return view;
    }

    @Override
    public void getPos(final int pos) {
        switch (pos)
        {
            case 1:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        questions.smoothScrollToPosition(pos);
                    }
                },500);
                break;
            case 2:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        questions.smoothScrollToPosition(pos);
                    }
                },500);
                break;
            case 3:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        questions.smoothScrollToPosition(pos);
                    }
                },500);
                break;
            case 4:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        questions.smoothScrollToPosition(pos);
                    }
                },500);
                break;
            case 5:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        questions.smoothScrollToPosition(pos);
                    }
                },500);
                break;
            case 6:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        questions.smoothScrollToPosition(pos);
                    }
                },500);
                break;
            case 7:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        questions.smoothScrollToPosition(pos);
                    }
                },500);
                break;
            case 8:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        questions.smoothScrollToPosition(pos);
                    }
                },500);
                break;
            case 9:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        questions.smoothScrollToPosition(pos);
                    }
                },500);
                break;
            case 10:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        questions.smoothScrollToPosition(pos);
                    }
                },500);
                break;
            case 11:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        questions.smoothScrollToPosition(pos);
                    }
                },500);
                break;
            case 12:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        questions.smoothScrollToPosition(pos);
                    }
                },500);
                break;
            case 13:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        questions.smoothScrollToPosition(pos);
                    }
                },500);
                break;
        }
    }

    @Override
    public void getAns(String ans) {
        Toast.makeText(getContext(), ""+ans, Toast.LENGTH_SHORT).show();

    }
}
