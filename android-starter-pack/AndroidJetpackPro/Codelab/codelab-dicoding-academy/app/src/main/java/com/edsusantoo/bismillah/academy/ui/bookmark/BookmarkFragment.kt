package com.edsusantoo.bismillah.academy.ui.bookmark


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.edsusantoo.bismillah.academy.R
import com.edsusantoo.bismillah.academy.data.source.local.entity.CourseEntity
import com.edsusantoo.bismillah.academy.data.source.vo.Status
import com.edsusantoo.bismillah.academy.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_bookmark.*


class BookmarkFragment : Fragment(), BookmarkFragmentCallback {


    private lateinit var viewModel: BookmarkViewModel

    companion object {
        fun newInstance(): Fragment {
            return BookmarkFragment()
        }

        private fun obtainViewModel(activity: FragmentActivity): BookmarkViewModel {
            val factory = ViewModelFactory.getInstance(activity.application)
            return ViewModelProviders.of(activity, factory).get(BookmarkViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {

            viewModel = obtainViewModel(activity!!)

            val bookmarkAdapter = BookmarkAdapter(activity, this)
            progress_bar.visibility = View.VISIBLE


            viewModel.getBookmark()?.observe(this, Observer {
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> {
                            progress_bar.visibility = View.VISIBLE
                        }
                        Status.SUCCESS -> {
                            progress_bar.visibility = View.GONE
                            bookmarkAdapter.setListCourses(it.data)
                            bookmarkAdapter.notifyDataSetChanged()
                        }
                        else -> {
                            progress_bar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })

            rv_bookmark.layoutManager = LinearLayoutManager(context)
            rv_bookmark.setHasFixedSize(true)
            rv_bookmark.adapter = bookmarkAdapter
        }

    }

    override fun onShareClick(courseEntity: CourseEntity) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(activity)
                .setType(mimeType)
                .setChooserTitle("Bagikan aplikasi ini sekarang")
                .setText(String.format("Segera daftarkan kelas %s di dicoding.com", courseEntity.title))
                .startChooser()
        }
    }
}
