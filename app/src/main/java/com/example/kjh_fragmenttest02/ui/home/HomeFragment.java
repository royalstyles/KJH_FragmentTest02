package com.example.kjh_fragmenttest02.ui.home;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.kjh_fragmenttest02.MySingleton;
import com.example.kjh_fragmenttest02.News;
import com.example.kjh_fragmenttest02.NewsItemClicked;
import com.example.kjh_fragmenttest02.NewsListAdapter;
import com.example.kjh_fragmenttest02.R;
import com.example.kjh_fragmenttest02.databinding.FragmentHomeBinding;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment implements NewsItemClicked {

    private FragmentHomeBinding binding;
    RecyclerView recyclerView;
    NewsListAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        Log.d(getClass().getName(), "onCreateView()");

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        View root = inflater.inflate(R.layout.fragment_home, null); // ?????? ??????????????? ????????
        recyclerView = root.findViewById(R.id.home_recylerView);
        fetch_date();

        mAdapter = new NewsListAdapter(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    private void fetch_date(){
        Log.d(getClass().getName(), "fetch_date()");

        String url = "https://newsapi.org/v2/everything?q=apple&from=" + getDate() + "&to=" + getDate() + "&sortBy=popularity&apiKey=63c99fa5e438467189add97cafe6145f";
//        String url = "https://newsapi.org/v2/top-headlines?country=in&category=general&apiKey=f25bd7cf2bf341fa8db0f6f426364335";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray newsJsonArray = response.getJSONArray("articles");
                    ArrayList<News> newsArrayList = new ArrayList<News>();
                    for (int i = 0; i < newsJsonArray.length(); i++) {
                        JSONObject newsJsonObject = newsJsonArray.getJSONObject(i);
                        News news = new News(
                                newsJsonObject.getString("title"),
                                newsJsonObject.getString("author"),
                                newsJsonObject.getString("url"),
                                newsJsonObject.getString("urlToImage")
                        );
                        Log.d(getClass().getName(), newsJsonObject.getString("author"));
                        newsArrayList.add(news);
                    }
                    mAdapter.updateNews(newsArrayList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(getClass().getName(), "onErrorResponse()");
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Log.d(getClass().getName(), "getHeaders()");
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("User-Agent", "Mozilla/5.0");
                return headers;
            }
        };
        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onDestroyView() {
        Log.d(getClass().getName(), "onDestroyView()");
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClicked(News item) {
        Log.d(getClass().getName(), "onItemClicked()");
        String url = item.url;
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(ContextCompat.getColor(getContext(), com.google.android.material.R.color.design_default_color_primary));
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(getContext(), Uri.parse(url));
    }

    // ?????? ????????? "yyyy-MM-dd hh:mm:ss"??? ???????????? ?????????
    private String getDate() {
        long now = System.currentTimeMillis();
        Date date = new Date(now - 1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String getDate = dateFormat.format(date);

        return getDate;
    }
}