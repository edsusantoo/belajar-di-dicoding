package com.edsusantoo.bismillah.mynote.ui.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.edsusantoo.bismillah.mynote.R
import com.edsusantoo.bismillah.mynote.database.Note
import com.edsusantoo.bismillah.mynote.helper.NoteDiffCallback
import com.edsusantoo.bismillah.mynote.ui.insert.NoteAddUpdateActivity


class NoteAdapter(private val context: Activity) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private val listNote = ArrayList<Note>()

    fun setListNotes(listNote: List<Note>) {
        val diffCallback = NoteDiffCallback(this.listNote, listNote)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.listNote.clear()
        this.listNote.addAll(listNote)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))
    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listNote[position], context)
    }

    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle: TextView = view.findViewById(R.id.tv_item_title)
        private val tvDescription: TextView = view.findViewById(R.id.tv_item_description)
        private val tvDate: TextView = view.findViewById(R.id.tv_item_date)

        private val cvNote: CardView = view.findViewById(R.id.cv_item_note)

        fun bind(note: Note, context: Activity) {
            tvTitle.text = note.title
            tvDate.text = note.date
            tvDescription.text = note.description

            cvNote.setOnClickListener {
                val intent = Intent(context, NoteAddUpdateActivity::class.java)
                intent.putExtra(NoteAddUpdateActivity.EXTRA_POSITION, adapterPosition)
                intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, note)
                context.startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_UPDATE)
            }
        }
    }


}