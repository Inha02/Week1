package com.example.week1.data

import android.content.Context
import androidx.room.Room

object Graph {
    lateinit var database: ContactDatabase

    val contactRepository by lazy {
        ContactRepository(contactDao = database.contactDao()  )
    }

    fun provide ( context : Context){
        database = Room.databaseBuilder(context, ContactDatabase::class.java, "contactlist.db").build()
    }

}