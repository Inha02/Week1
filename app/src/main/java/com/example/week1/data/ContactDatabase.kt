package com.example.week1.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Contact::class],
    version = 1,
)
@TypeConverters(Converters::class)
abstract class ContactDatabase: RoomDatabase()  {

    abstract fun contactDao() : ContactDao

}