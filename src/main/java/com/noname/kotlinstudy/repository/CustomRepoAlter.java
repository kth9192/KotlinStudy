package com.noname.kotlinstudy.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.noname.kotlinstudy.model.People;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomRepoAlter {

    private StarDao starDao;
    private LiveData<PagedList<People>> listLiveData;
    private ExecutorService executorService;

    public CustomRepoAlter(Application application) {
        AppDatabase db = null;

        if (db != null) {
            starDao = db.starDao();
        }
        DataSource.Factory<Integer, People> myDataSource = starDao.getAll();

        listLiveData = new LivePagedListBuilder(myDataSource, 20).build();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<PagedList<People>> getAll(){
        return listLiveData;
    }

    public void insert(People people) {
        executorService.execute(() -> starDao.insert(people));
    }

    public void delete(People people){
        executorService.execute(() -> starDao.delete(people));
    }
}
