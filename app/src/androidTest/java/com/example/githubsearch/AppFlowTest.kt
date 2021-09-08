package com.example.githubsearch

import android.os.SystemClock
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AppFlowTest {

    @Rule
    @JvmField
    val activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun performSearch() {
        onView(withId(R.id.etSearch))
            .perform(typeText("Kotlin"), pressImeActionButton());
        SystemClock.sleep(3000);
    }

    @Test
    fun performEmptySearch() {
        onView(withId(R.id.etSearch))
            .perform(typeText(""), pressImeActionButton());
        SystemClock.sleep(1000);
    }


}