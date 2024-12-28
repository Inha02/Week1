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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.week1.data.Graph
import com.example.week1.ui.theme.Week1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Graph.provide(this)
        enableEdgeToEdge()
        setContent {
            Week1Theme {
                Surface () {
                    NavView()
                }
            }
        }
    }
}



@Composable
fun NavView(){
    val navController  = rememberNavController()

    val contactViewModel: ContactViewModel = viewModel()
    val galleryViewModel: GalleryViewModel = viewModel()
    val editViewModel: EditViewModel = viewModel()
    val diaryViewModel:DiaryViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.Contact.route){
        composable(Screen.Contact.route){ ContactView(navController, contactViewModel)}
        composable(Screen.Gallery.route + "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.StringType
                }
            )) { backStackEntry ->
            GalleryView(backStackEntry, navController, galleryViewModel)  }

        composable(Screen.Diary.route + "/{imageUri}/{diary}",
            arguments = listOf(
                navArgument("imageUri"){ type = NavType.StringType},
                navArgument("diary"){type = NavType.StringType}
            )) { backStackEntry ->
            DiaryView(backStackEntry,navController, diaryViewModel)
        }

        composable(Screen.Edit.route + "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.StringType
                }
            )
            ) { backStackEntry ->
            EditView(navController, backStackEntry, editViewModel)  }
    }
}

