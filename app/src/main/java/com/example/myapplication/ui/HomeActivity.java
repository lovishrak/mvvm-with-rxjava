package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.model.Model;
import com.example.myapplication.ui.adapter.Adapter;
import com.example.myapplication.ui.viewmodel.ViewModel;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter adapter;
    ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getMessages();
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        adapter = new Adapter(HomeActivity.this);
        recyclerView.setAdapter(adapter);
        viewModel.initChat();
    }

    private void getMessages() {
        viewModel.getAllText().observe(this, new Observer<List<Model>>() {
            @Override
            public void onChanged(List<Model> chatMessages) {
                adapter.updateList(chatMessages);

            }
        });
    }
}