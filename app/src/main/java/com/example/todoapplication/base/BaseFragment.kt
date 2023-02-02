package com.example.todoapplication.base

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import androidx.fragment.app.Fragment

// open to be public
open class BaseFragment : Fragment() {
    var progressDialog: ProgressDialog? = null
    var AlertDialog: AlertDialog? = null

    fun showLoadingDialog() {
        progressDialog = ProgressDialog(context)
        progressDialog!!.setMessage("Loading...")
        progressDialog!!.show()
    }

    fun hideLoading() {
        progressDialog!!.dismiss()
    }


    fun showMessage(
        message: String,
        positiveActionTitle: String? = null,
        positiveAction: DialogInterface.OnClickListener? = null,
        negativeActionTitle: String? = null,
        negativeAction: DialogInterface.OnClickListener? = null,
        cancelable: Boolean = true
    ) {
        var messageDialogBuilder = android.app.AlertDialog.Builder(context)
        messageDialogBuilder.setMessage(message)

        if (positiveActionTitle != null) {
            messageDialogBuilder.setPositiveButton(
                positiveActionTitle,
                positiveAction ?: DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
        }
        if (negativeActionTitle != null) {
            messageDialogBuilder.setNegativeButton(
                negativeActionTitle,
                negativeAction ?: DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
        }
        messageDialogBuilder.setCancelable(cancelable)
        AlertDialog = messageDialogBuilder.show()

    }

}