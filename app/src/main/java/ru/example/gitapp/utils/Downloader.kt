package ru.example.gitapp.utils

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.net.URL


fun saveImage(context: Context, image: Bitmap, imageName: String?) {
    val foStream: FileOutputStream
    try {
        foStream = context.openFileOutput(imageName + ".png", Context.MODE_PRIVATE)
        image.compress(Bitmap.CompressFormat.PNG, 100, foStream)
        Log.d("HAPPY", "Save file")
        foStream.close()
    } catch (e: Exception) {
        Log.d("HAPPY", "Exception, Something went wrong!")
        e.printStackTrace()
    }
}

fun downloadImageBitmap(url: String): Bitmap? {
    var bitmap: Bitmap? = null
    try {
        val inputStream: InputStream = URL(url).openStream() // Download Image from URL
        bitmap = BitmapFactory.decodeStream(inputStream) // Decode Bitmap
        inputStream.close()
    } catch (e: java.lang.Exception) {
        Log.d("HAPPY", "Exception 1, Something went wrong!")
        e.printStackTrace()
    }
    return bitmap
}

//"my_image.png"
fun onLoadBitmap(context: Context, result: Bitmap?, name: String) {
    saveImage(context, result!!, name)
}

fun getImagePath(context: Context, imageName: String): String {
    return context.getFileStreamPath(imageName).absolutePath

}

private fun saveMediaToStorage(context: Context, bitmap: Bitmap?, imageName: String) {
    val filename = "${imageName}.jpg"
    var outputStream: OutputStream? = null
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        context.contentResolver?.also { resolver ->
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            }
            val imageUri: Uri? =
                resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            outputStream = imageUri?.let { resolver.openOutputStream(it) }
        }
    } else {
        val imagesDir =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val image = File(imagesDir, filename)
        outputStream = FileOutputStream(image)
    }
    outputStream?.use {
        bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, it)
        Toast.makeText(context, "Saved to Gallery", Toast.LENGTH_SHORT).show()
    }
}