package com.faizal.newsapp.home


import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.faizal.core.domain.model.Article
import com.faizal.core.domain.model.Source
import com.faizal.newsapp.features.home.home.ui.home.HomeScreen
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val dummyArticles = listOf(
        Article("Title1", Source("id1", "BBC"), "Author1", "Desc1", "url1", "image1", "2024-03-01", "content1"),
        Article("Title2", Source("id2", "CNN"), "Author2", "Desc2", "url2", "image2", "2024-03-02", "content2")
    )

    private fun fakePagingData(): Flow<PagingData<Article>> {
        return flowOf(PagingData.from(dummyArticles))
    }

    @Test
    fun searchBar_isDisplayed() {
        composeTestRule.setContent {
            HomeScreen(
                articles = fakePagingData().collectAsLazyPagingItems(),
                navigateToSearch = {},
                navigateToDetails = {},
                event = {},
                sideEffect = null
            )
        }
        composeTestRule.onNodeWithTag("SearchBar").assertIsDisplayed()
    }

//    @Test
//    fun articleList_displaysItemsCorrectly() {
//        composeTestRule.setContent {
//            HomeScreen(
//                articles = fakePagingData().collectAsLazyPagingItems(),
//                navigateToSearch = {},
//                navigateToDetails = {}
//            )
//        }
//
//        composeTestRule.waitForIdle()
//
//        composeTestRule.onNodeWithText("Title1").assertIsDisplayed()
//        composeTestRule.onNodeWithText("Title2").assertIsDisplayed()
//    }







}
