package com.tashariko.imageinfo

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build

object ImageHelper {

    fun decodeSampledBitmapFromResource(res: Resources, resId: Int): Bitmap? {

        val options = BitmapFactory.Options()
        // First decode with inJustDecodeBounds=true to check dimensions and can query the bitmap without having to allocate the memory
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(res, resId, options)

        // Calculate inSampleSize
        val reqHeight = 800
        val reqWidth = 600

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)
        // Decode bitmap with inSampleSize set and alot memory for the bitmap as we are retuning it.
        options.inJustDecodeBounds = false

        return  BitmapFactory.decodeResource(res, resId, options).copy(Bitmap.Config.RGB_565, true);

    }


    private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        // Raw height and width of image
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1
        if (height > reqHeight || width > reqWidth) {
            val halfHeight = height / 2
            val halfWidth = width / 2

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width. We are going for largest
            // sample size because it defines the required bounds of our bitmap
            while (halfHeight / inSampleSize >= reqHeight
                && halfWidth / inSampleSize >= reqWidth
            ) {
                inSampleSize *= 2
            }
        }

        return inSampleSize
    }



    fun byteSizeOf(bitmap: Bitmap): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            bitmap.allocationByteCount
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            bitmap.byteCount
        } else {
            bitmap.rowBytes * bitmap.height
        }
    }
}