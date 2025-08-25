package com.faizal.newsapp.viewmodel

import androidx.paging.PagingData
import com.faizal.core.domain.model.Article
import com.faizal.core.domain.model.Source
import com.faizal.core.domain.usecases.news.GetNews
import com.faizal.core.domain.usecases.news.GetSavedArticles
import com.faizal.core.utils.NetworkConnectionManager
import com.faizal.newsapp.helper.MainDispatcherRule
import com.faizal.newsapp.features.home.home.viewmodel.home.HomeViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private lateinit var getNews: GetNews
    private lateinit var getSavedArticles: GetSavedArticles
    private lateinit var networkConnectionManager: NetworkConnectionManager

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    @Before
    fun setUp() {
        getNews = mockk()
        getSavedArticles = mockk()
        networkConnectionManager = mockk()

        every { getNews.invoke(any()) } returns flowOf(
            PagingData.from(
                listOf(
                    Article(
                        "DummyTitle",
                        Source("id", "CNN"),
                        "DummyAuthor",
                        "DummyDesc",
                        "dummyUrl",
                        "dummyImage",
                        "2024-03-01",
                        "dummyContent"
                    )
                )
            )
        )

        every { networkConnectionManager.observeNetworkConnection() } returns flowOf(true)
        every { networkConnectionManager.isConnected() } returns true

        every { getSavedArticles.invoke() } returns flowOf(emptyList())

        viewModel = HomeViewModel(getNews, getSavedArticles, networkConnectionManager)
    }

    @Test
    fun `news flow should be collected successfully`() = runTest {
        val job = launch {
            viewModel.news.collect { pagingData ->
                assertNotNull(pagingData)
            }
        }
        job.cancel()
    }
}

