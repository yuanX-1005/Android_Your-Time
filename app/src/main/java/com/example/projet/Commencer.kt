package com.example.projet

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

class Commencer : Activity(){
    private var isPaused = false
    private var isCancelled = false
    private var resumeFromMillis:Long = 0
    private var progr:Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_commencer)

        val millisInFuture:Long = 11*1000//10sec
        val countDownInterval:Long = 1000
        val button_start: Button = findViewById(R.id.button_start)
        val button_stop: Button = findViewById(R.id.button_stop)
        val button_pause: Button = findViewById(R.id.button_pause)
        val button_resume: Button = findViewById(R.id.button_resume)


        // Count down timer start button

        button_start.setOnClickListener{
            // Start the timer
            timer(millisInFuture,countDownInterval).start()
            it.isEnabled = false
            button_stop.isEnabled = true
            button_pause.isEnabled = true

            isCancelled = false
            isPaused = false

        }


        // Count down timer stop/cancel button
        button_stop.setOnClickListener{
            // Start the timer
            isCancelled = true
            isPaused = false

            it.isEnabled = false
            button_start.isEnabled = true
            button_pause.isEnabled = false
        }


        // Count down timer pause button
        button_pause.setOnClickListener{
            isPaused = true
            isCancelled = false

            it.isEnabled = false
            button_start.isEnabled = false
            button_stop.isEnabled = false
            button_resume.isEnabled = true
        }


        // Count down timer resume button
        button_resume.setOnClickListener{
            // Resume the timer
            timer(resumeFromMillis,countDownInterval).start()

            isPaused = false
            isCancelled = false

            it.isEnabled = false
            button_pause.isEnabled = true
            button_start.isEnabled = false
            button_stop.isEnabled = true
        }

    }
    private fun timer(millisInFuture:Long,countDownInterval:Long): CountDownTimer {
        return object: CountDownTimer(millisInFuture,countDownInterval){
            val text_view: TextView = findViewById(R.id.text_view)
            val text_view_statut: TextView = findViewById(R.id.text_view_statut)

            override fun onTick(millisUntilFinished: Long){
                val timeRemaining = "${millisUntilFinished/1000}"

                val prog_bar: ProgressBar = findViewById(R.id.progress_bar)
                //progr += 100/millisInFuture
                progr += 10

                prog_bar.progress = progr.toInt()
                text_view_statut.text = "$progr%"


                if (isPaused){
                    text_view.text = "${text_view.text}"
                    // To ensure start timer from paused time
                    resumeFromMillis = millisUntilFinished
                    cancel()
                }else if (isCancelled){
                    text_view.text = "${text_view.text}"
                    cancel()
                }else{
                    text_view.text = timeRemaining
                }
            }

            override fun onFinish() {
                text_view.text = "Done"
            }
        }

    }
}



// Extension function to show toast message
fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
