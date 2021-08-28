package com.example.practicetask1.view.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.practicetask1.R
import com.example.practicetask1.contracts.PhotoDialogOwner

class ProfileFragment : Fragment(), PhotoDialogOwner {

    private var profilePhoto: ImageView? = null

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
        profilePhoto?.setOnClickListener {
            val dialog = PhotoDialogFragment(this)
            dialog.show(parentFragmentManager, "PhotoDialogFragment")
        }
    }

    override fun choosePhoto() {
        Toast.makeText(requireContext(), R.string.choose_photo, Toast.LENGTH_LONG).show()
    }

    override fun usePhoto(bitmapPicture: Bitmap) {
        profilePhoto?.apply {
            setImageBitmap(bitmapPicture)
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
    }

    override fun deletePhoto() {
        profilePhoto?.apply {
            setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.empty_profile_picture
                )
            )
            scaleType = ImageView.ScaleType.CENTER_INSIDE
            setBackgroundResource(R.color.light_grey2)
        }
    }
}
