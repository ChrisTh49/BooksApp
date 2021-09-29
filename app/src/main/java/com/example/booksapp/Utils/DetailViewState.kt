package com.example.booksapp.Utils

import com.example.booksapp.model.BooksItem

sealed class DetailViewState {
    object Empty: DetailViewState()
    object Loading: DetailViewState()
    data class Success(val data: BooksItem): DetailViewState()
    data class Error(val exception: Throwable): DetailViewState()
}