package com.example.stresscoping.view

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.DialogFragment
import com.example.stresscoping.R

class StressCopingAddDialogFragment : DialogFragment() {

    interface Listener {
        fun onClickOkOnAddDialog(title: String)
    }

    var listener: Listener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val editText = AppCompatEditText(it).apply {
                setHint(R.string.hint_add_dialog)
            }
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.title_stress_coping_add_dialog)
                .setMessage(R.string.msg_stress_coping_add_dialog)
                .setView(editText)
                .setPositiveButton(R.string.btn_ok) { dialog, _ ->
                    // OKボタンを押したときの処理
                    val title = editText.text.toString()
                    listener?.onClickOkOnAddDialog(title)

                    dialog.dismiss()
                }
                .setNegativeButton(R.string.btn_cancel) { _, _ -> }
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}