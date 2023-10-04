package com.swapnil.intentpracticeapp

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ImageViewModel: ViewModel() {

    private val _imageUri = mutableStateOf<Uri?>(null)
    val imageUri: State<Uri?> = _imageUri


    fun updateImageUri(uri: Uri?){
        _imageUri.value = uri
    }
}