package com.example.week1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week1.data.Contact
import com.example.week1.data.ContactDao
import kotlinx.coroutines.launch

class ContactViewModel(
    private val dao: ContactDao
): ViewModel(){



}