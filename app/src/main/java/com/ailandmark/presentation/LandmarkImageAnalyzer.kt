package com.ailandmark.presentation

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.graphics.Rect
import android.graphics.YuvImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.ailandmark.domain.Classification
import com.ailandmark.domain.LandmarkClassifier
import java.io.ByteArrayOutputStream

/**
 * This analyzer will get called for each frame
 */
class LandmarkImageAnalyzer(private val classifier: LandmarkClassifier,
private val onResults:(List<Classification>)-> Unit): ImageAnalysis.Analyzer{

    override fun analyze(image: ImageProxy) {
        val rotationDegrees = image.imageInfo.rotationDegrees
        val bitmap = image.toBitmap()
    }

    /**
     * - Extracts Y, U, and V byte buffers from the `ImageProxy` planes.
     * - Combines these buffers into a single `ByteArray` in NV21 format.
     * - Creates a `YuvImage` from the NV21 data.
     * - Compresses the `YuvImage` to JPEG format.
     * - Decodes the JPEG byte array into a `Bitmap`.
     *
     */
    fun ImageProxy.toBitmap(): Bitmap {
        val yBuffer = planes[0].buffer
        val uBuffer = planes[1].buffer
        val vBuffer = planes[2].buffer

        val ySize = yBuffer.remaining()
        val uSize = uBuffer.remaining()
        val vSize = vBuffer.remaining()

        val nv21 = ByteArray(ySize + uSize + vSize)
        yBuffer.get(nv21, 0, ySize)
        vBuffer.get(nv21, ySize, vSize)
        uBuffer.get(nv21, ySize + vSize, uSize)

        val yuvImage = YuvImage(nv21, ImageFormat.NV21, width, height, null)
        val out = ByteArrayOutputStream()
        yuvImage.compressToJpeg(Rect(0, 0, width, height), 100, out)
        val jpegBytes = out.toByteArray()
        return BitmapFactory.decodeByteArray(jpegBytes, 0, jpegBytes.size)
    }
}



