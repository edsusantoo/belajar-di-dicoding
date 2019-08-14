package com.edsusantoo.bismillah.academy.ui.bookmark


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.edsusantoo.bismillah.academy.R
import com.edsusantoo.bismillah.academy.data.CourseEntity
import kotlinx.android.synthetic.main.fragment_bookmark.*


class BookmarkFragment : Fragment(), BookmarkFragmentCallback {
    private lateinit var viewModel: BookmarkViewModel

    companion object {
        fun newInstance(): Fragment {
            return BookmarkFragment()
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

            viewModel = ViewModelProviders.of(this).get(BookmarkViewModel::class.java)

            val bookmarkAdapter = BookmarkAdapter(activity, this)
            bookmarkAdapter.setListCourses(viewModel.getBookmark())

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
