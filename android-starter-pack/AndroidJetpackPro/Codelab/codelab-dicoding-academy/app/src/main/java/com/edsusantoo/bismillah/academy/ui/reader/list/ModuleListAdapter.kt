package com.edsusantoo.bismillah.academy.ui.reader.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edsusantoo.bismillah.academy.R
import com.edsusantoo.bismillah.academy.data.ModuleEntity
import kotlinx.android.synthetic.main.items_module_list_custom.view.*


class ModuleListAdapter(private val listener: MyAdapterClickListener) :
        RecyclerView.Adapter<ModuleListAdapter.ModuleListViewHolder>() {

    private val modules = ArrayList<ModuleEntity>()

    fun setModules(modules: List<ModuleEntity>?) {
        if (modules == null) return
        this.modules.clear()
        this.modules.addAll(modules)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleListViewHolder {
        return ModuleListViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.items_module_list_custom,
                        parent,
                        false
                )
        )
    }

    override fun getItemCount(): Int {
        return modules.size
    }

    override fun onBindViewHolder(holder: ModuleListViewHolder, position: Int) {
        val module = modules[position]
        holder.bind(module.mTitle)
        holder.itemView.setOnClickListener {
            listener.onItemClicked(holder.adapterPosition, modules[position].mModuleId)
        }
    }

    class ModuleListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle: TextView = view.text_module_title
        val tvLastSeen: TextView = view.text_last_seen

        fun bind(title: String) {
            tvTitle.text = title
        }

    }


    interface MyAdapterClickListener {
        fun onItemClicked(position: Int, moduleId: String)
    }

}