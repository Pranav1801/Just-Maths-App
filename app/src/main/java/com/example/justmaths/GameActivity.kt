package com.example.justmaths

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    private var isResultCorrect: Boolean = false
    private var correctCount = 0
    private var wrongCount = 0
    private var totalCount = 0
    private var score = 0
    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        timer()
        generateQuestion()
    }

    private fun timer() {
        if (!isDestroyed) {
            val countTime = findViewById<TextView>(R.id.timer)
            object : CountDownTimer(60000, 1000) {
                override fun onTick(time: Long) {
                    countTime.text = counter.toString()
                    counter++
                }
                @SuppressLint("SetTextI18n")
                override fun onFinish() {
                    countTime.text = "Over"
                    resultActivity()
                }
            }.start()
        }
    }

    fun resultActivity() {
        val intent = Intent(this, ResultActivity::class.java)
        wrongCount = totalCount - correctCount
        val message = score.toString()
        val correctOnes = correctCount.toString()
        val wrongOnes = wrongCount.toString()
        val totalOnes = totalCount.toString()

        intent.putExtra("Score", message)
        intent.putExtra("Correct", correctOnes)
        intent.putExtra("Wrong", wrongOnes)
        intent.putExtra("Total", totalOnes)
        startActivity(intent)
        finish()
    }

    @SuppressLint("SetTextI18n")
    private fun generateQuestion() {
        isResultCorrect = true
        val num1 = Random.nextInt(100)
        val num2 = Random.nextInt(100)
        var result = num1 + num2
        val f: Float = Random.nextFloat()
        if (f > 0.5f) {
            result = Random.nextInt(100)
            isResultCorrect = false
        }
        val textViewQuestion = findViewById<TextView>(R.id.question)
        val textViewResult = findViewById<TextView>(R.id.result)

        textViewQuestion.text = "$num1 + $num2 "
        textViewResult.text = " = $result"
        val correct = findViewById<ImageButton>(R.id.txt_correct)
        val wrong = findViewById<ImageButton>(R.id.txt_wrong)

        correct.setOnClickListener {
            totalCount++
            if (result == num1 + num2) {
                correctCount++
                verifyAnswer(true)
                generateQuestion()
            } else
                generateQuestion()
        }
        wrong.setOnClickListener {
            generateQuestion()
            totalCount++
        }
    }

    @SuppressLint("SetTextI18n")
    fun verifyAnswer(answer: Boolean) {
        if (answer == isResultCorrect) {
            score += 5
            val txtScore: TextView = findViewById(R.id.txt_Score)
            txtScore.text = "Current Score: $score"
        }
    }
}