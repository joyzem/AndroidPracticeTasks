package com.example.practicetask1.view.fragments

import android.Manifest
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.example.practicetask1.R
import com.example.practicetask1.contracts.PhotoDialogOwner

class PhotoDialogFragment(private val dialogOwner: PhotoDialogOwner) : DialogFragment() {

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                getBitmapLauncher.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
            } else {
                showExplainingUI()
            }
            dismiss()
        }

    private val getBitmapLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                dialogOwner.usePhoto(result.data?.extras?.get("data") as Bitmap)
            }
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
