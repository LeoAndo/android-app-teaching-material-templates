package com.example.m3composebasicapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

internal sealed class TopDestinations(
    val routeName: String,
    val icon: ImageVector,
    val label: String
) {
    object HomeRoute : TopDestinations(routeName = "home", icon = Icons.Filled.Home, label = "Home")
    object FavoriteRoute :
        TopDestinations(routeName = "favorite", icon = Icons.Filled.Favorite, label = "Favorite")

    object SearchRoute :
        TopDestinations(routeName = "search", icon = Icons.Filled.Search, label = "Search")
}

// nest Navigation.
internal sealed class SearchDestinations(
    val routeName: String
) {
    object TopRoute : SearchDestinations(routeName = "top")
    object ResultRoute : SearchDestinations(routeName = "result")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(routeName)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}