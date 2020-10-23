package com.example.myapplication.network;

import android.util.Log;

import com.example.myapplication.model.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class Repository {


    public Observable<List<Model>> getAllList() {
        List<Model> l = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            l.add(new Model("" + i));
        }

        Observable observable = Observable.fromArray(l);


        return observable;

    }

    public Observable<List<Model>> getchatList() {
        List<Model> l = new ArrayList<>();
        l.add(new Model("lovish"));
        l.add(new Model("Aman"));
        l.add(new Model("Shubham"));
        Observable observable = Observable.fromArray(l);
        return observable;

    }
}
