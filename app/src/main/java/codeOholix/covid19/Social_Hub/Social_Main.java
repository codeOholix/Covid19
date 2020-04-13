package codeOholix.covid19.Social_Hub;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import codeOholix.covid19.R;
import codeOholix.covid19.Util.UI.Dots_Maker;

public class Social_Main  extends Fragment {

    RecyclerView.SmoothScroller smoothScroller;

    private View v1;
    private RecyclerView web_pages;
    private ImageView next,prev;
    private TextView heading;
    private int Scroll_Width=0,OVERALL_SCROLL=0;

    private News_Twit_Attrs attrs;
    private List<News_Twit_Attrs> attrsList;
    private News_Twit_Adapter news_twit_adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v1 = inflater.inflate(R.layout.activity_social_hub,container,false);

        smoothScroller = new
                LinearSmoothScroller(getContext()) {
                    @Override protected int getVerticalSnapPreference() {
                        return LinearSmoothScroller.SNAP_TO_START;
                    }
                };

        ImageView next = v1.findViewById(R.id.next);
        ImageView prev  =v1.findViewById(R.id.prev);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web_pages.smoothScrollToPosition(1);
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web_pages.smoothScrollToPosition(0);
            }
        });

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Scroll_Width = size.x;

        loadURL();
        web_pages = v1.findViewById(R.id.web_view);
        web_pages.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        web_pages.setHasFixedSize(true);
        web_pages.setAdapter(news_twit_adapter);
        web_pages.setOverScrollMode(View.OVER_SCROLL_NEVER);


        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(web_pages);

        web_pages.addItemDecoration(new Dots_Maker());

        web_pages.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                OVERALL_SCROLL = OVERALL_SCROLL + dx;

                if (OVERALL_SCROLL == 0)
                {
                    // Arrow Visibility
                }
                if (OVERALL_SCROLL == Scroll_Width)
                {
                    // Arrow Visibility
                }
            }
        });

        return v1;
        }

        private void loadURL()
        {
            attrsList = new ArrayList<>();

            // Value 1
            attrs = new News_Twit_Attrs();
            attrs.setUrl("https://twitter.com/search?q=%23ReleaseDrKafeelkhan&src=trend_click");
            attrsList.add(attrs);



            // Value 2
            attrs = new News_Twit_Attrs();
            attrs.setUrl("https://timesofindia.indiatimes.com/india/21-day-india-lockdown-heres-what-will-remain-open/articleshow/74798407.cms#_ga=2.145919988.198351298.1586336961-amp-rcIFOkG2spp8bF5xvHrvKw");
            attrsList.add(attrs);

            news_twit_adapter = new News_Twit_Adapter(getContext(),attrsList);
            news_twit_adapter.notifyDataSetChanged();
        }

    }
