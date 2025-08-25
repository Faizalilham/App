package com.faizal.newsapp.repository



import com.faizal.core.data.local.NewsDao
import com.faizal.core.data.remote.wrapped.ListResponse
import com.faizal.core.data.remote.NewsApi
import com.faizal.core.data.repository.NewsRepositoryImpl
import com.faizal.core.domain.model.Article
import com.faizal.core.domain.model.Source
import com.faizal.core.domain.repository.NewsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsRepositoryImplTest {

    private lateinit var newsApi: NewsApi
    private lateinit var newsDao: NewsDao
    private lateinit var repository: NewsRepository

    @Before
    fun setUp() {
        newsApi = mockk()
        newsDao = mockk()
        repository = NewsRepositoryImpl(newsApi,newsDao)
    }

    @Test
    fun `getNews should return Flow of PagingData`() = runTest {
        val flow = repository.getNews(listOf("bbc-news"))

        assertNotNull(flow)
    }

    @Test
    fun `getNews should return dummy data in Flow`() = runTest {
        val dummyArticles = listOf(
            Article("DummyTitle", Source("id", "CNN"), "DummyAuthor", "DummyDesc", "dummyUrl", "dummyImage", "2024-03-01", "dummyContent")
        )
        val fakeResponse = ListResponse(status = "ok", totalResults = 2, articles = dummyArticles)

        coEvery { newsApi.getNews(any(), any(), any()) } returns fakeResponse

        val flow = repository.getNews(listOf("cnn"))

        assertNotNull(flow)
    }
}
