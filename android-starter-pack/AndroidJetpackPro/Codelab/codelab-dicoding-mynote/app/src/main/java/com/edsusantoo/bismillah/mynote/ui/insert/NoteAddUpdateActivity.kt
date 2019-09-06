package com.edsusantoo.bismillah.mynote.ui.insert

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.edsusantoo.bismillah.mynote.R
import com.edsusantoo.bismillah.mynote.database.Note
import com.edsusantoo.bismillah.mynote.helper.DateHelper
import com.edsusantoo.bismillah.mynote.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_note_add_update.*


class NoteAddUpdateActivity : AppCompatActivity() {

    private var isEdit = false
    private var position = 0
    private var note: Note? = null

    private lateinit var noteAddUpdateViewModel: NoteAddUpdateViewModel

    companion object {
        const val REQUEST_ADD = 100
        const val RESULT_ADD = 101
        const val REQUEST_UPDATE = 200
        const val RESLUT_UPDATE = 201
        const val RESULT_DELETE = 301

        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20

        const val EXTRA_NOTE = "extra_note"
        const val EXTRA_POSITION = "extra_position"

        private fun obtainViewModel(activity: AppCompatActivity): NoteAddUpdateViewModel {
            val factory = ViewModelFactory.getInstance(activity.application)
            return ViewModelProviders.of(activity, factory).get(NoteAddUpdateViewModel::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_add_update)

        noteAddUpdateViewModel = obtainViewModel(this)

        note = intent.getParcelableExtra(EXTRA_NOTE)
        if (note != null) {
            position = intent.getIntExtra(EXTRA_POSITION, 0)
            isEdit = true
        }

        val actionBarTitle: String?
        val btnTitle: String?

        if (isEdit) {
            actionBarTitle = getString(R.string.change)
            btnTitle = getString(R.string.update)

            if (note != null) {
                edt_title.setText(note?.title)
                edt_description.setText(note?.description)
            }
        } else {
            actionBarTitle = getString(R.string.add)
            btnTitle = getString(R.string.save)
        }

        if (actionBar != null) {
            actionBar?.title = actionBarTitle
            actionBar?.setDisplayHomeAsUpEnabled(true)
        }

        btn_submit.text = btnTitle

        btn_submit.setOnClickListener {

            val title = edt_title.text.toString().trim()
            val desciption = edt_description.text.toString().trim()

            when {
                title.isEmpty() -> edt_title.error = getString(R.string.empty)
                desciption.isEmpty() -> edt_description.error = getString(R.string.empty)
                else -> {
                    note?.title = title
                    note?.description = desciption

                    val intent = Intent()
                    intent.putExtra(EXTRA_NOTE, note)
                    intent.putExtra(EXTRA_POSITION, position)

                    if (isEdit) {
                        noteAddUpdateViewModel.update(note)
                        setResult(RESLUT_UPDATE, intent)
                    } else {
                        note?.date = DateHelper.getCurrentDate()
                        noteAddUpdateViewModel.insert(note)
                        setResult(RESULT_ADD, intent)
                        finish()
                    }
                }
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.action_delete -> showAlertDialog(ALERT_DIALOG_DELETE)
            R.id.home -> showAlertDialog(ALERT_DIALOG_CLOSE)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        showAlertDialog(ALERT_DIALOG_CLOSE)
    }

    private fun showAlertDialog(type: Int) {
        val isDialogClose = type == ALERT_DIALOG_CLOSE
        val dialogTitle: String
        val dialogMessage: String

        if (isDialogClose) {
            dialogTitle = getString(R.string.cancel)
            dialogMessage = getString(R.string.message_cancel)
        } else {
            dialogMessage = getString(R.string.message_delete)
            dialogTitle = getString(R.string.delete)
        }

        val alertDialogBuilder = AlertDialog.Builder(this)

        alertDialogBuilder.setTitle(dialogTitle)
        alertDialogBuilder
            .setMessage(dialogMessage)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                if (isDialogClose) {
                    finish()
                } else {
                    noteAddUpdateViewModel.delete(note)

                    val intent = Intent()
                    intent.putExtra(EXTRA_POSITION, position)
                    setResult(RESULT_DELETE, intent)
                    finish()

                }
            }
            .setNegativeButton(getString(R.string.no)) { dialog, _ -> dialog.cancel() }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()

    }
}
