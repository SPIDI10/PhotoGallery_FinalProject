package com.bignerdranch.android.photogallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.photogallery.api.GalleryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "PhotoGalleryViewModel"

class PhotoGalleryViewModel : ViewModel() {
    private val photoRepository = PhotoRepository()

    private val _galleryItems: MutableStateFlow<List<GalleryItem>> =
        MutableStateFlow(emptyList())
    val galleryItems: StateFlow<List<GalleryItem>>
        get() = _galleryItems.asStateFlow()

    private var currentPage = 1 // Current page number

    init {
        loadPhotos()
    }

    fun loadPhotos() {
        viewModelScope.launch {
            try {
                val items = photoRepository.fetchPhotos(currentPage)
                _galleryItems.value += items
                currentPage++ // Increment page for next call
            } catch (ex: Exception) {
                // Handle error
            }
        }
    }
}
