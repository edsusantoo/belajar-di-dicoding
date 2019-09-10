package com.edsusantoo.bismillah.academy.ui.academy


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.edsusantoo.bismillah.academy.R
import com.edsusantoo.bismillah.academy.data.source.vo.Status
import com.edsusantoo.bismillah.academy.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_academy.*


class AcademyFragment : Fragment() {

    private lateinit var viewModel: AcademyViewModel

    companion object {
        fun newInstance(): Fragment {
            return AcademyFragment()
        }

        private fun obtainViewModel(activity: FragmentActivity): AcademyViewModel {
            val factory = ViewModelFactory.getInstance(activity.application)
            return ViewModelProviders.of(activity, factory).get(AcademyViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_academy, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {

            viewModel = obtainViewModel(activity!!)

            val academyAdapter = AcademyAdapter(activity)
            progress_bar.visibility = View.VISIBLE

            viewModel.setUsername("Dicoding")
            viewModel.course.observe(this, Observer {
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> {
                            progress_bar.visibility = View.VISIBLE
                        }
                        Status.SUCCESS -> {
                            progress_bar.visibility = View.GONE
                            academyAdapter.setListCourses(it.data)
                            academyAdapter.notifyDataSetChanged()
                        }
                        else -> {
                            progress_bar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })

            rv_academy.layoutManager = LinearLayoutManager(context)
            rv_academy.setHasFixedSize(true)
            rv_academy.adapter = academyAdapter

        }
    }
}
