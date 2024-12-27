package com.example.week1.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.week1.ImageComponent

@Entity(tableName = "contact-table")
data class Contact(
    val name: String,
    val phone: String,
    val images: List<ImageComponent>,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)