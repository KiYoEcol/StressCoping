package com.example.stresscoping.view

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.stresscoping.R

class StressCopingsDeleteDialogFragment : DialogFragment() {
    interface Listener {
        fun deleteStressCopings()
    }

    companion object {
        val NUMBER_OF_DELETED_STRESS_COPINGS = "number_of_deleted_stress_copings"
    }

    var listener: Listener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val number = arguments?.getInt(NUMBER_OF_DELETED_STRESS_COPINGS, 0)
        return activity?.let {
            val builder = AlertDialog.Builder(it, R.style.StressCopingDeleteDialogFragment)
            builder.setTitle(R.string.title_stress_coping_delete_dialog)
                .setMessage(getString(R.string.msg_stress_copings_delete_dialog, number))
                .setPositiveButton(R.string.btn_cancel) { _, _ -> }
                .setNegativeButton(R.string.btn_delete) { dialog, _ ->
                    listener?.deleteStressCopings()
                    dialog.dismiss()
                }

            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}