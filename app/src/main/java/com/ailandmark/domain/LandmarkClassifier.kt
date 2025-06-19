package com.ailandmark.domain

import android.graphics.Bitmap

interface LandmarkClassifier {
    /**
     * We use this to classify the image
     * @param bitmap:Bitmap
     * @param rotation:Int
     */
    fun classify(bitmap: Bitmap, rotation:Int):List<Classification>

}