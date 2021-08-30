package com.example.practicetask1.view.fragments

import android.content.Context.MODE_PRIVATE
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import com.example.practicetask1.R
import com.example.practicetask1.contracts.PhotoDialogOwner
import java.io.IOException

class ProfileFragment : Fragment(), PhotoDialogOwner {

    private var profilePhoto: ImageView? = null

    private val takePhotoLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
            savePhotoToInternalStorage(bitmap)
            setProfilePhoto(bitmap, emptyPhoto = false)
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profilePhoto = view.findViewById(R.id.profile_photo)
        setProfilePhoto(loadProfilePhotoFromInternalStorage(), isProfilePhotoDeleted())
        profilePhoto?.setOnClickListener {
            val dialog = PhotoDialogFragment(this)
            dialog.show(parentFragmentManager, "PhotoDialogFragment")
        }
    }

    private fun deleteProfilePhoto(): Boolean {
        return try {
            requireActivity().deleteFile("$PROFILE_PHOTO_FILE_NAME.jpg")
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    }

    private fun isProfilePhotoDeleted(): Boolean {
        return requireActivity().filesDir.listFiles().isNullOrEmpty()
    }

    private fun loadProfilePhotoFromInternalStorage(): Bitmap {
        val photoFile = requireActivity().filesDir.listFiles()?.find { file ->
            file.name == "$PROFILE_PHOTO_FILE_NAME.jpg"
        }?.also { file ->
            val bytes = file.readBytes()
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        }
        return ContextCompat.getDrawable(requireActivity(), R.drawable.empty_profile_picture)!!
            .toBitmap()
    }

    private fun savePhotoToInternalStorage(bmp: Bitmap): Boolean {
        return try {
            requireActivity().openFileOutput("$PROFILE_PHOTO_FILE_NAME.jpg", MODE_PRIVATE)
                .use { stream ->
                    if (!bmp.compress(Bitmap.CompressFormat.JPEG, 95, stream)) {
                        throw IOException()
                    }
                }
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

    private fun setProfilePhoto(bitmap: Bitmap, emptyPhoto: Boolean) {
        profilePhoto?.setImageBitmap(bitmap)
        if (emptyPhoto) {
            profilePhoto?.scaleType = ImageView.ScaleType.CENTER_INSIDE
        } else {
            profilePhoto?.scaleType = ImageView.ScaleType.FIT_CENTER
        }
    }

    override fun choosePhoto() {
        Toast.makeText(requireContext(), R.string.choose_photo, Toast.LENGTH_LONG).show()
    }

    override fun takePhoto() {
        takePhotoLauncher.launch()
    }

    override fun deletePhoto() {
        deleteProfilePhoto()
        setProfilePhoto(loadProfilePhotoFromInternalStorage(), emptyPhoto = true)
    }

    companion object {
        private const val PROFILE_PHOTO_FILE_NAME = "profile_photo"
    }
}
