package com.noname.kotlinstudy.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.noname.kotlinstudy.model.People
import com.noname.kotlinstudy.repository.CustomRepository

class CustomViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = CustomViewModelAlter::class.java.simpleName
    private var customRepository: CustomRepository = CustomRepository(application)
    lateinit var listLiveData: LiveData<PagedList<People>>

    init {
        listLiveData = customRepository.getAll()
    }

    fun insert(people: People) {
        customRepository.insert(people)
    }

    fun delete(people: People) {
        customRepository.delete(people)
    }

}