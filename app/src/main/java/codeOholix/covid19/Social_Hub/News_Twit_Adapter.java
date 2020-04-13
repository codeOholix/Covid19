package codeOholix.covid19.Social_Hub;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import codeOholix.covid19.Home.Horizontal_Card_Adapter;
import codeOholix.covid19.Home.Horizontal_Card_Attr;
import codeOholix.covid19.R;
import lecho.lib.hellocharts.view.LineChartView;

public class News_Twit_Adapter extends RecyclerView.Adapter<News_Twit_Adapter.MyViewHolder>{

private String[] url = {"https://twitter.com/search?q=%23ReleaseDrKafeelkhan&src=trend_click",
        "https://www.google.com"};
private List<News_Twit_Attrs> list;
private Context context;

@NonNull
@Override
public News_Twit_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.fragment_news_twitter, parent, false);

        return new News_Twit_Adapter.MyViewHolder(itemView);
        }


public class MyViewHolder extends RecyclerView.ViewHolder {


    public WebView webView;

    public MyViewHolder(View view) {

        super(view);
        webView = view.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return true;
            }
            @Override
            public void onPageFinished(WebView view, final String url) {
            }
        });



    }
}

    public News_Twit_Adapter(Context context, List<News_Twit_Attrs> attrs)
    {
        this.context = context;
        this.list = new ArrayList<>();
        this.list = attrs;
    }

    @Override
    public void onBindViewHolder(@NonNull News_Twit_Adapter.MyViewHolder holder, int i) {

        holder.webView.loadUrl(url[i]);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

