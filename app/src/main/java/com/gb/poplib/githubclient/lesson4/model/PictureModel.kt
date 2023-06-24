package com.gb.poplib.githubclient.lesson4.model

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import com.gb.poplib.githubclient.App
import java.io.FileOutputStream

class PictureModel {

    fun getPictureFromDrawable(idDrawable: Int) =
        BitmapFactory.decodeResource(App.instance.resources, idDrawable)

    fun setPictureInDrawable(bitmap: Bitmap) = BitmapDrawable(App.instance.resources, bitmap)

    fun getPictureFromFile(path: String) = BitmapFactory.decodeFile(path)

    fun setPicturePngOnFile (bitmap: Bitmap, path: String) {
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, FileOutputStream(path))
    }

}