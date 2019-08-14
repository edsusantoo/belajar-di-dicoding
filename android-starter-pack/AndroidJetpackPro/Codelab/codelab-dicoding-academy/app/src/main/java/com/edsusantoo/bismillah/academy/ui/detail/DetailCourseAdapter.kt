package com.edsusantoo.bismillah.academy.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edsusantoo.bismillah.academy.R
import com.edsusantoo.bismillah.academy.data.ModuleEntity
import kotlinx.android.synthetic.main.items_module_list.view.*

class DetailCourseAdapter : RecyclerView.Adapter<DetailCourseAdapter.DetailCourseViewHolder>() {

    private val mModules = ArrayList<ModuleEntity>()

    fun setModules(modules: List<ModuleEntity>?) {
        if (modules == null) return
        mModules.clear()
        mModules.addAll(modules)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailCourseViewHolder {
        return DetailCourseViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.items_module_list,
                        parent,
                        false
                )
        )
    }

    override fun getItemCount(): Int {
        return mModules.size
    }

    override fun onBindViewHolder(holder: DetailCourseViewHolder, position: Int) {
        holder.bind(mModules[position].mTitle)
    }

    class DetailCourseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle: TextView = view.text_module_title

        fun bind(title: String) {
            tvTitle.text = title
        }
    }
}