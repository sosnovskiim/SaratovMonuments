package com.sosnowskydevelop.saratovmonuments.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.sosnowskydevelop.saratovmonuments.R

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        val imageView: ImageView = findViewById(R.id.imageView)
        val extras = intent.extras

        if (extras != null) {
            val title = extras.getString("title")
            supportActionBar?.title = title

            val imageId = extras.getInt("imageId")
            imageView.setImageResource(imageId)
        }
    }
}