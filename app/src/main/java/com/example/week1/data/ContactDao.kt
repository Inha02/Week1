package com.example.week1.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.week1.Screen

@Dao
abstract class ContactDao {

    @Upsert
    abstract suspend fun upsertContact(contact: Contact)

    @Delete
    abstract suspend fun deleteContact(contact: Contact)
}