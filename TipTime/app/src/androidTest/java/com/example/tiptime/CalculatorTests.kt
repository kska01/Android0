package com.example.tiptime

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)   // To specify the test runner
class CalculatorTests {
    // To interact with the activity
    @get: Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_default_tip() {
        // To input some text in edit text field
        onView(withId(R.id.cost_of_service_edit_text)).perform(typeText("50.00"))
        // To click the Calculate button
        onView(withId(R.id.calculate_button)).perform(click())
        // To display correct tip
        onView(withId(R.id.tip_result)).check(matches(withText(containsString("$10.00"))))
    }

    @Test
    fun calculate_18_percent_tip() {
        onView(withId(R.id.cost_of_service_edit_text)).perform(typeText("100.00"))
        onView(withId(R.id.option_eighteen_percent)).perform(click())
        onView(withId(R.id.calculate_button)).perform(click())
        onView(withId(R.id.tip_result)).check(matches(withText(containsString("18.00"))))
    }

    @Test
    fun calculate_15_percent_tip() {
        onView(withId(R.id.cost_of_service_edit_text)).perform(typeText("100.00"))
        onView(withId(R.id.option_fifteen_percent)).perform(click())
        onView(withId(R.id.calculate_button)).perform(click())
        onView(withId(R.id.tip_result)).check(matches(withText(containsString("15.00"))))
    }

    @Test
    fun calculate_18_percent_tip_No_Round_Up() {
        onView(withId(R.id.cost_of_service_edit_text)).perform(typeText("70.00"))
        onView(withId(R.id.option_eighteen_percent)).perform(click())
        onView(withId(R.id.round_up_switch)).perform(click())
        onView(withId(R.id.calculate_button)).perform(click())
        onView(withId(R.id.tip_result)).check(matches(withText(containsString("12.60"))))
    }
}