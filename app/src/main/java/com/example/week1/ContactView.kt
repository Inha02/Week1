package com.example.week1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun ContactView(navController: NavHostController) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                containerColor = Color.Cyan,
                contentColor = Color.White,
                onClick = {navController.navigate(Screen.Gallery.route)}
            ) {

                Icon(Icons.Default.Add, "")
            }
        }
    ) { innerpadding ->
        Column (modifier = Modifier.padding(innerpadding)){

            LazyColumn (modifier = Modifier.fillMaxSize()){
                items(10){
                    Surface (
                        modifier = Modifier.padding(bottom = 8.dp),
                        shadowElevation = 4.dp
                    ){
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        ) {

                        }
                    }
                }
            }
        }
    }

}