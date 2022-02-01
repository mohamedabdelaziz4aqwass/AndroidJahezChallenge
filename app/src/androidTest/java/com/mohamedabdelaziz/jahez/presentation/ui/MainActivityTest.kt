package com.mohamedabdelaziz.jahez.presentation.ui

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
 import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.rule.ActivityTestRule
import com.mohamedabdelaziz.jahez.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    @Rule @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)
    @Test
    fun isMainActivityInView() {
       ActivityScenario.launch(MainActivity::class.java)
       onView(ViewMatchers.withId(R.id.mainActivity)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun sortListByOffers() {
        onView(ViewMatchers.withId(R.id.action_offers)).perform(click())
    }

    @Test
    fun sortListByDistance() {
        onView(ViewMatchers.withId(R.id.action_distance)).perform(click())
    }



}