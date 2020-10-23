package com.example.myapplication.ui;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.example.myapplication.R;
import com.example.myapplication.model.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subscribers.TestSubscriber;

import static org.junit.Assert.*;

public class HomeActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> mainActivityActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    private HomeActivity homeActivity = null;
    List<Model> list = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        homeActivity = mainActivityActivityTestRule.getActivity();
        TestSubscriber<List<Model>> testSubscriber = new TestSubscriber<>();
    }

    @Test
    public void testLaunch() {
        View view = homeActivity.findViewById(R.id.recyclerView);
        assertNotNull(view);

    }

    @Test
    public void testList(){
        assertNotNull(homeActivity.viewModel.getAllText().getValue());
    }

    @After
    public void tearDown() throws Exception {
        homeActivity = null;
    }
}