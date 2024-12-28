package com.example.week1

import android.net.Uri
import androidx.compose.animation.defaultDecayAnimationSpec
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter

@Composable
fun DiaryView(
    backStackEntry: NavBackStackEntry,
    navHostController: NavHostController,
    diaryViewModel: DiaryViewModel
){
    val encodedUri = backStackEntry.arguments?.getString("imageUri") ?: ""
    val decodedUri = Uri.decode(encodedUri)
    val imageUri = Uri.parse(decodedUri)

    val diary = backStackEntry.arguments?.getString("diary") ?: ""

    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = rememberAsyncImagePainter(imageUri),
            contentDescription = "Loaded Image",
            contentScale = ContentScale.Fit,
            modifier = Modifier.weight(1f).fillMaxSize()
        )

        Text(diary, modifier = Modifier.weight(1f))
    }





}