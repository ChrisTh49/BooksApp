package com.example.booksapp.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.Utils.DetailViewState
import com.example.booksapp.Utils.ViewState
import com.example.booksapp.model.BooksItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MainViewModel : ViewModel() {
    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    private val _detailViewState = MutableStateFlow<DetailViewState>(DetailViewState.Loading)

    val books = _viewState.asStateFlow()
    val book = _detailViewState.asStateFlow()

    val formatJson = Json {
        ignoreUnknownKeys = true;
        prettyPrint = true;
        isLenient = true;
    }

    fun getAllBooks(context: Context) = viewModelScope.launch {
        try {
            val booksJson = context.assets.open("books.json").bufferedReader().use {
                it.readText()
            }

            val bookList = formatJson.decodeFromString<List<BooksItem>>(booksJson)

            _viewState.value = ViewState.Success(bookList)
        } catch (e: Exception) {
            _viewState.value = ViewState.Error(e)
        }
    }

    fun getBookById(context: Context, isbnNO: String) = viewModelScope.launch {
        try {
            val booksJson = context.assets.open("books.json").bufferedReader().use {
                it.readText()
            }

            val bookList = formatJson.decodeFromString<List<BooksItem>>(booksJson)
                .filter { it.isbn.contentEquals(isbnNO) }.first()

            _detailViewState.value = DetailViewState.Success(bookList)
        } catch (e: Exception) {
            _detailViewState.value = DetailViewState.Error(e)
        }
    }
}
