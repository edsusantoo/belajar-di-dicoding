package com.edsusantoo.bismillah.academy.ui.reader.list


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.edsusantoo.bismillah.academy.R
import com.edsusantoo.bismillah.academy.data.ModuleEntity
import com.edsusantoo.bismillah.academy.ui.reader.CourseReaderActivity
import com.edsusantoo.bismillah.academy.ui.reader.CourseReaderCallback
import com.edsusantoo.bismillah.academy.ui.reader.CourseReaderViewModel
import kotlinx.android.synthetic.main.fragment_module_list.*


class ModuleListFragment : Fragment(), ModuleListAdapter.MyAdapterClickListener {

    private lateinit var adapter: ModuleListAdapter
    private lateinit var courseReaderCallback: CourseReaderCallback
    private lateinit var viewModel: CourseReaderViewModel

    companion object {
        var TAG: String = ModuleListFragment::class.java.simpleName

        fun newInstance(): Fragment {
            return ModuleListFragment()
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_module_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            viewModel = ViewModelProviders.of(activity!!).get(CourseReaderViewModel::class.java)
            adapter = ModuleListAdapter(this)
            populateRecyclerView(viewModel.getModules())
        }
    }

    override fun onItemClicked(position: Int, moduleId: String) {
        courseReaderCallback.moveTo(position, moduleId)
        viewModel.setSelectedModule(moduleId)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        courseReaderCallback = context as CourseReaderActivity
    }

    private fun populateRecyclerView(modules: List<ModuleEntity>) {
        progress_bar.visibility = View.GONE
        adapter.setModules(modules)
        rv_module.layoutManager = LinearLayoutManager(context)
        rv_module.setHasFixedSize(true)
        rv_module.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(rv_module.context, DividerItemDecoration.VERTICAL)
        rv_module.addItemDecoration(dividerItemDecoration)
    }


}
