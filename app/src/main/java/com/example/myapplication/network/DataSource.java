package com.example.myapplication.network;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.example.myapplication.network.Repository;

import io.reactivex.Completable;
import io.reactivex.disposables.CompositeDisposable;

public class DataSource extends PageKeyedDataSource {
    Repository repo;
    CompositeDisposable disposable;
    MutableLiveData<Lifecycle.State> state;
    Completable completable = null;

    DataSource(Repository repo, CompositeDisposable disposable) {
        this.disposable = disposable;
        this.repo = repo;
        this.state = new MutableLiveData<>();
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback callback) {

        updateState(Lifecycle.State.STARTED);
        disposable.add(
                repo.getAllList().subscribe(res -> {
                })
        );
    }

    @Override
    public void loadBefore(@NonNull LoadParams params, @NonNull LoadCallback callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams params, @NonNull LoadCallback callback) {

    }

    private void updateState(Lifecycle.State state) {
        this.state.postValue(state);
    }
}
