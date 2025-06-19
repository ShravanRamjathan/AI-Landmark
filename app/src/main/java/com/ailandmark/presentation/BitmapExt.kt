package com.ailandmark.presentation

import android.graphics.Bitmap
import android.icu.number.IntegerWidth

/**
 * This function calculates the starting coordinates to crop the bitmap from its center to the specified
 */
fun Bitmap.centerCrop(desiredWidth: Int, desiredHeight:Int): Bitmap{
    val xStart = (width-desiredWidth)/2
    val yStart = (height-desiredHeight)/2
    if(xStart<0||yStart<0||desiredWidth>width||desiredHeight>height){
       throw IllegalArgumentException("Invalid arguments for center cropping")

    }
    return Bitmap.createBitmap(this, xStart,yStart,desiredWidth,desiredHeight)
}