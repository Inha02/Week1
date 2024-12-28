package com.example.week1

import android.net.Uri
import androidx.compose.animation.defaultDecayAnimationSpec
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size

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

    val scrollState = rememberScrollState()

    Column (
        modifier = Modifier
            .padding(top = 64.dp, bottom = 64.dp)
            .verticalScroll(scrollState)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        val model = ImageRequest.Builder(LocalContext.current)
            .data(imageUri)
            .size(Size.ORIGINAL)
            .crossfade(true)
            .build()

        Image(
            painter = rememberAsyncImagePainter(model),
            contentDescription = "Loaded Image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp ))
        )

        CustomDivider()

        Text(diary, modifier = Modifier.padding(8.dp))

        HorizontalDivider(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            thickness = 1.dp, // 원하는 두께 설정,
            color = Color(0xFF8F7A79) // 8F7A79 색상
        )
    }





}




@Composable
fun CustomDivider() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        // 왼쪽 선
        Divider(
            color = Color(0xff8f7a79),
            thickness = 1.dp,
            modifier = Modifier.weight(1f)
        )

        // 이미지
        Icon(
            painter = painterResource(id = R.drawable.acorn1), // 원하는 이미지 리소스
            contentDescription = "Divider Image",
            modifier = Modifier.size(36.dp) // 이미지 크기 설정
                .padding(horizontal = 8.dp),
            tint = Color(0xff8f7a79)
        )

        // 오른쪽 선
        Divider(
            color = Color(0xff8f7a79),
            thickness = 1.dp,
            modifier = Modifier.weight(1f)
        )
    }
}