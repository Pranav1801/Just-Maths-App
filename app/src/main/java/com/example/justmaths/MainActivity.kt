package com.example.justmaths

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val shareButton = findViewById<ImageButton>(R.id.share_button)
        shareButton.setOnClickListener {
            onShareClick()
        }
    }

    fun onNextClick(view: View) {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    private fun onShareClick() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT,"Hi There!!!!!")
        intent.putExtra(Intent.EXTRA_TEXT, "Play Just Maths with me")
        startActivity(Intent.createChooser(intent,"Share via"))
    }
}
