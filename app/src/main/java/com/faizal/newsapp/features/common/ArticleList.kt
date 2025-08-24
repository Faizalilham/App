package com.faizal.newsapp.features.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.faizal.newsapp.UIKit.EmptyScreen
import com.faizal.newsapp.domain.model.Article
import com.faizal.newsapp.ui.theme.Dimens.ExtraSmallPadding2
import com.faizal.newsapp.ui.theme.Dimens.MediumPadding1

@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onClick: (Article) -> Unit
) {
    if (articles.isEmpty()){
        EmptyScreen()
    }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MediumPadding1),
        contentPadding = PaddingValues(all = ExtraSmallPadding2)
    ) {
        items(
            count = articles.size,
        ) {
            articles[it].let { article ->
                ArticleCard(article = article, onClick = { onClick(article) })
            }
        }
    }

}

@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit,
) {

    val handlePagingResult = handlePagingResult(articles)

    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ) {
            items(
                count = articles.itemCount,
            ) { index ->
                articles[index]?.let { article ->
                    ArticleCard(
                        article = article,
                        onClick = { onClick(article) },
                    )
                }
            }
        }
    }
}


@Composable
fun handlePagingResult(articles: LazyPagingItems<Article>): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            if (articles.itemCount == 0) {
                ShimmerEffect()
                false
            } else {
                true
            }
        }

        error != null -> {
            EmptyScreen(error = error)
            false
        }

        loadState.refresh is LoadState.NotLoading && articles.itemCount == 0 -> {
            EmptyScreen()
            false
        }

        else -> {
            true
        }
    }
}

@Composable
fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(MediumPadding1)) {
        repeat(10) {
            ArticleCardShimmer(
                modifier = Modifier.padding(horizontal = MediumPadding1)
            )
        }
    }
}