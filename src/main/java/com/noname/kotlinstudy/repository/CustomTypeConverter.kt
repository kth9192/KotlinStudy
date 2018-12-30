package com.noname.kotlinstudy.repository

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


public class CustomTypeConverter {

    @TypeConverter
    fun stringToStringModelList(data: String): List<String> {

        val listType = object : TypeToken<List<String>>() {

        }.getType()

        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun stringModelListToString(someObjects: List<String>): String {

        val gson = Gson()

        return gson.toJson(someObjects)
    }
}