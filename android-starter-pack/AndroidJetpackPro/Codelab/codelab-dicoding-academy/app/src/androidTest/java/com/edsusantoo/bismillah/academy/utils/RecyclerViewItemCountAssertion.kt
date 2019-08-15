package com.edsusantoo.bismillah.academy.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import org.hamcrest.CoreMatchers
import org.junit.Assert.assertNotNull

class RecyclerViewItemCountAssertion(private val expectedCounnt: Int) : ViewAssertion {
    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }

        val recyclerView: RecyclerView = view as RecyclerView
        val adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = recyclerView.adapter
        assertNotNull(adapter)
        assertThat(adapter?.itemCount, CoreMatchers.equalTo(expectedCounnt))//untuk mengetes jumlah rv sama dgn expetasi
    }

}