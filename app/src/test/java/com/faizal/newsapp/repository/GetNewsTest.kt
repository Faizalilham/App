package com.faizal.newsapp.repository

import androidx.paging.PagingData
import com.faizal.newsapp.common.model.Article
import com.faizal.newsapp.domain.model.Source
import com.faizal.newsapp.domain.repository.NewsRepository
import com.faizal.newsapp.domain.usecases.news.GetNews
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetNewsTest {

    private lateinit var newsRepository: NewsRepository
    private lateinit var getNews: GetNews

    @Before
    fun setUp() {
        newsRepository = mockk()
        getNews = GetNews(newsRepository)
    }

    @Test
    fun `invoke should call getNews on repository`() = runTest {
        every { newsRepository.getNews(any()) } returns flowOf(PagingData.empty())

        val result = getNews.invoke(listOf("bbc-news"))

        assertNotNull(result)
    }

    @Test
    fun `invoke should return dummy PagingData`() = runTest {
        val dummyPagingData = PagingData.from(
            listOf(Article("FakeTitle", Source("id", "BBC"), "FakeAuthor", "FakeDesc", "fakeUrl", "fakeImage", "2024-03-01", "fakeContent"))
        )

        every { newsRepository.getNews(any()) } returns flowOf(dummyPagingData)

        val result = getNews.invoke(listOf("bbc-news"))

        assertNotNull(result)
    }
}
