package com.example.week1

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week1.data.Contact
import com.example.week1.data.ContactDao
import com.example.week1.data.ContactRepository
import com.example.week1.data.Graph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContactViewModel(
    private val contactRepository: ContactRepository = Graph.contactRepository
): ViewModel(){

    private val _contractState = mutableStateOf(ContactState())
    val contactState: State<ContactState> = _contractState

    init {
        getContact()
    }

    fun addContact(contact: Contact){

        viewModelScope.launch(Dispatchers.IO) {
            contactRepository.addContact(contact)
        }

    }

    fun deleteContact(contact: Contact){

        viewModelScope.launch(Dispatchers.IO) {
            contactRepository.deleteContact(contact)
        }

    }

    private fun getContact(){
         viewModelScope.launch(Dispatchers.IO) {

             contactRepository.getAllContacts().collect{ data ->
                 withContext(Dispatchers.Main){
                        _contractState.value = _contractState.value.copy(
                            loading = false,
                            contactList = data,
                            error = null
                        )
                 }
             }

         }
    }




}