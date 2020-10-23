package com.example.myapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.example.myapplication.model.Model;
import com.example.myapplication.R;
import com.example.myapplication.ui.adapter.Adapter;
import com.example.myapplication.ui.viewmodel.ViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter adapter;
    ViewModel viewModel;
    LinearLayoutManager linearLayoutManager;
    ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getMessages();

    }

    private void init() {
        progressBar = new ProgressDialog(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        adapter = new Adapter(MainActivity.this);
        recyclerView.setAdapter(adapter);
        viewModel.init();

    }

    private void getMessages() {

        viewModel.getAllText().observe(this, new Observer<List<Model>>() {
            @Override
            public void onChanged(List<Model> chatMessages) {
                adapter.updateList(chatMessages);

                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);

                    }

                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        if (chatMessages.size() % linearLayoutManager.findLastVisibleItemPosition() == 25) {
                            progressBar.show();
                            new Handler().postDelayed(new Runnable() {

                                @Override
                                public void run() {
                                    progressBar.dismiss();
                                }

                            }, 1000);
                        }
                    }
                });
            }
        });
    }
}