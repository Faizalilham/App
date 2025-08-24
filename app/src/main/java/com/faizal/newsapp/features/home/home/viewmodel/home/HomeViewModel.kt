package com.faizal.newsapp.features.home.home.viewmodel.home


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.faizal.newsapp.common.utils.NetworkConnectionManager
import com.faizal.newsapp.common.utils.UIComponent
import com.faizal.newsapp.domain.model.Article
import com.faizal.newsapp.domain.usecases.news.GetNews
import com.faizal.newsapp.domain.usecases.news.GetSavedArticles
import com.faizal.newsapp.features.home.home.viewmodel.detail.DetailEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsUseCase: GetNews,
    private val getSavedArticles: GetSavedArticles,
    private val networkConnectionManager: NetworkConnectionManager
): ViewModel() {

    var sideEffect by mutableStateOf<UIComponent?>(null)
        private set

    var state = mutableStateOf(HomeState())
        private set

    fun onEvent(event : HomeEvent){
        when(event){
            is HomeEvent.RemoveSideEffect -> {
                sideEffect = null
            }
        }
    }

    val isConnected = networkConnectionManager.observeNetworkConnection()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = networkConnectionManager.isConnected()
        )

    val onlineNews = getNewsUseCase(
        source = listOf("bbc-news","abc-news","al-jazeera-english")
    ).cachedIn(viewModelScope)

    private val offlineNews = getSavedArticles()
        .map { articles ->
            articles.distinctBy { it.url }
            PagingData.from(articles)
        }
        .distinctUntilChanged()
        .cachedIn(viewModelScope)

    @OptIn(ExperimentalCoroutinesApi::class)
    val news: Flow<PagingData<Article>> = isConnected
        .flatMapLatest { connected ->
            if (connected) {
                onlineNews
            } else {
                Log.d("TESTED","Load From DB")
                sideEffect = UIComponent.Toast("Loaded from local database")
                offlineNews
            }
        }
}