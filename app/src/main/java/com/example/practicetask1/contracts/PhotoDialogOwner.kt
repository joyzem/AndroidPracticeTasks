package com.example.practicetask1.contracts

import android.graphics.Bitmap

interface PhotoDialogOwner {
    fun choosePhoto()
    fun usePhoto(bitmapPicture: Bitmap)
    fun deletePhoto()
}
