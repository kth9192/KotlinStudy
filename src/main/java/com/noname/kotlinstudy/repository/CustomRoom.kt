package com.noname.kotlinstudy.repository

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = arrayOf("id"))
data class CustomRoom(

    var id: String,
    var title: String?
){
}