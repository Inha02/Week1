package com.example.week1

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.week1.data.Contact

@Composable
fun GalleryView(
    backStackEntry: NavBackStackEntry,
    navController: NavHostController,
    galleryViewModel: GalleryViewModel){




    val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: -1

    LaunchedEffect(key1 = id) {
        if (id != -1) {
            galleryViewModel.getContact(id)
        }
    }

    val contact = galleryViewModel.contactState.value
    val images = contact.images

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                containerColor = Color(0xFF7D8F69),
                contentColor = Color.White,
                onClick = {navController.navigate(Screen.Edit.route + "/${contact.id}")}
            ) {

                Icon(Icons.Default.AddPhotoAlternate, "")
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier.padding(innerPadding)
        ){
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                items(images.size)
                { index ->
                    Box(
                        modifier = Modifier
                            .aspectRatio(1f/1f)
                    ) {
                        val uri = Uri.parse(images[index].imageUri)
                        val encodedUri = Uri.encode(images[index].imageUri)
                        Image(
                            painter = rememberAsyncImagePainter(uri),
                            contentDescription = "Loaded Image",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    navController.navigate(Screen.Diary.route + "/${encodedUri}/${images[index].diary}")
                                }
                        )

                    }
                }

            }
        }
    }

}