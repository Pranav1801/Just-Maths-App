package com.example.justmaths

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
class ResultActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val shareButton = findViewById<ImageButton>(R.id.share_button)
        val finalScore = findViewById<TextView>(R.id.txt_final_score)
        val correctScore = findViewById<TextView>(R.id.txt_correct)
        val wrongScore = findViewById<TextView>(R.id.txt_wrong)
        val totalScore = findViewById<TextView>(R.id.txt_total_questions)

        val scoreResult = intent.getStringExtra("Score")
        val correctResult = intent.getStringExtra("Correct")
        val wrongResult = intent.getStringExtra("Wrong")
        val totalResult = intent.getStringExtra("Total")

        finalScore.text = "Total Score: $scoreResult"
        correctScore.text = "Correct Questions Answered: $correctResult"
        wrongScore.text = "Wrong Questions Answered: $wrongResult"
        totalScore.text = "Total Questions Attempted: $totalResult"

        shareButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT,"Hi There!!!!!")
            intent.putExtra(Intent.EXTRA_TEXT, "My Highest Score is $scoreResult \n Total Questions Attempted $totalResult")
            startActivity(Intent.createChooser(intent,"Share via"))
        }
    }
    fun onBackClick(view: View){
        val backButton = findViewById<TextView>(R.id.back_button)
        val intent = Intent(this, MainActivity::class.java)
        backButton.setOnClickListener{
            startActivity(intent)
        }
    }
}