package com.faizal.newsapp.features.home.search.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.faizal.core.domain.model.Article
import com.faizal.newsapp.UIKit.SearchBar
import com.faizal.newsapp.features.common.ArticlesList
import com.faizal.newsapp.features.home.search.viewmodel.SearchEvent
import com.faizal.newsapp.features.home.search.viewmodel.SearchState
import com.faizal.newsapp.ui.theme.Dimens.MediumPadding1
import kotlinx.coroutines.delay

@Composable
fun SearchScreen(
    state: SearchState,
    event:(SearchEvent) -> Unit,
    navigateToDetails:(Article) -> Unit
) {

    var searchQuery by remember { mutableStateOf(state.searchQuery) }

    Column(
        modifier = Modifier
            .padding(top = MediumPadding1, start = MediumPadding1, end = MediumPadding1)
            .statusBarsPadding()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { query ->
                searchQuery = query
                event(SearchEvent.UpdateSearchQuery(query))
            },
            onSearch = {
                event(SearchEvent.SearchNews)
            }
        )
        LaunchedEffect(searchQuery) {
            delay(300)
            if (searchQuery.isNotBlank()) {
                event(SearchEvent.SearchNews)
            }else{
                event(SearchEvent.ClearSearch)
            }
        }
        Spacer(modifier = Modifier.height(MediumPadding1))
        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticlesList(
                articles = articles,
                onClick = navigateToDetails
            )
        }
    }
}