package com.example.myapplication.ui.viewmodel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.helper.AppConstants;
import com.example.myapplication.helper.AppUtil;
import com.example.myapplication.model.Model;
import com.example.myapplication.network.Repository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ViewModel extends androidx.lifecycle.ViewModel {

    private Repository repo;
    private MutableLiveData<List<Model>> textMessages;
    MutableLiveData<Integer> errorCode;

    public ViewModel() {
        this.repo = new Repository();
        textMessages = new MutableLiveData<>();
        errorCode = new MutableLiveData<>();
    }

    @SuppressLint("CheckResult")
    public void init() {
        repo.getAllList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(i -> {
                    errorCode.setValue(AppConstants.LOADING);
                    Log.d("lovish", "Subscribed");
                })
                .subscribe(this::onMessageReceived, this::onError);
    }

    @SuppressLint("CheckResult")
    public void initChat() {
        repo.getchatList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(i -> {
                    Log.d("lovish", "Subscribed");
                })
                .subscribe(this::onMessageReceived, this::onError);
    }

    public LiveData<List<Model>> getAllText() {
        return textMessages;
    }

    private void onMessageReceived(List<Model> textMsgs) {
        Log.d("lovish", "Msg Received");
        errorCode.setValue(AppConstants.LOADED);
        textMessages.setValue(textMsgs);
    }

    private void onError(Throwable throwable) {
        throwable.printStackTrace();
        AppUtil.log(throwable.toString());
    }


}
