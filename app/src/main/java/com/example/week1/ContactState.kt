package com.example.week1

import com.example.week1.data.Contact

data class ContactState(
    val loading: Boolean = false,
    val contactList: List<Contact> = emptyList(),
    val error: String? = null
)
