package com.example.myapplication.ui;

import android.view.View;

import androidx.lifecycle.Observer;
import androidx.test.rule.ActivityTestRule;

import com.example.myapplication.R;
import com.example.myapplication.model.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;
import io.reactivex.subscribers.TestSubscriber;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private MainActivity mainActivity = null;
    List<Model> list = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch() {
        View view = mainActivity.findViewById(R.id.recyclerView);
        assertNotNull(view);

    }

    @Test
    public void testList() {
        assertNotNull(mainActivity.viewModel.getAllText().getValue());
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}