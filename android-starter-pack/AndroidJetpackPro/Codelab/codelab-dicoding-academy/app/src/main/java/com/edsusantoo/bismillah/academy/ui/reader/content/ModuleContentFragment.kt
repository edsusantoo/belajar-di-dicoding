package com.edsusantoo.bismillah.academy.ui.reader.content


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.edsusantoo.bismillah.academy.R
import com.edsusantoo.bismillah.academy.data.ModuleEntity
import com.edsusantoo.bismillah.academy.ui.reader.CourseReaderViewModel
import kotlinx.android.synthetic.main.fragment_module_content.*


class ModuleContentFragment : Fragment() {
    private lateinit var viewModel: CourseReaderViewModel

    companion object {
        var TAG: String = ModuleContentFragment::class.java.simpleName

        fun newInstance(): Fragment {
            return ModuleContentFragment()
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

            viewModel = ViewModelProviders.of(activity!!).get(CourseReaderViewModel::class.java)

            populateWebView(viewModel.getSelectedModule())
        }
    }

    private fun populateWebView(content: ModuleEntity?) {
        web_view.loadData(content?.contentEntity?.mContent, "text/html", "UTF-8")
    }
}
