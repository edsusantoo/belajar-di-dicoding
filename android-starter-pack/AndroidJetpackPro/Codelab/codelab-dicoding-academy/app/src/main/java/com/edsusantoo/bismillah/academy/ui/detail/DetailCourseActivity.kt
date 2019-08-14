package com.edsusantoo.bismillah.academy.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.edsusantoo.bismillah.academy.R
import com.edsusantoo.bismillah.academy.data.CourseEntity
import com.edsusantoo.bismillah.academy.ui.reader.CourseReaderActivity
import kotlinx.android.synthetic.main.activity_detail_course.*
import kotlinx.android.synthetic.main.content_detail_course.*


class DetailCourseActivity : AppCompatActivity() {

    private lateinit var adapter: DetailCourseAdapter

    private lateinit var viewModel: DetailCourseViewModel

    companion object {
        const val EXTRA_COURSE = "extra_course"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_course)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProviders.of(this).get(DetailCourseViewModel::class.java)

        adapter = DetailCourseAdapter()

        val extras: Bundle? = intent.extras
        if (extras != null) {
            val courseId: String? = extras.getString(EXTRA_COURSE)
            if (courseId != null) {
                viewModel.setCourseId(courseId)
                adapter.setModules(viewModel.getModules())
            }
        }

        if (viewModel.getCourse() != null) {
            populateCourse(viewModel.getCourse())
        }

        rv_module.isNestedScrollingEnabled = false
        rv_module.layoutManager = LinearLayoutManager(this)
        rv_module.setHasFixedSize(true)
        rv_module.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(rv_module.context, DividerItemDecoration.VERTICAL)
        rv_module.addItemDecoration(dividerItemDecoration)


    }

    private fun populateCourse(courseEntity: CourseEntity?) {
        text_title.text = courseEntity?.title
        text_description.text = courseEntity?.description
        text_date.text = String.format("Deadline %s", courseEntity?.deadline)

        Glide.with(applicationContext)
                .load(courseEntity?.imagePath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(image_poster)

        btn_start.setOnClickListener {
            val intent = Intent(this@DetailCourseActivity, CourseReaderActivity::class.java)
            intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, viewModel.getCourseId())
            startActivity(intent)
        }
    }

}