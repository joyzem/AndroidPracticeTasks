package com.example.practicetask1.view.fragments

import android.Manifest
import android.app.AlertDialog
import android.app.Dialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.fragment.app.DialogFragment
import com.example.practicetask1.R
import com.example.practicetask1.contracts.PhotoDialogOwner

class PhotoDialogFragment(private val dialogOwner: PhotoDialogOwner) : DialogFragment() {

    private val requestPermissionLauncher =
        registerForActivityResult(RequestPermission()) { isGranted ->
            if (isGranted) {
                dialogOwner.takePhoto()
            } else {
                showExplainingUI()
            }
            dismiss()
        }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = activity?.layoutInflater
            val view = inflater?.inflate(R.layout.photo_dialog_fragment, null)
            view?.let { layout ->
                layout.findViewById<LinearLayout>(R.id.choose_photo_item)
                    .setOnClickListener {
                        dialogOwner.choosePhoto()
                        dismiss()
                    }

                layout.findViewById<LinearLayout>(R.id.take_photo_item)
                    .setOnClickListener {
                        if (hasCamera()) {
                            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
                        } else {
                            Toast.makeText(requireContext(), R.string.no_camera, Toast.LENGTH_LONG)
                                .show()
                        }
                    }

                layout.findViewById<LinearLayout>(R.id.delete_photo_item)
                    .setOnClickListener {
                        dialogOwner.deletePhoto()
                        dismiss()
                    }
            }
            builder.setView(view)
            builder.create()
        } ?: throw IllegalStateException()
    }

    private fun showExplainingUI() {
        Toast.makeText(requireContext(), R.string.explaining_message, Toast.LENGTH_LONG).show()
    }

    private fun hasCamera() = context?.packageManager?.hasSystemFeature(
        PackageManager.FEATURE_CAMERA_ANY
    ) ?: false
}
