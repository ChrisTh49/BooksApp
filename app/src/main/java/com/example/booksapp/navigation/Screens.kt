package com.example.booksapp.navigation

import androidx.annotation.StringRes
import com.example.booksapp.R

sealed class Screens(val route: String, @StringRes val resourceId: Int) {
    object BookList : Screens("book_list", R.string.text_bookList)
    object BookDetails : Screens("book_details", R.string.text_bookDetails)
}
