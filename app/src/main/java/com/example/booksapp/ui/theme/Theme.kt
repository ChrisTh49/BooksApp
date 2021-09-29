package com.example.booksapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.example.booksapp.ui.theme.Shapes

private val DarkColorPalette = darkColors(
    primary = PrimaryDark,
    primaryVariant = TextColorDark,
    secondary = Teal200,
    onSurface = CardDark,
    background = BackgroundDark,
)

private val LightColorPalette = lightColors(
    primary = Primary,
    primaryVariant = TextColor,
    secondary = Teal200,
    onSurface = Card,
    background = Background,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun BooksAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = Shapes,
        content = content
    )
}