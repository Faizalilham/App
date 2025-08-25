package com.faizal.newsapp.paging

import androidx.paging.PagingSource
import com.faizal.core.data.local.NewsDao
import com.faizal.core.data.remote.wrapped.ListResponse
import com.faizal.core.data.remote.NewsApi
import com.faizal.core.data.remote.NewsPagingSource
import com.faizal.core.domain.model.Article
import com.faizal.core.domain.model.Source
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException


@ExperimentalCoroutinesApi
class NewsPagingDataSourceTest {

    private lateinit var newsApi: NewsApi
    private lateinit var pagingSource: NewsPagingSource
    private lateinit var newsDao: NewsDao

    @Before
    fun setUp() {
        newsApi = mockk()
        newsDao = mockk()
        pagingSource = NewsPagingSource(newsApi,newsDao, "bbc-news")
    }

    @Test
    fun `load returns page when successful`() = runTest {
        val fakeArticles = listOf(
            Article("Title1", Source("id1", "BBC"), "Author1", "Desc1", "url1", "image1", "2024-03-01", "content1"),
            Article("Title2", Source("id2", "ABC"), "Author2", "Desc2", "url2", "image2", "2024-03-02", "content2")
        )
        val response = ListResponse(status = "ok", totalResults = 2, articles = fakeArticles)

        coEvery { newsApi.getNews(any(), any(), any()) } returns response

        val result = pagingSource.load(PagingSource.LoadParams.Refresh(null, 10, false))

        assertTrue(result is PagingSource.LoadResult.Page)
        assertEquals(2, (result as PagingSource.LoadResult.Page).data.size)
    }

    @Test
    fun `load returns error when API fails`() = runTest {
        coEvery { newsApi.getNews(any(), any(), any()) } throws IOException("Network error")

        val result = pagingSource.load(PagingSource.LoadParams.Refresh(null, 10, false))

        assertTrue(result is PagingSource.LoadResult.Error)
    }

    @Test
    fun `load returns page with dummy data`() = runTest {
        // Dummy data
        val dummyArticles = listOf(
            Article("Title1", Source("id1", "BBC"), "Author1", "Desc1", "url1", "image1", "2024-03-01", "content1"),
            Article("Title2", Source("id2", "ABC"), "Author2", "Desc2", "url2", "image2", "2024-03-02", "content2")
        )
        val fakeResponse = ListResponse(status = "ok", totalResults = 2, articles = dummyArticles)


        coEvery { newsApi.getNews(any(), any(), any()) } returns fakeResponse

        val result = pagingSource.load(PagingSource.LoadParams.Refresh(null, 10, false))

        // Cek bro apakah hasilnya sesuai dengan dummy data
        assertTrue(result is PagingSource.LoadResult.Page)
        val data = (result as PagingSource.LoadResult.Page).data
        assertEquals(2, data.size)
        assertEquals("Title1", data[0].title)
    }

}
