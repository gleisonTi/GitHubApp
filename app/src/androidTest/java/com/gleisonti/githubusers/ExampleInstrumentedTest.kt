package com.gleisonti.githubusers

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavHostController
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gleisonti.githubusers.model.models.usermodels.UserSearchItem
import com.gleisonti.githubusers.presenter.MainActivity
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ScaffoldComposeTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    private lateinit var pagingSource: PagingSource<Int, UserSearchItem>

    @Before
    fun setup() {
        pagingSource = mockk()
    }


    @Test
    fun testUserListView() = runBlockingTest {
        composeTestRule.onNodeWithText("Lista De Usuários").assertIsDisplayed()
    }

    @Test
    fun testNavigation() {

        composeTestRule.onNodeWithText("Screen 1").performClick()

        // Verifique se a navegação para a segunda tela ocorreu corretamente
        composeTestRule.onNodeWithText("Screen 2").assertIsDisplayed()
    }

}
