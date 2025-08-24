package com.faizal.newsapp.features.home.home.viewmodel.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faizal.core.domain.model.Article
import com.faizal.core.domain.usecases.news.IsArticleFavorite
import com.faizal.core.domain.usecases.news.SetFavoriteArticles
import com.faizal.newsapp.utils.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val setFavoriteArticle: SetFavoriteArticles,
    private val isArticleFavorite: IsArticleFavorite
) : ViewModel() {

    var sideEffect by mutableStateOf<UIComponent?>(null)
        private set

    var state by mutableStateOf(DetailState(isFavorite = false))
        private set

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.UpsertDeleteArticle -> {
                Log.d("DetailViewModel", "UpsertDeleteArticle event triggered")
                Log.d("DetailViewModel", "Article title: ${event.article.title}")
                Log.d("DetailViewModel", "Article URL: ${event.article.url}")
                toggleFavorite(article = event.article)
            }
            is DetailEvent.RemoveSideEffect -> {
                sideEffect = null
            }
        }
    }

    private fun toggleFavorite(article: Article) {
        viewModelScope.launch {
            val currentStatus = isArticleFavorite(article.url)
            setFavoriteArticle(article.url, !currentStatus)
            state = state.copy(isFavorite = !currentStatus)
            sideEffect = UIComponent.Toast(
                if (!currentStatus) "Article saved" else "Removed from favorites"
            )
        }
    }

    fun loadFavoriteStatus(article: Article) {
        viewModelScope.launch {
            val currentStatus = isArticleFavorite(article.url)
            state = state.copy(isFavorite = currentStatus)
        }
    }

}