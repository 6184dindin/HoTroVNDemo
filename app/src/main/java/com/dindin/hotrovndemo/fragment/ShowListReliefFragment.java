package com.dindin.hotrovndemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dindin.hotrovndemo.Poco;
import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.adapter.NewsAdapter;
import com.dindin.hotrovndemo.databinding.FragmentShowListReliefBinding;

import java.util.List;

public class ShowListReliefFragment extends Fragment {

    FragmentShowListReliefBinding binding;
    List<Poco> pocos;
    NewsAdapter newsAdapter;

    int key;
    public ShowListReliefFragment(List<Poco> pocoList, int key) {
        // Required empty public constructor
        this.pocos = pocoList;
        this.key = key;
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

        newsAdapter = new NewsAdapter(getContext(), pocos, key);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.rcShowListRelief.setLayoutManager(layoutManager);
        binding.rcShowListRelief.setAdapter(newsAdapter);

        return binding.getRoot();
    }
}