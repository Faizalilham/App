package com.faizal.newsapp.features.home.search.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.faizal.newsapp.common.model.Article
import com.faizal.newsapp.common.utils.Constant
import com.faizal.newsapp.features.home.search.data.service.SearchService

data class SearchPagingDataSource(
    val searchApi : SearchService,
    val searchQuery : String,
    val source : String
): PagingSource<Int, Article>() {

    private var totalNewsCount = 0

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val searchResponse = searchApi.searchNews(searchQuery = searchQuery, sources = source,page = page, apiKey = Constant.API_KEY)
            totalNewsCount += searchResponse.articles.size
            val articles = searchResponse.articles.distinctBy { it.title }
            LoadResult.Page(
                data = articles,
                nextKey = if(totalNewsCount == searchResponse.totalResults) null else page + 1,
                prevKey = null
            )
        }catch (e : Exception){
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }

}
