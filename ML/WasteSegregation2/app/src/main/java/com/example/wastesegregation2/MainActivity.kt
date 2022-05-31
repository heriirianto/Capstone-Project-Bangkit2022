package com.example.wastesegregation2

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.wastesegregation2.ml.WsmodelGAPQuant2
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder

class MainActivity : AppCompatActivity() {

    lateinit var bitmap: Bitmap
    lateinit var imgview: ImageView
    val label = arrayOf("Non-Recyclable", "Organic", "Recyclable")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgview = findViewById(R.id.imageView)

        var tv: TextView = findViewById(R.id.resultView)
        var select: Button = findViewById(R.id.selectButton)

        select.setOnClickListener(View.OnClickListener {
            var intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 100)
        })

        var predict: Button = findViewById(R.id.predictButton)

        predict.setOnClickListener(View.OnClickListener {

            // Convert Bitmap to ByteBuffer
            var buffer = convertBitmapToByteBuffer(bitmap)
            val model = WsmodelGAPQuant2.newInstance(this)

            // Creates inputs for reference.
            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)
            inputFeature0.loadBuffer(buffer)

            // Runs model inference and gets result.
            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer

            // Obtain position of max value
            var maxValue = getMaxValue(outputFeature0.floatArray)

            // Get the predicted label
            tv.setText(label[maxValue] + " " +outputFeature0.floatArray[maxValue])

            // Releases model resources if no longer used.
            model.close()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        imgview.setImageURI(data?.data)

        var uri: Uri? = data?.data
        bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
    }

    // To find the position of max value in the array
    fun getMaxValue(arr: FloatArray): Int {
        var index = 0
        var value = 0.0f

        for (i in 0..2) {
            if (arr[i] > value) {
                index = i
                value = arr[i]
            }
        }
        return index
    }

    private fun convertBitmapToByteBuffer(bp: Bitmap): ByteBuffer {
        // Allocate ByteBuffer
        val bufferImage: ByteBuffer = ByteBuffer.allocateDirect(java.lang.Float.BYTES * 224 * 224 * 3)
        bufferImage.order(ByteOrder.nativeOrder())

        // Rescale the input image into (224, 224, 3) size
        val bitmap = Bitmap.createScaledBitmap(bp, 224, 224, true)
        val arrayValues = IntArray(224 * 224)
        bitmap.getPixels(arrayValues, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)

        // Dividing each pixels by 255. to normalize the value
        var pixel = 0
        for (i in 0..223) {
            for (j in 0..223) {
                val value = arrayValues[pixel++]
                bufferImage.putFloat((value shr 16 and 0xFF) / 255f)
                bufferImage.putFloat((value shr 8 and 0xFF) / 255f)
                bufferImage.putFloat((value and 0xFF) / 255f)
            }
        }

        // Return image in normalize value ByteBuffer
        return bufferImage
    }
}