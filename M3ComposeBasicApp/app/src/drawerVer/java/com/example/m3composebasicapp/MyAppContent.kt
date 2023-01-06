package com.example.m3composebasicapp

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.m3composebasicapp.ui.favorite.FavoriteScreen
import com.example.m3composebasicapp.ui.theme.M3ComposeBasicAppTheme
import com.example.m3composebasicapp.ui.home.HomeScreen
import com.example.m3composebasicapp.ui.search.ResultScreen
import com.example.m3composebasicapp.ui.search.SearchScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.activity.compose.BackHandler
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Alignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppContent() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    // icons to mimic drawer destinations
    val items = listOf(
        TopDestinations.HomeRoute,
        TopDestinations.FavoriteRoute,
        TopDestinations.SearchRoute
    )
    var selectedItem by remember { mutableStateOf(items[0]) }
    val modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    MyAppContentStateless(
        modifier = modifier,
        drawerState = drawerState,
        scope = scope,
        selectedItem = selectedItem,
        items = items,
        onClickDrawerItem = { item -> selectedItem = item }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyAppContentStateless(
    modifier: Modifier,
    drawerState: DrawerState,
    scope: CoroutineScope,
    selectedItem: TopDestinations,
    items: List<TopDestinations>,
    onClickDrawerItem: (TopDestinations) -> Unit,
) {
    BackHandler(enabled = drawerState.isOpen) {
        scope.launch {
            drawerState.close()
        }
    }

    M3ComposeBasicAppTheme {
        DismissibleNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                DismissibleDrawerSheet {
                    Spacer(Modifier.height(12.dp))
                    items.forEach { item ->
                        NavigationDrawerItem(
                            icon = { Icon(item.icon, contentDescription = item.label) },
                            label = { Text(item.label) },
                            selected = item == selectedItem,
                            onClick = {
                                scope.launch { drawerState.close() }
                                onClickDrawerItem(item)
                            },
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                    }
                }
            },
            content = {
                Box {
                    when (items.firstOrNull { it.routeName == selectedItem.routeName }) {
                        TopDestinations.HomeRoute -> HomeScreen(modifier)
                        TopDestinations.SearchRoute -> {
                            MyAppNavigationGraph(
                                startDestination = TopDestinations.SearchRoute.routeName,
                                modifier = modifier
                            )
                        }
                        TopDestinations.FavoriteRoute -> FavoriteScreen(modifier)
                        null -> Text(
                            text = "$selectedItem is unknown.",
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize()
                        )
                    }
                    FloatingActionButton(onClick = {
                        Log.d("MyAppContent", "FloatingActionButton Clicked!!")
                    }, modifier = Modifier
                        .padding(12.dp)
                        .align(Alignment.BottomEnd)) {
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
        modifier = Modifier.fillMaxSize()
    ) {
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