package com.playrion.picturesviewer.extensions

import android.app.AlertDialog
import androidx.fragment.app.Fragment
import com.playrion.picturesviewer.R

fun Fragment.showError(message: String, action: () -> Unit) {
    AlertDialog.Builder(requireContext()).setTitle(message)
        .setPositiveButton(getString(R.string.retry)) { dialog, _ ->
            dialog.dismiss()
            action()
        }.create().show()
}