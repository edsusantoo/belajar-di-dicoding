package com.edsusantoo.bismillah.academy.ui.reader.content


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.edsusantoo.bismillah.academy.R
import com.edsusantoo.bismillah.academy.data.source.local.entity.ContentEntity
import com.edsusantoo.bismillah.academy.data.source.local.entity.ModuleEntity
import com.edsusantoo.bismillah.academy.data.source.vo.Status
import com.edsusantoo.bismillah.academy.ui.reader.CourseReaderViewModel
import com.edsusantoo.bismillah.academy.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_module_content.*


@Suppress("PLUGIN_WARNING")
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
            viewModel = obtainViewModel(activity!!)
            progress_bar.visibility = View.VISIBLE
            viewModel.selectedModule.observe(this, Observer { moduleEntity ->
                if (moduleEntity != null) {
                    when (moduleEntity.status) {
                        Status.LOADING -> progress_bar.visibility = View.VISIBLE
                        Status.SUCCESS -> if (moduleEntity.data != null) {
                            setButtonNextPrevState(moduleEntity.data)
                            progress_bar.visibility = View.GONE
                            if (moduleEntity.data.mRead!!.not()) {
                                viewModel.readContent(moduleEntity.data)
                            }

                            if (moduleEntity.data.contentEntity != null) {
                                populateWebView(moduleEntity.data.contentEntity)
                            }
                        }
                        else -> {
                            progress_bar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            btn_next.setOnClickListener { viewModel.setNextPage() }

            btn_prev.setOnClickListener { viewModel.setPrevPage() }
        }

    }

    private fun populateWebView(content: ContentEntity?) {
        web_view.loadData(content?.mContent, "text/html", "UTF-8")
    }

    private fun setButtonNextPrevState(module: ModuleEntity) {
        if (activity != null) {
            when {
                module.position == 0 -> {
                    btn_prev.isEnabled = false
                    btn_next.isEnabled = true
                }
                module.position == viewModel.getModuleSize() - 1 -> {
                    btn_prev.isEnabled = true
                    btn_next.isEnabled = false
                }
                else -> {
                    btn_prev.isEnabled = true
                    btn_next.isEnabled = true
                }
            }
        }
    }

}
