package com.faizal.newsapp.data.remote


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.faizal.newsapp.common.utils.Mapper.toEntity
import com.faizal.newsapp.data.local.NewsDao
import com.faizal.newsapp.domain.model.Article
import kotlin.collections.distinctBy

class NewsPagingSource(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao,
    private val sources: String
) : PagingSource<Int, Article>() {

    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val newsResponse = newsApi.getNews(sources = sources, page = page)
            totalNewsCount += newsResponse.articles.size
            val articles = newsResponse.articles.distinctBy { it.url }

            // insert ke DB
            newsDao.upsertArticles(articles.map { it.toEntity() })

            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { pos ->
            val anchorPage = state.closestPageToPosition(pos)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
