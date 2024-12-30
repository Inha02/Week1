package com.example.week1

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week1.data.Contact
import com.example.week1.data.ContactRepository
import com.example.week1.data.Graph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class EditFromGalleryViewModel(
    private val contactRepository: ContactRepository = Graph.contactRepository
) : ViewModel() {
    private val _contactState = mutableStateOf(Contact("", "", emptyList(), 0))
    val contactState: State<Contact> = _contactState

    private val _diaryState = mutableStateOf("")
    val diaryState: State<String> = _diaryState

    private val _imageUriState = mutableStateOf(Uri.EMPTY)
    val imageUriState: State<Uri> = _imageUriState

    suspend fun getContact(id: Int, index:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            contactRepository.getContactByID(id).collect { data ->
                withContext(Dispatchers.Main) {
                    _contactState.value = data
                    _diaryState.value = data.images[index].diary
                    _imageUriState.value = Uri.parse(data.images[index].imageUri)
                }
            }
        }
    }

    fun updateDiaryText(text: String) {
        _diaryState.value = text
    }

    fun updateImageUri(imageUri:Uri){
        _imageUriState.value = imageUri
    }

    fun updateContact(contact: Contact) {

        viewModelScope.launch(Dispatchers.IO) {
            contactRepository.addContact(contact)
        }

    }

    fun saveBitmapToInternalStorage(context: Context, bitmap: Bitmap, fileName: String): String {
        // 먼저 디렉터리를 확보한다.
        val folder = context.getExternalFilesDir(null)
        if (folder != null && !folder.exists()) {
            folder.mkdirs() // 디렉터리를 생성
        }

        // 이제 '파일' 객체를 생성한다.
        val file = File(folder, fileName)

        // FileOutputStream으로 파일에 쓰기
        FileOutputStream(file).use { outputStream ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        }
        return file.absolutePath // 최종적으로 저장된 파일 경로 반환
    }

    fun getBitmapFromUri(context: Context, uri: Uri): Bitmap {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.contentResolver, uri))
        } else {
            MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        }
    }
}