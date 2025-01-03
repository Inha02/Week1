package com.example.week1

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.week1.data.Contact
import com.example.week1.data.ContactRepository
import com.example.week1.data.Graph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DiaryViewModel(
    private val contactRepository: ContactRepository = Graph.contactRepository
): ViewModel() {

    private val _contactState = mutableStateOf(Contact("","", emptyList(),0))
    val contactState : State<Contact> = _contactState

    private val _diaryState = mutableStateOf("")
    val diaryState: State<String> = _diaryState

    fun getContact(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            contactRepository.getContactByID(id).collect{
                    data->
                withContext(Dispatchers.Main){
                    _contactState.value = data
                }
            }
        }
    }

    fun updateContact(contact: Contact){

        viewModelScope.launch(Dispatchers.IO) {
            contactRepository.addContact(contact)
        }

    }


    fun deleteImage(id:Int,index: Int,navHostController: NavHostController){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val currentContact = _contactState.value
                val newImages = currentContact.images.filterIndexed { i, _ -> i != index }
                val newContact = currentContact.copy(images = newImages)
                contactRepository.addContact(newContact)
            }

            navHostController.popBackStack()
        }
    }





}