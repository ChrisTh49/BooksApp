package com.example.booksapp.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.booksapp.Utils.coloredShadow
import com.example.booksapp.ui.theme.Primary
import com.example.booksapp.ui.theme.TextColor
import com.example.booksapp.ui.theme.typography
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun ItemBookList(
    title: String,
    author: String,
    thumbnailUrl: String,
    categories: List<String>,
    onItemClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .clickable(onClick = onItemClick)
            .padding(12.dp)
            .background(MaterialTheme.colors.background)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.onSurface.copy(0.6F)),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(
                    data = thumbnailUrl,
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(98.dp, 145.dp)
                    .padding(12.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column() {
                Text(
                    text = title,
                    style = typography.subtitle1,
                    color = MaterialTheme.colors.primaryVariant.copy(0.7F),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "by $author",
                    style = typography.caption,
                    color = MaterialTheme.colors.primaryVariant.copy(0.7F),
                )
                Spacer(modifier = Modifier.height(12.dp))
                FlowRow() {
                    categories.forEach {
                        ChipView(category = it)
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ChipView(category: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(color = MaterialTheme.colors.primary.copy(0.10F))
            .padding(horizontal = 12.dp, vertical = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = category,
            style = typography.caption,
            color = MaterialTheme.colors.primary,
        )
    }
}