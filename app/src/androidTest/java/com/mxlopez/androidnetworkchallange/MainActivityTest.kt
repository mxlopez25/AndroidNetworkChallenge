package com.mxlopez.androidnetworkchallange

import android.util.Log
import android.view.View
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.platform.app.InstrumentationRegistry
import com.mxlopez.androidnetworkchallange.models.CharacterModel
import com.mxlopez.androidnetworkchallange.viewmodels.CharactersViewModel
import kotlinx.coroutines.delay
import org.hamcrest.Matchers.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainActivityTest {
    @get:Rule
    val composeTestRule =  createAndroidComposeRule<MainActivity>()

    @Before
    fun registerIdlingResource() {
//        CharactersViewModel().getCharacterList(InstrumentationRegistry.getInstrumentation().getTargetContext())
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResources() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun checkListFirstItem() {
        composeTestRule.waitForIdle()
        val list = Helper.getCharacterList()

        composeTestRule.onNodeWithText("Name: ${list.first().name}").assertExists()
        composeTestRule.onNodeWithText("Name: ${list.first().name}").performClick()
    }

    @Test
    fun checkAllItems() {
        composeTestRule.waitForIdle()
        val characters = Helper.getCharacterList()

        for(character in characters) {
            composeTestRule.onNodeWithText("Name: ${character.name}").assertExists()
            composeTestRule.onNodeWithText("Name: ${character.name}").assertIsDisplayed()

        }
    }

    @Test
    fun checkLastItem() {
        composeTestRule.waitForIdle()
        val character = Helper.getCharacterList().last()

        composeTestRule.onNodeWithText("Name: ${character.name}").assertExists()
        composeTestRule.onNodeWithText("Name: ${character.name}").assertIsDisplayed()
    }

    @Test
    fun checkToastMessage() {
        composeTestRule.waitForIdle()
        val decor = composeTestRule.activity.window.decorView
        val characters = Helper.getCharacterList()

        for (c in characters) {
            composeTestRule.onNodeWithText("Name: ${c.name}").performClick()
            composeTestRule.onNodeWithText("Name: ${c.name}").assertIsDisplayed()
        }
    }
}