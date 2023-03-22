package de.stefanlang.jc_demo_app

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("de.stefanlang.jc_demo_app", appContext.packageName)
    }

    @Test
    fun initialState() {
        rule.onNodeWithText("Add").assertExists()
        rule.onNodeWithText("0").assertExists()
    }

    @Test
    fun testCount() {
        rule.onNodeWithText("Add").assertExists()
        rule.onNodeWithTag("button").performClick()
        rule.onNodeWithTag("sum", useUnmergedTree = true).assertTextEquals("1")
    }
}