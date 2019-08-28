package com.edsusantoo.bismillah.academy.ui.bookmark

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.edsusantoo.bismillah.academy.R
import com.edsusantoo.bismillah.academy.testing.SingleFragmentActivity
import com.edsusantoo.bismillah.academy.utils.EspressoIdlingResource
import org.junit.Before
import org.junit.Rule
import org.junit.Test


//TODO:Jumlah recycleviewnya masih error
class BookmarkFragmentTest {
    @get:Rule
    val activityRule: ActivityTestRule<SingleFragmentActivity> = ActivityTestRule(SingleFragmentActivity::class.java)
    private val bookmarkFragment: BookmarkFragment = BookmarkFragment()

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
        activityRule.activity.setFragment(bookmarkFragment)
    }

    @Before
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadBookmarks() {
        onView(withId(R.id.rv_bookmark)).check(matches(isDisplayed()))
//        onView(withId(R.id.rv_bookmark)).check(RecyclerViewItemCountAssertion(5))
    }
}