package com.edsusantoo.bismillah.academy.ui.academy

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.edsusantoo.bismillah.academy.R
import com.edsusantoo.bismillah.academy.testing.SingleFragmentActivity
import com.edsusantoo.bismillah.academy.utils.EspressoIdlingResource
import com.edsusantoo.bismillah.academy.utils.RecyclerViewItemCountAssertion
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AcademyFragmentTest {
    @get:Rule
    val activityRule: ActivityTestRule<SingleFragmentActivity> = ActivityTestRule(SingleFragmentActivity::class.java)

    private val academyFragment: AcademyFragment = AcademyFragment()

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
        activityRule.activity.setFragment(academyFragment)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadCourses() {
        onView(withId(R.id.rv_academy)).check(RecyclerViewItemCountAssertion(5))
    }
}