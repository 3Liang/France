package com.wang.franch.ui

import android.app.Activity
import android.content.Intent
import android.graphics.*
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.FileProvider
import com.wang.franch.R
import com.wang.franch.base.ActivityBase
import com.wang.franch.databinding.ActivityImageCropBinding
import com.wang.franch.helper.FileHelper
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


/**
 * 图片裁剪页面
 *
 * @Author Leiyu
 * @CreateDate 2021/2/24
 */
class ActivityImageCrop : ActivityBase() {
    private lateinit var tempFile: File
    private lateinit var binding: ActivityImageCropBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tempFile = File(getExternalFilesDir("img"), "temp.jpg")
        binding = ActivityImageCropBinding.inflate(layoutInflater)
        binding.toolbarInfo = toolbarInfo()?.also {
            it.rightText(getString(R.string.confirm))
            it.rightTextClickListener(View.OnClickListener { confirm() })
        }
        setContentView(binding.root)
        takePhoto()
    }

    private fun confirm() {
        if (binding.ivCrop.canRightCrop()) {
            val crop: Bitmap? = binding.ivCrop.crop()
            if (crop != null) {
                val resultFile = FileHelper.imageFile(this)
                resultFile.createNewFile()
                saveImage(crop, resultFile)
                intent.putExtra("result", resultFile.path)
                setResult(Activity.RESULT_OK, intent)
            } else {
                setResult(Activity.RESULT_CANCELED)
            }
            finish()
        } else {
            Toast.makeText(this, "cannot crop correctly", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveImage(
        bitmap: Bitmap,
        saveFile: File
    ) {
        try {
            val fos = FileOutputStream(saveFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.flush()
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            setResult(Activity.RESULT_CANCELED)
            finish()
            return
        }
        var selectedBitmap: Bitmap? = null
        if (requestCode == 110 && tempFile.exists()) {
            val degrees: Float = getBitmapDegree(tempFile.path)
            val options: BitmapFactory.Options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeFile(tempFile.path, options)
            options.inJustDecodeBounds = false
            options.inSampleSize = calculateSampleSize(options)
            val bitmap = BitmapFactory.decodeFile(tempFile.path, options)
            selectedBitmap = rotateBitmap(bitmap!!, degrees)
        }
        if (selectedBitmap != null) {
            binding.ivCrop.setImageToCrop(selectedBitmap)
        }
    }



    private fun takePhoto() {
        val startCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val uri: Uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            FileProvider.getUriForFile(this, "com.wang.franch.fileProvider", tempFile)
        } else {
            Uri.fromFile(tempFile)
        }
        startCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        if (startCameraIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(startCameraIntent, 110)
        }
    }

    private fun calculateSampleSize(options: BitmapFactory.Options): Int {
        val outHeight = options.outHeight
        val outWidth = options.outWidth
        var sampleSize = 1
        val destHeight = 1000
        val destWidth = 1000
        if (outHeight > destHeight || outWidth > destHeight) {
            sampleSize = if (outHeight > outWidth) {
                outHeight / destHeight
            } else {
                outWidth / destWidth
            }
        }
        if (sampleSize < 1) {
            sampleSize = 1
        }
        return sampleSize
    }

    /**
     * 读取图片的旋转的角度
     *
     * @param path 图片绝对路径
     * @return 图片的旋转角度
     */
    fun getBitmapDegree(path: String): Float {
        var degree = 0f
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            val exifInterface = ExifInterface(path)
            // 获取图片的旋转信息
            val orientation: Int = exifInterface.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )
            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> degree = 90f
                ExifInterface.ORIENTATION_ROTATE_180 -> degree = 180f
                ExifInterface.ORIENTATION_ROTATE_270 -> degree = 270f
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return degree
    }

    /**
     * 旋转图片，使图片保持正确的方向。
     *
     * @param bitmap  原始图片
     * @param degrees 原始图片的角度
     * @return Bitmap 旋转后的图片
     */
    fun rotateBitmap(bitmap: Bitmap, degrees: Float): Bitmap? {
        if (degrees == 0f) {
            return bitmap
        }
        val matrix = Matrix()
        matrix.setRotate(degrees, bitmap.width / 2f, bitmap.height / 2f)
        val bmp = Bitmap.createBitmap(
            bitmap,
            0,
            0,
            bitmap.width,
            bitmap.height,
            matrix,
            true
        )
        bitmap.recycle()
        return bmp
    }
}