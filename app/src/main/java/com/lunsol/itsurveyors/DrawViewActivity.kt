package com.lunsol.itsurveyors

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView

/**
 * Created by abcd7 on 2018/02/25.
 */
class DrawViewActivity: AppCompatActivity() {
    private var imageView: TouchImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.draw_view)
        val imageURL: String = intent.getStringExtra("path")
        val bitmap = BitmapFactory.decodeFile(imageURL)
        imageView = findViewById<ImageView>(R.id.imageView) as TouchImageView
        imageView!!.setImageBitmap(bitmap)
        imageView!!.setOnTouchImageViewListener(object : TouchImageView.OnTouchImageViewListener {
            override fun onMove() {
                val point = imageView!!.scrollPosition
                val rect = imageView!!.zoomedRect
                val currentZoom = imageView!!.currentZoom
                val isZoomed = imageView!!.isZoomed
            }
        })
        }


}