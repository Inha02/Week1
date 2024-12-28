package com.example.week1

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

@Composable
fun EditView(navHostController: NavHostController,
             backStackEntry: NavBackStackEntry,
             editViewModel: EditViewModel) {

    val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: -1

    LaunchedEffect(key1 = id) {
        if (id != -1) {
            editViewModel.getContact(id)
        }

    }
    val contact = editViewModel.contactState.value
    var diaryState = editViewModel.diaryState.value

    val context = LocalContext.current
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        context.packageName + ".provider", file
    )

    var ImageUri by remember { mutableStateOf<Uri>(Uri.EMPTY) }


    val ImagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { pickerUri ->
            if (pickerUri != null) {
                ImageUri = pickerUri
            }
        }
    )

    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
        ImageUri = uri
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            cameraLauncher.launch(uri)
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    Column (modifier = Modifier.padding(bottom = 32.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally)
    {

        Image(
            painter = rememberAsyncImagePainter(ImageUri),
            "",
            contentScale = ContentScale.Fit,
            modifier = Modifier.height(300.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                shape = RectangleShape,
                border = BorderStroke(1.dp, Color.LightGray),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp),
                onClick = {

                    val permissionCheckResult =
                        ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)

                    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                        cameraLauncher.launch(uri)
                    } else {
                        // Request a permission
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                }) {

                Icon(
                    Icons.Default.CameraAlt,
                    contentDescription = null,
                    tint = Color.Black
                )

            }
            Button(
                shape = RectangleShape,
                border = BorderStroke(1.dp, Color.LightGray),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp),
                onClick = {
                    ImagePickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }) {
                Icon(
                    Icons.Default.Image,
                    contentDescription = null,
                    tint = Color.Black
                )
            }

        }

        OutlinedTextField(diaryState,
            onValueChange = {
                editViewModel.updateDiaryText(it)
            }
            )

        Row(){
            Button(
                onClick = {
                    navHostController.popBackStack()
                }
            ){
                Text("취소")
            }

            Button(
                onClick = {

                    val fileName = "image_${System.currentTimeMillis()}.jpg"

                    val bitmap = editViewModel.getBitmapFromUri(context, ImageUri)
                    val path = editViewModel.saveBitmapToInternalStorage(context, bitmap, fileName)


                    val newContact = contact.copy(
                        name = contact.name,
                        id = contact.id,
                        phone = contact.phone,
                        images = contact.images + ImageComponent(imageUri = path, diary = diaryState)
                    )
                    editViewModel.updateContact(newContact)
                    navHostController.popBackStack()
                }
            ) {
                Text("저장")
            }
        }
    }


}

@SuppressLint("SimpleDateFormat")
fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        externalCacheDir      /* directory */
    )
    return image
}