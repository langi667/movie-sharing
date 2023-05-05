package de.stefanlang.moviesharingjc

import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import de.stefanlang.moviesharingjc.core.rating_control.RatingControl
import de.stefanlang.moviesharingjc.core.rating_control.contentDescriptionRating
import de.stefanlang.moviesharingjc.ui.theme.MovieSharingJCTheme
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class RatingControlTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun defaultStates() {
        val max = 5
        val initial = 1

        composeTestRule.setContent {
            MovieSharingJCTheme {
                Box(contentAlignment = Alignment.Center) {
                    RatingControl(max, initial)
                }
            }
        }

        for (i in 1..max) {
            val description = contentDescriptionRating(i, i <= initial)
            composeTestRule.onNodeWithContentDescription(description, useUnmergedTree = true)
                .assertExists()
        }
    }

    @Test
    fun testSelection() {
        val max = 5
        val initial = 1

        composeTestRule.setContent {
            MovieSharingJCTheme {
                Box(contentAlignment = Alignment.Center) {
                    RatingControl(max, initial)
                }
            }
        }

        val new = 3
        val newDesc = contentDescriptionRating(new, false)
        composeTestRule.onNodeWithContentDescription(newDesc).performClick()

        for (curr in 1..new) {
            val newDescSelected = contentDescriptionRating(curr, true)
            composeTestRule.onNodeWithContentDescription(newDescSelected).assertExists()
        }

        val newDescSelected = contentDescriptionRating(new + 1, true)
        composeTestRule.onNodeWithContentDescription(newDescSelected).assertDoesNotExist()
    }

    @Test
    fun testNonePreSelected() {
        val max = 10
        val initial = 0

        composeTestRule.setContent {
            MovieSharingJCTheme {
                Box(contentAlignment = Alignment.Center) {
                    RatingControl(max, initial)
                }
            }
        }

        for (curr in 1..max) {
            val currDesc = contentDescriptionRating(curr, false)
            val currDescSelected = contentDescriptionRating(curr, true)
            composeTestRule.onNodeWithContentDescription(currDesc).assertExists()
            composeTestRule.onNodeWithContentDescription(currDescSelected).assertDoesNotExist()
        }
    }

    fun testPreSelectedOvershoot() {
        val max = 10
        val offset = 1
        val initial = max + 1

        composeTestRule.setContent {
            MovieSharingJCTheme {
                Box(contentAlignment = Alignment.Center) {
                    RatingControl(max, initial)
                }
            }
        }

        for (curr in 1..max) {
            val currDesc = contentDescriptionRating(curr, false)
            val currDescSelected = contentDescriptionRating(curr, true)

            composeTestRule.onNodeWithContentDescription(currDesc).assertDoesNotExist()
            composeTestRule.onNodeWithContentDescription(currDescSelected).assertExists()
        }

        for (curr in max..initial) {
            val currDesc = contentDescriptionRating(curr, true)
            composeTestRule.onNodeWithContentDescription(currDesc).assertDoesNotExist()
        }
    }

    @Test
    fun testCallbackDeselection() {
        val max = 5
        val initial = 4
        val newSelection = 2

        val onRatingChanged = { selection: Int ->
            assertEquals(selection, newSelection)
        }

        composeTestRule.setContent {
            MovieSharingJCTheme {
                Box(contentAlignment = Alignment.Center) {
                    RatingControl(max, initial, onRatingChanged = onRatingChanged)
                }
            }
        }

        val currDesc = contentDescriptionRating(newSelection, newSelection <= initial)
        composeTestRule.onNodeWithContentDescription(currDesc).performClick()
    }

    @Test
    fun testCallbackSelection() {
        val max = 5
        val initial = 2
        val newSelection = 4

        val onRatingChanged = { selection: Int ->
            assertEquals(selection, newSelection)
        }

        composeTestRule.setContent {
            MovieSharingJCTheme {
                Box(contentAlignment = Alignment.Center) {
                    RatingControl(max, initial, onRatingChanged = onRatingChanged)
                }
            }
        }

        val currDesc = contentDescriptionRating(newSelection, newSelection <= initial)
        composeTestRule.onNodeWithContentDescription(currDesc).performClick()
    }

    @Test
    fun testCallbackShouldNotTrigger() {
        val max = 5
        val initial = 4
        val newSelection = 4

        val onRatingChanged = { selection: Int ->
            // Should have been called
            assert(false)
        }

        composeTestRule.setContent {
            MovieSharingJCTheme {
                Box(contentAlignment = Alignment.Center) {
                    RatingControl(max, initial, onRatingChanged = onRatingChanged)
                }
            }
        }

        val currDesc = contentDescriptionRating(newSelection, newSelection <= initial)
        composeTestRule.onNodeWithContentDescription(currDesc).performClick()
    }

    @Test
    fun testDrag() {
        val max = 5
        val initial = 1

        val testTag = "RatingControlTest"
        var ratingChangedCount = 0
        val onRatingChanged = { selection: Int ->
            // Should have been called
            ratingChangedCount += 1
            if (ratingChangedCount == 1) {
                assertEquals(3, selection)
            } else if (ratingChangedCount == 2) {
                assertEquals(5, selection)
            }
        }

        composeTestRule.setContent {
            MovieSharingJCTheme {
                Box(contentAlignment = Alignment.Center) {
                    RatingControl(
                        max,
                        initial,
                        testTag = testTag,
                        onRatingChanged = onRatingChanged
                    )
                }
            }
        }

        val node = composeTestRule.onNodeWithTag(testTag, useUnmergedTree = true)
        node.assertExists()

        node.performTouchInput {
            click()
            swipeLeft()
        }
    }
}