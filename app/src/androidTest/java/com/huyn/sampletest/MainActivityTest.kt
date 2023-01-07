package com.huyn.sampletest

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test

class MainActivityTest() {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testNextButton_expectedCorrectQuote() {
        Espresso.onView(ViewMatchers.withId(R.id.btn_next))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.tv_quote))
            .check(ViewAssertions.matches(ViewMatchers.withText("You can observe a lot just by watching")))
    }

    @Test
    fun testShareButton() {
        Intents.init()
        val expected = Matchers.allOf(
            IntentMatchers.hasAction(Intent.ACTION_CHOOSER),
//            IntentMatchers.hasAction(Intent.ACTION_SEND),
//            IntentMatchers.hasExtra("Your key", "Watch ${dummyMovieData.title} with me!\n\n${dummyMovieData.summary}"),
//            IntentMatchers.hasType("text/plain")
        )
        Espresso.onView(ViewMatchers.withId(R.id.btn_share))
            .perform(ViewActions.click())
        intended(expected)
        Intents.release()
    }

}