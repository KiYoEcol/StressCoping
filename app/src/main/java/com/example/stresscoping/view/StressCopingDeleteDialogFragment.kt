package com.example.stresscoping.view

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.stresscoping.R
import com.example.stresscoping.model.StressCopingModel

class StressCopingDeleteDialogFragment : DialogFragment() {
    interface Listener {
        fun onClickDeleteOnDeleteDialog(model: StressCopingModel)
    }

    var listener: Listener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val json = arguments?.getString(StressCopingModel.KEY_STRESS_COPING_MODEL)
                ?: throw IllegalStateException("argument cannot be null")
            val model = StressCopingModel.fromJson(json)

            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.title_stress_coping_add_dialog)
                .setMessage(model.title)
                .setPositiveButton(R.string.btn_cancel) { _, _ -> }
                .setNegativeButton(R.string.btn_delete) { dialog, _ ->
                    listener?.onClickDeleteOnDeleteDialog(model)
                    dialog.dismiss()
                }
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}