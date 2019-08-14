package com.edsusantoo.bismillah.academy.ui.academy

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.edsusantoo.bismillah.academy.R
import com.edsusantoo.bismillah.academy.data.CourseEntity
import com.edsusantoo.bismillah.academy.ui.detail.DetailCourseActivity
import kotlinx.android.synthetic.main.items_academy.view.*


class AcademyAdapter(private val activity: Activity?) : RecyclerView.Adapter<AcademyAdapter.AcademyViewHolder>() {

    private val mCourse = ArrayList<CourseEntity>()

    fun setListCourses(listCourse: List<CourseEntity>?) {
        if (listCourse == null) return
        this.mCourse.clear()
        this.mCourse.addAll(listCourse)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcademyViewHolder {
        return AcademyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items_academy, parent, false))
    }

    override fun getItemCount(): Int {
        return mCourse.size
    }

    override fun onBindViewHolder(holder: AcademyViewHolder, position: Int) {
        holder.tvTitle.text = mCourse[position].title
        holder.tvDescription.text = mCourse[position].description
        holder.tvDate.text = String.format("Deadline %s", mCourse[position].deadline)
        holder.itemView.setOnClickListener {
            val intent = Intent(activity, DetailCourseActivity::class.java)
            intent.putExtra(DetailCourseActivity.EXTRA_COURSE, mCourse[position].courseId)
            activity?.startActivity(intent)
        }

        Glide.with(holder.itemView.context)
                .load(mCourse[position].imagePath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(holder.imgPoster)
    }

    class AcademyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.tv_item_title
        val tvDescription: TextView = itemView.tv_item_description
        val tvDate: TextView = itemView.tv_item_date
        val imgPoster: ImageView = itemView.img_poster
    }

}