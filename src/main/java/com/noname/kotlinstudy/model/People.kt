package com.noname.kotlinstudy.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class People(
    @PrimaryKey @SerializedName("name") var name: String,
    @SerializedName("height") var height: String,
    @SerializedName("mass") var mass: String,
    @SerializedName("birth_year") var birthYear: String,
    @SerializedName("gender") var gender: String,
    @SerializedName("homeworld") var homeworld: String,
    @SerializedName("films") var filmsfilms: List<String>
)