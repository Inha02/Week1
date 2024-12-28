package com.example.week1.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.week1.Screen
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ContactDao {

    @Upsert
    abstract suspend fun upsertContact(contact: Contact)

    @Delete
    abstract suspend fun deleteContact(contact: Contact)

    @Query("Select * from `contact-table`")
    abstract fun getAllContacts(): Flow<List<Contact>>

    @Query("SELECT * FROM `contact-table` WHERE id = :id")
    abstract fun getContactById(id: Int): Flow<Contact>




}