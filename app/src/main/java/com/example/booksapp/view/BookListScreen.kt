package com.example.booksapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.booksapp.Components.ItemBookList
import com.example.booksapp.Components.TextInputField
import com.example.booksapp.R
import com.example.booksapp.Utils.ViewState
import com.example.booksapp.model.BooksItem
import com.example.booksapp.navigation.MainActions
import com.example.booksapp.viewModel.MainViewModel

@ExperimentalComposeUiApi
@Composable
fun BookListScreen(viewModel: MainViewModel, actions: MainActions) {


    when (val result = viewModel.books.value) {
        ViewState.Empty -> Text(text = "This looks empty :(")
        is ViewState.Error -> Text(text = "An error has been found ${result.exception}, talk to an administrator")
        ViewState.Loading -> Text(text = "Loading")
        is ViewState.Success -> {
            BookList(result.data, actions)
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun BookList(bookList: List<BooksItem>, actions: MainActions) {

    val search = remember {
        mutableStateOf("")
    }

    val listState = rememberLazyListState()

    LazyColumn(state = listState, contentPadding = PaddingValues(vertical = 24.dp)) {
        item {
            Text(
                text = stringResource(R.string.text_title),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colors.primaryVariant,
                maxLines = 2,
                modifier = Modifier.padding(start = 16.dp, end = 24.dp, bottom = 24.dp)
            )
        }

        item {
            TextInputField(
                label = stringResource(R.string.text_search),
                value = search.value,
                onValueChanged = {
                    search.value = it
                },
            )

            Spacer(modifier = Modifier.height(60.dp))
        }

        items(bookList.filter { it.title.contains(search.value, ignoreCase = true) }) { book ->
            ItemBookList(
                book.title,
                book.authors.toString(),
                book.thumbnailUrl,
                book.categories,
                onItemClick = {
                    actions.gotoBookDetails.invoke(book.isbn)
                },
            )
        }
    }
}