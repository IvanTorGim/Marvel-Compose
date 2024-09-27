package com.example.marvelcompose

import androidx.compose.ui.test.hasAnySibling
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasScrollToIndexAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.test.platform.app.InstrumentationRegistry
import arrow.core.Either
import com.example.marvelcompose.data.entities.Comic
import com.example.marvelcompose.ui.screens.common.MarvelItemListScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private val items: List<Comic> = (1..100).map {
        Comic(
            id = it,
            title = "Title $it",
            description = "Description $it",
            thumbnail = "",
            format = Comic.Format.COMIC,
            urls = emptyList(),
            reference = emptyList()
        )
    }

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MarvelItemListScreen(marvelItems = Either.Right(items)) {}
        }
    }

    @Test
    fun navigatesTo51(): Unit = with(composeTestRule) {
        onNode(hasScrollToIndexAction()).performScrollToIndex(50)
        onNodeWithText("Title 51").assertExists()
    }

    @Test
    fun navigatesTo61AndShowsBottomSheet(): Unit = with(composeTestRule) {
        onNode(hasScrollToIndexAction()).performScrollToIndex(50)

        onNode(
            hasParent(hasText("Title 51"))
                    and hasContentDescription(context.getString(R.string.more_actions)
            )
        ).performClick()

        onNode(
            hasAnySibling(hasText(context.getString(R.string.go_to_detail))
                    and hasText("Title 51")
            )
        ).assertExists()
    }
}

