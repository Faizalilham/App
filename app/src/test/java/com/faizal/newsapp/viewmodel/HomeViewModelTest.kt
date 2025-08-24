package com.faizal.newsapp.viewmodel

import androidx.paging.PagingData
import com.faizal.newsapp.common.model.Article
import com.faizal.newsapp.domain.model.Source
import com.faizal.newsapp.helper.MainDispatcherRule
import com.faizal.newsapp.features.home.home.viewmodel.home.HomeViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private lateinit var newsUseCase: NewsUseCase

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    @Before
    fun setUp() {
        newsUseCase = mockk()
        every { newsUseCase.getNews(any()) } returns flowOf(
            PagingData.from(
                listOf(Article("DummyTitle", Source("id", "CNN"), "DummyAuthor", "DummyDesc", "dummyUrl", "dummyImage", "2024-03-01", "dummyContent"))
            )
        )

        viewModel = HomeViewModel(newsUseCase)
    }

    @Test
    fun `news flow should be collected successfully`() = runTest {
        assertNotNull(viewModel.news)
    }
}
