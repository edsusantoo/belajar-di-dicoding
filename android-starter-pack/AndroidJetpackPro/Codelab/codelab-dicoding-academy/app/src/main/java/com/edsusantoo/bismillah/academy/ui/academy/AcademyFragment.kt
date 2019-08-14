package com.edsusantoo.bismillah.academy.ui.academy


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.edsusantoo.bismillah.academy.R
import kotlinx.android.synthetic.main.fragment_academy.*


class AcademyFragment : Fragment() {

    private lateinit var viewModel: AcademyViewModel

    companion object {
        fun newInstance(): Fragment {
            return AcademyFragment()
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

            viewModel = ViewModelProviders.of(this).get(AcademyViewModel::class.java)

            val academyAdapter = AcademyAdapter(activity)
            academyAdapter.setListCourses(viewModel.getCourse())

            rv_academy.layoutManager = LinearLayoutManager(context)
            rv_academy.setHasFixedSize(true)
            rv_academy.adapter = academyAdapter

        }
    }
}
