package com.example.booksapp.Utils

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.booksapp.Components.BookDetailsCard
import com.example.booksapp.Components.TopBar
import com.example.booksapp.R
import com.example.booksapp.navigation.MainActions
import com.example.booksapp.ui.theme.typography
import com.example.booksapp.viewModel.MainViewModel

@Composable
fun BookDetailsScreen(viewModel: MainViewModel, actions: MainActions) {

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.text_bookDetails),
                action = actions
            )
        }
    ) {
        bookDetails(viewModel = viewModel)
    }
}

@Composable
fun bookDetails(viewModel: MainViewModel) {

    val context = LocalContext.current

    when (val result = viewModel.book.value) {
        DetailViewState.Empty -> Text(text = "This looks empty :(")
        is DetailViewState.Error -> Text(text = "An error has been found ${result.exception}, talk to an administrator")
        DetailViewState.Loading -> Text(text = "Loading")
        is DetailViewState.Success -> {

            val book = result.data
            LazyColumn() {
                item {
                    BookDetailsCard(book.title, book.authors, book.thumbnailUrl, book.categories)
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = stringResource(R.string.text_description),
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.primaryVariant,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = if (book.longDescription.isNullOrEmpty()) {
                            "This Book has not description"
                        } else {
                            book.longDescription
                        },
                        style = typography.body1,
                        color = MaterialTheme.colors.primaryVariant.copy(0.7F),
                        modifier = Modifier.padding(horizontal = 20.dp),
                        textAlign = TextAlign.Justify
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(40.dp))
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {
                                Toast.makeText(
                                    context,
                                    "This function is not Available",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            modifier = Modifier
                                .width(250.dp)
                                .height(65.dp)
                                .clip(shape = RoundedCornerShape(50.dp))
                        ) {
                            Text(text = "Go to the store")
                        }
                    }
                }
            }
        }
    }
}