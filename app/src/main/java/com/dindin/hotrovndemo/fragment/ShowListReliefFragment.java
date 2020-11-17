package com.dindin.hotrovndemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dindin.hotrovndemo.News;
import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.adapter.NewsAdapter;
import com.dindin.hotrovndemo.databinding.FragmentShowListReliefBinding;

import java.util.List;

public class ShowListReliefFragment extends Fragment {

    FragmentShowListReliefBinding binding;
    List<News> news;
    NewsAdapter newsAdapter;

    int key;
    String phoneNumber;
    int field;

    public ShowListReliefFragment(List<News> newsList, int key, int field, String phoneNumber) {
        // Required empty public constructor
        this.news = newsList;
        this.key = key;
        this.field = field;
        this.phoneNumber = phoneNumber;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_show_list_relief, container, false);

        newsAdapter = new NewsAdapter(getContext(), news, key,field,phoneNumber);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.rcShowListRelief.setLayoutManager(layoutManager);
        binding.rcShowListRelief.setAdapter(newsAdapter);

        return binding.getRoot();
    }
}