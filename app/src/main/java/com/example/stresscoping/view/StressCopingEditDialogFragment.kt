package com.example.stresscoping.view

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.DialogFragment
import com.example.stresscoping.R
import com.example.stresscoping.model.StressCopingModel

class StressCopingEditDialogFragment : DialogFragment() {

    interface Listener {
        fun onClickUpdateOnEditDialog(stressCoping: StressCopingModel)
    }

    var listener: Listener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val json = arguments?.getString(StressCopingModel.KEY_STRESS_COPING_MODEL)
                ?: throw IllegalStateException("arguments cannot be null")
            val stressCoping = StressCopingModel.fromJson(json)

            val editText = AppCompatEditText(it).apply {
                setText(stressCoping.title)
                setHint(R.string.hint_edit_dialog)
            }
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.title_stress_coping_edit_dialog)
                .setView(editText)
                .setPositiveButton(R.string.btn_update) { dialog, _ ->
                    // OKボタンを押したときの処理
                    val title = editText.text.toString()
                    val newStressCoping = StressCopingModel(stressCoping.id, title)
                    listener?.onClickUpdateOnEditDialog(newStressCoping)

                    dialog.dismiss()
                }
                .setNegativeButton(R.string.btn_cancel) { _, _ -> }
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}