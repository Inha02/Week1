package com.example.week1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.week1.ui.theme.Week1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week1Theme {
                Surface () {
                    ContactView()
                }
            }
        }
    }
}

@Composable
fun ContactView() {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                containerColor = Color.Cyan,
                contentColor = Color.White,
                onClick = {}
            ) {
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Week1Theme {
        ContactView()
    }
}