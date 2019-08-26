package com.edsusantoo.bismillah.academy.ui.reader.content


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.edsusantoo.bismillah.academy.R
import com.edsusantoo.bismillah.academy.data.ModuleEntity
import com.edsusantoo.bismillah.academy.ui.reader.CourseReaderViewModel
import com.edsusantoo.bismillah.academy.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_module_content.*


class ModuleContentFragment : Fragment() {
    private lateinit var viewModel: CourseReaderViewModel

    companion object {
        var TAG: String = ModuleContentFragment::class.java.simpleName

        fun newInstance(): Fragment {
            return ModuleContentFragment()
        }

        private fun obtainViewModel(activity: FragmentActivity): CourseReaderViewModel {
            val factory = ViewModelFactory.getInstance(activity.application)
            return ViewModelProviders.of(activity, factory).get(CourseReaderViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_module_content, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {

            progress_bar.visibility = View.VISIBLE

            viewModel = obtainViewModel(activity!!)

            viewModel.getSelectedModule()?.observe(this, Observer {
                if (it != null) {
                    progress_bar.visibility = View.GONE
                    populateWebView(it)
                }
            })
        }
    }

    private fun populateWebView(content: ModuleEntity?) {
        web_view.loadData(content?.contentEntity?.mContent, "text/html", "UTF-8")
    }
}
