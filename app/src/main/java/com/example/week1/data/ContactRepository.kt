package com.example.week1.data

class ContactRepository(private val contactDao: ContactDao) {

    suspend fun addContact(contact:Contact){
        contactDao.upsertContact(contact)
    }

    suspend fun deleteContact(contact: Contact){
        contactDao.deleteContact(contact)
    }

}