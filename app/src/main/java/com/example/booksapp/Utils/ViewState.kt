package com.example.booksapp.Utils

import com.example.booksapp.model.BooksItem

sealed class ViewState {
    object Empty: ViewState()
    object Loading: ViewState()
    data class Success(val data: List<BooksItem>): ViewState()
    data class Error(val exception: Throwable): ViewState()
}
