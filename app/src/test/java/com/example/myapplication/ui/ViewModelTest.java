package com.example.myapplication.ui;

import com.example.myapplication.network.Repository;
import com.example.myapplication.ui.viewmodel.ViewModel;

import org.junit.Before;
import org.junit.Test;

public class ViewModelTest {

    ViewModel viewModel;
    private Repository repo;

    @Before
    public void setup() {
        viewModel = new ViewModel();
        this.repo = new Repository();
    }

    @Test
    public void RXTest() {

    }

}