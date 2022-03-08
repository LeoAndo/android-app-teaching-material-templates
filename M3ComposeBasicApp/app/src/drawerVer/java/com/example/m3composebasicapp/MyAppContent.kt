package com.example.m3composebasicapp

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.m3composebasicapp.ui.extentions.mainContentPadding
import com.example.m3composebasicapp.ui.favorite.FavoriteScreen
import com.example.m3composebasicapp.ui.theme.M3ComposeBasicAppTheme
import com.example.m3composebasicapp.ui.home.HomeScreen
import com.example.m3composebasicapp.ui.search.ResultScreen
import com.example.m3composebasicapp.ui.search.SearchScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MyAppContent(
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    scope: CoroutineScope = rememberCoroutineScope()
) {
    M3ComposeBasicAppTheme {
        var selectedItem by remember { mutableStateOf(0) }
        val items = listOf(
            TopDestinations.HomeRoute,
            TopDestinations.FavoriteRoute,
            TopDestinations.SearchRoute
        )
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                items.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                        selected = selectedItem == index,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem = index
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            },
            content = {
                Log.d("MyAppContent", "selectedItem: " + items[selectedItem].routeName)
                Box(modifier = Modifier.mainContentPadding(PaddingValues(20.dp))) {
                    MyAppNavigationGraph(startDestination = items[selectedItem].routeName)
                    FloatingActionButton(onClick = {
                        Log.d("MyAppContent", "FloatingActionButton Clicked!!")
                    }, modifier = Modifier.align(Alignment.BottomEnd)) {
                        Icon(Icons.Filled.Email, contentDescription = "Email")
                    }
                }
            }
        )
    }
}

@Composable
private fun MyAppNavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable(route = TopDestinations.HomeRoute.routeName, content = {
            HomeScreen(
                modifier = modifier,
            )
        })

        composable(route = TopDestinations.FavoriteRoute.routeName, content = {
            FavoriteScreen(modifier = modifier)
        })

        // nest navigation
        navigation(
            startDestination = SearchDestinations.TopRoute.routeName,
            route = TopDestinations.SearchRoute.routeName,
        ) {
            composable(
                route = SearchDestinations.TopRoute.routeName,
                content = {
                    SearchScreen(
                        modifier = modifier,
                        navigateToNextScreen = { query ->
                            navController.navigate(SearchDestinations.ResultRoute.withArgs(query))
                        },
                    )
                }
            )
            composable(
                route = SearchDestinations.ResultRoute.routeName + "/{query}",
                arguments = listOf(
                    navArgument("query") {
                        type = NavType.StringType
                        defaultValue = "no value given"
                        nullable = false
                    }
                ),
                content = {
                    val query = requireNotNull(it.arguments?.getString("query"))
                    Log.d(MainActivity.TAG, "query: $query")
                    ResultScreen(
                        navController = navController,
                        query = query,
                        modifier = modifier,
                    )
                }
            )
        }
    }
}