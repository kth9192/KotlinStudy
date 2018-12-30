package com.noname.kotlinstudy.repository;

import androidx.paging.DataSource;
import androidx.room.*;
import com.noname.kotlinstudy.model.People;

@Dao
public interface StarDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(People... people);

    @Delete
    void delete(People people);

    @Query("SELECT * from People")
    DataSource.Factory<Integer, People> getAll();
}
