package com.edsusantoo.bismillah.academy.ui.reader.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edsusantoo.bismillah.academy.R
import com.edsusantoo.bismillah.academy.data.source.local.entity.ModuleEntity
import kotlinx.android.synthetic.main.items_module_list_custom.view.*


class ModuleListAdapter(private val listener: MyAdapterClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val modules = ArrayList<ModuleEntity>()

    fun setModules(modules: List<ModuleEntity>?) {
        if (modules == null) return
        this.modules.clear()
        this.modules.addAll(modules)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            ModuleListHideViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.items_module_list_custom_disable,
                    parent,
                    false
                )
            )
        } else {
            ModuleListViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.items_module_list_custom,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return modules.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val module = modules[position]
        if (holder.itemViewType == 0) {
            val moduleViewHolderHide = holder as ModuleListHideViewHolder
            moduleViewHolderHide.bind(module.title)
        } else {
            val moduleViewHolder = holder as ModuleListViewHolder
            moduleViewHolder.bind(module.title)
            moduleViewHolder.itemView.setOnClickListener {
                listener.onItemClicked(
                    holder.getAdapterPosition(),
                    modules[moduleViewHolder.adapterPosition].moduleId
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val modulePosition = modules[position].position
        return when {
            modulePosition == 0 -> 1
            modules[modulePosition - 1].mRead!! -> 1
            else -> 0
        }

    }


    class ModuleListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle: TextView = view.text_module_title

        fun bind(title: String) {
            tvTitle.text = title
        }

    }

    class ModuleListHideViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle: TextView = view.text_module_title

        fun bind(title: String) {
            tvTitle.text = title
        }

    }


    interface MyAdapterClickListener {
        fun onItemClicked(position: Int, moduleId: String)
    }

}