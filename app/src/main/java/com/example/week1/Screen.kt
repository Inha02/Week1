package com.example.week1

sealed class Screen(val title: String, val route: String){

    object Contact: Screen("Contact", "contact")
    object Gallery: Screen("Gallery", "gallery")
    object Diary: Screen("Diary", "diary")
    object Edit: Screen("Edit", "edit")
    object EditFromGallery: Screen("EditFromGallery", "editFromGallery")

}