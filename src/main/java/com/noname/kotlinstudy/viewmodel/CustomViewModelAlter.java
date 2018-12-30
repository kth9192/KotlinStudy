package com.noname.kotlinstudy.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import com.noname.kotlinstudy.model.People;
import com.noname.kotlinstudy.repository.CustomRepoAlter;
import com.noname.kotlinstudy.repository.CustomRepository;

import java.util.Objects;

public class CustomViewModelAlter extends AndroidViewModel {

    private static String TAG = CustomViewModelAlter.class.getSimpleName();
    private CustomRepository customRepo;
    private LiveData<PagedList<People>> listLiveData;

    public CustomViewModelAlter(@NonNull Application application) {
        super(application);
        this.customRepo = new CustomRepository(application);

    }

    public LiveData<PagedList<People>> getListLiveData() {

        if(listLiveData == null) {
            listLiveData = customRepo.getAll();
        }

        return listLiveData;
    }

    public void insert(People people) {customRepo.insert(people); }

    public void delete(People people){customRepo.delete(people);}
}
