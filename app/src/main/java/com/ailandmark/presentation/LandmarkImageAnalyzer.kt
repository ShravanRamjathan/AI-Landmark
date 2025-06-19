package com.ailandmark.presentation

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.ailandmark.domain.Classification
import com.ailandmark.domain.LandmarkClassifier

/**
 * This analyzer will get called for each frame
 */
class LandmarkImageAnalyzer(private val classifier: LandmarkClassifier,
private val onResults:(List<Classification>)-> Unit): ImageAnalysis.Analyzer{

    override fun analyze(image: ImageProxy) {
        val rotationDegrees = image.imageInfo.rotationDegrees
        val bitmap = image.ce
    }
}