package com.noname.kotlinstudy.repository

import androidx.paging.PagedList
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import android.app.Application
import android.util.Log
import androidx.paging.DataSource
import com.noname.kotlinstudy.model.People
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class CustomRepository constructor(application: Application) {

    private var TAG : String = CustomRepository::class.java.simpleName
    lateinit var customDao: CustomDao
    lateinit var starDao: StarDao

    var listLiveData: LiveData<PagedList<People>>
    var myDataSource: DataSource.Factory<Int, People>
    var executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = AppDatabase.getDatabase(application)

        if (db != null) {
            customDao = db.customDao()
            starDao = db.starDao()
        }
        myDataSource = starDao.all

        listLiveData = LivePagedListBuilder(myDataSource, 20).build()
    }

    fun getAll(): LiveData<PagedList<People>> {
        return listLiveData
    }

    fun insert(people: People) {
        executorService.execute { starDao.insert(people) }
    }

    fun delete(people: People) {
        executorService.execute { starDao.delete(people) }
    }
}