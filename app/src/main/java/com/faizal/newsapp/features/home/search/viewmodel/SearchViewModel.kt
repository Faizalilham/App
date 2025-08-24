package com.faizal.newsapp.features.home.search.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.faizal.core.domain.usecases.news.GetSavedArticles
import com.faizal.core.domain.usecases.news.SearchNews
import com.faizal.core.utils.NetworkConnectionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchNewsUseCase: SearchNews,
    private val getSavedArticles: GetSavedArticles,
    private val networkConnectionManager: NetworkConnectionManager
) : ViewModel() {

    private var _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    val isConnected = networkConnectionManager.observeNetworkConnection()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = networkConnectionManager.isConnected()
        )


    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearchQuery -> {
                _state.value = _state.value.copy(searchQuery = event.searchQuery)
            }
            is SearchEvent.SearchNews -> {
                searchNews()
            }
            is SearchEvent.ClearSearch -> {
                 _state.value = _state.value.copy(articles = null)
            }
        }
    }

    private val offlineNews = getSavedArticles()
        .map { articles ->
            PagingData.from(articles)
        }
        .distinctUntilChanged()
        .cachedIn(viewModelScope)


    private fun searchNews() {
        if (isConnected.value) {
            val articles = searchNewsUseCase(
                searchQuery = _state.value.searchQuery,
                sources = listOf("bbc-news", "abc-news", "al-jazeera-english")
            ).cachedIn(viewModelScope)

            _state.value = _state.value.copy(articles = articles)
        } else {
            val articles = offlineNews
                .map { pagingData ->
                    pagingData.filter { article ->
                        article.title.contains(_state.value.searchQuery, ignoreCase = true) &&
                                !article.isFavorite
                    }
                }

            _state.value = _state.value.copy(articles = articles)
        }
    }


}