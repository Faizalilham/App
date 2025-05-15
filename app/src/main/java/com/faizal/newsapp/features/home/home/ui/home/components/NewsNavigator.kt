package com.faizal.newsapp.features.home.home.ui.home.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.faizal.newsapp.R
import com.faizal.newsapp.common.model.Article
import com.faizal.newsapp.features.home.bookmark.ui.BookmarkScreen
import com.faizal.newsapp.features.home.bookmark.viewmodel.BookmarkViewModel
import com.faizal.newsapp.features.home.home.model.BottomNavigationItem
import com.faizal.newsapp.features.home.home.ui.detail.DetailScreen
import com.faizal.newsapp.features.home.home.ui.home.HomeScreen
import com.faizal.newsapp.features.home.home.viewmodel.detail.DetailViewModel
import com.faizal.newsapp.features.home.home.viewmodel.home.HomeViewModel
import com.faizal.newsapp.features.home.profile.ui.ProfileScreen
import com.faizal.newsapp.features.home.search.ui.SearchScreen
import com.faizal.newsapp.features.home.search.viewmodel.SearchViewModel
import com.faizal.newsapp.nvgraph.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsNavigator() {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, title = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, title = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, title = "Bookmark"),
            BottomNavigationItem(icon = R.drawable.ic_us, title = "About Us"),
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }
    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.BookmarkScreen.route -> 2
        Route.ProfileScreen.route -> 3
        else -> 0
    }

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        HomeBottomNavigation(
            items = bottomNavigationItems,
            selectedItem = selectedItem,
            onClickItem = { index ->
                when (index) {
                    0 -> navigateToTab(
                        navController = navController,
                        route = Route.HomeScreen.route
                    )

                    1 -> navigateToTab(
                        navController = navController,
                        route = Route.SearchScreen.route
                    )

                    2 -> navigateToTab(
                        navController = navController,
                        route = Route.BookmarkScreen.route
                    )
                    3 -> navigateToTab(
                        navController = navController,
                        route = Route.ProfileScreen.route
                    )
                }
            }
        )
    }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,
                    navigateToSearch = {
                        navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )
                    },
                    navigateToDetails = {article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    })
            }
            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController)
                SearchScreen(state = state, event = viewModel::onEvent,navigateToDetails = {article ->
                    navigateToDetails(
                        navController = navController,
                        article = article
                    )
                })
            }


            composable(route = Route.DetailsScreen.route) {
                val viewModel: DetailViewModel = hiltViewModel()
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { article ->
                        DetailScreen(
                            article = article,
                            event = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() },
                            sideEffect = viewModel.sideEffect
                        )
                    }

            }
            composable(route = Route.BookmarkScreen.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController)
                BookmarkScreen(
                    state = state,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }

            composable(route = Route.ProfileScreen.route) {
                OnBackClickStateSaver(navController = navController)
                ProfileScreen()
            }
        }
    }
}

@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navigateToTab(
            navController = navController,
            route = Route.HomeScreen.route
        )
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToDetails(navController: NavController, article: Article){
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}