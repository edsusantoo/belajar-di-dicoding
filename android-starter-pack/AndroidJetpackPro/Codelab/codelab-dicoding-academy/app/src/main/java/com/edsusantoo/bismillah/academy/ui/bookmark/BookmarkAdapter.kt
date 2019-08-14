package com.edsusantoo.bismillah.academy.ui.bookmark

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.edsusantoo.bismillah.academy.R
import com.edsusantoo.bismillah.academy.data.CourseEntity
import com.edsusantoo.bismillah.academy.ui.detail.DetailCourseActivity
import kotlinx.android.synthetic.main.items_bookmark.view.*


class BookmarkAdapter(
        private val activity: Activity?,
        private val callback: BookmarkFragmentCallback
) : RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>() {

    private val mCourse = ArrayList<CourseEntity>()

    fun setListCourses(listCourse: List<CourseEntity>?) {
        if (listCourse == null) return
        this.mCourse.clear()
        this.mCourse.addAll(listCourse)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        return BookmarkViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items_bookmark, parent, false))
    }

    override fun getItemCount(): Int {
        return mCourse.size
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val course = mCourse.get(position)

        holder.tvTitle.text = course.title
        holder.tvDate.text = String.format("Deadline %s", course.deadline)
        holder.tvDescription.text = course.description
        holder.itemView.setOnClickListener { v ->
            val intent = Intent(activity, DetailCourseActivity::class.java)
            intent.putExtra(DetailCourseActivity.EXTRA_COURSE, course.courseId)
            activity?.startActivity(intent)
        }
        holder.imgShare.setOnClickListener { callback.onShareClick(mCourse[holder.adapterPosition]) }

        Glide.with(holder.itemView.context)
                .load(course.imagePath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(holder.imgPoster)
    }

    class BookmarkViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.tv_item_title
        val tvDescription: TextView = view.tv_item_description
        val tvDate: TextView = view.tv_item_date
        val imgShare: ImageButton = view.img_share
        val imgPoster: ImageView = view.img_poster
    }

}