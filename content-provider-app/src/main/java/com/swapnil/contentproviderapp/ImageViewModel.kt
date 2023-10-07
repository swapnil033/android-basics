package com.swapnil.contentproviderapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ImageViewModel: ViewModel() {


    var images by mutableStateOf(emptyList<ImageData>())
        private set

    fun addImages(images: List<ImageData>){
        this.images = images
    }
}