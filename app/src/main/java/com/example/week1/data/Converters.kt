package com.example.week1.data

import androidx.room.TypeConverter
import com.example.week1.ImageComponent
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromImageComponentList(list: List<ImageComponent>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toImageComponentList(data: String): List<ImageComponent> {
        val listType = object : TypeToken<List<ImageComponent>>() {}.type
        return gson.fromJson(data, listType)
    }
}