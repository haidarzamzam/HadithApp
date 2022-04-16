package com.haidev.hadithapp.ui.screen.hadithbooks

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.haidev.hadithapp.R
import com.haidev.hadithapp.util.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test


class HadithBooksActivityTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(HadithBooksActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadListHadithBooks() {
        Espresso.onView(withId(R.id.rv_hadith_books))
            .check(matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_hadith_books))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))
        Espresso.onView(withId(R.id.rv_hadith_books))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))
    }
}