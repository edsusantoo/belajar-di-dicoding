package com.edsusantoo.bismillah.academy.ui.bookmark

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.edsusantoo.bismillah.academy.R
import com.edsusantoo.bismillah.academy.testing.SingleFragmentActivity
import com.edsusantoo.bismillah.academy.utils.RecyclerViewItemCountAssertion
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class BookmarkFragmentTest {
    @get:Rule
    val activityRule: ActivityTestRule<SingleFragmentActivity> = ActivityTestRule(SingleFragmentActivity::class.java)
    private val bookmarkFragment: BookmarkFragment = BookmarkFragment()

    @Before
    fun setUp() {
        activityRule.activity.setFragment(bookmarkFragment)
    }

    @Test
    fun loadBookmarks() {
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withId(R.id.rv_bookmark)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_bookmark)).check(RecyclerViewItemCountAssertion(5))
    }
}