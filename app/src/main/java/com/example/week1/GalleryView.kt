package com.example.week1

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp

@Composable
fun GalleryView(){
    Column(){
        Text(text = "연락처")
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(count = 10)
            {
                Box(
                    modifier = Modifier
                        .aspectRatio(1f/1f)
                ) {

                }
            }

        }
    }
}