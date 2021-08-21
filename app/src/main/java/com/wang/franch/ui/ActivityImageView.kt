package com.wang.franch.ui

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.*
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.children
import androidx.lifecycle.Observer
import androidx.viewpager.widget.PagerAdapter
import com.wang.franch.R
import com.wang.franch.base.ActivityBase
import com.wang.franch.databinding.ActivityImageViewBinding
import com.wang.franch.helper.FileHelper
import com.wang.franch.viewmodel.BusinessViewModel
import java.io.FileOutputStream

/**
 * 选择图片页面
 *
 * @Author Leiyu
 * @CreateDate 2021/2/24
 */
class ActivityImageView : ActivityBase() {
    private lateinit var binding: ActivityImageViewBinding
    private val adapter = ImageAdapter()
    private lateinit var viewModel: BusinessViewModel
    private lateinit var dialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageViewBinding.inflate(layoutInflater)
        binding.ivTakePhoto.setOnClickListener { checkAndRequestPermission(android.Manifest.permission.CAMERA) }
        binding.ivUpload.setOnClickListener { createPdfFile() }
        binding.viewPager.offscreenPageLimit = Int.MAX_VALUE
        binding.viewPager.adapter = adapter
        setContentView(binding.root)
        viewModel = BusinessViewModel()
        viewModel.uploadResult.observe(this, Observer {
            if (it) {
                Toast.makeText(this, getString(R.string.upload_successfully), Toast.LENGTH_SHORT)
                    .show()
                dialog.dismiss()
                finish()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        data?.getStringExtra("result")?.let {
            adapter.add(it)
            binding.viewPager.currentItem = adapter.count - 1
            binding.ivUpload.visibility = View.VISIBLE
        }
    }

    override fun onPermissionGranted(permission: String?) {
        startActivityForResult(Intent(this, ActivityImageCrop::class.java), 120)
    }

    private class ImageAdapter : PagerAdapter() {

        private val images = ArrayList<String>()

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val imageView = ImageView(container.context)
            val bitmap = BitmapFactory.decodeFile(images[position])
            imageView.setImageBitmap(photoEffect(bitmap))
            container.addView(imageView)
            return imageView
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun getCount(): Int {
            return images.size
        }

        fun add(item: String) {
            images.add(item)
            notifyDataSetChanged()
        }

        private fun photoEffect(bitmap: Bitmap): Bitmap {
            val bmp = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bmp)
            val paint = Paint(Paint.ANTI_ALIAS_FLAG)
//            val hueMatrix = ColorMatrix()
//            hueMatrix.set(
//                floatArrayOf(
//                    0.9f, 0f, 0f, 0f, 0f,
//                    0f, 0.9f, 0f, 0f, 0f,
//                    0f, 0f, 0.9f, 0f, 0f,
//                    0f, 0f, 0f, 1f, 0f
//                )
//            )
//            val saturationMatrix = ColorMatrix()
//            saturationMatrix.setSaturation(0f)
            val lumMatrix = ColorMatrix()
            lumMatrix.setScale(1.1f, 1.1f, 1.1f, 1f)
//            val colorMatrix = ColorMatrix()
//            colorMatrix.postConcat(hueMatrix)
//            colorMatrix.postConcat(saturationMatrix)
//            colorMatrix.postConcat(lumMatrix)
            paint.colorFilter = ColorMatrixColorFilter(lumMatrix)
            canvas.drawBitmap(bitmap, 0f, 0f, paint)
            return bmp
        }

        private fun replaceBitmapColor(oldBitmap: Bitmap): Bitmap? {
            val mBitmap = oldBitmap.copy(Bitmap.Config.ARGB_8888, true)
            for (i in 0 until mBitmap.height) {
                for (j in 0 until mBitmap.width) {
                    //获得Bitmap 图片中每一个点的color颜色值
                    //将需要填充的颜色值如果不是
                    //在这说明一下 如果color 是全透明 或者全黑 返回值为 0
                    //getPixel()不带透明通道 getPixel32()才带透明部分 所以全透明是0x00000000
                    //而不透明黑色是0xFF000000 如果不计算透明部分就都是0了
                    val color = mBitmap.getPixel(j, i)
                    //将颜色值存在一个数组中 方便后面修改
                    if (color < Color.GRAY) {
                        mBitmap.setPixel(j, i, Color.BLACK)
                    } else {
                        mBitmap.setPixel(j, i, Color.WHITE)
                    }
                }
            }
            return mBitmap
        }
    }

    private fun createPdfFile() {
        dialog = ProgressDialog.show(this, "", getString(R.string.uploading))
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(
            binding.viewPager.width,
            binding.viewPager.height,
            adapter.count
        ).create()
        for (view in binding.viewPager.children) {
            val page = pdfDocument.startPage(pageInfo)
            val canvas = page.canvas
            view.draw(canvas)
            pdfDocument.finishPage(page)
        }
        val file = FileHelper.pdfFile(this)
        val fos = FileOutputStream(file)
        pdfDocument.writeTo(fos)
        fos.close()
        pdfDocument.close()
        viewModel.uploadReceipt(file.path)
    }

}