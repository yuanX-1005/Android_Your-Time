package com.example.projet

import android.app.Activity
import android.content.Context
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception

class Commencer : Activity(){
    private var isPaused = false
    private var isCancelled = false
    private var resumeFromMillis:Long = 0
    private var progr:Long = 0
    private var nbfois:Long = 3



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_commencer)

        val millisInFuture:Long = 11*1000//10sec
        val countDownInterval:Long = 1000
        val button_start: Button = findViewById(R.id.button_start)
        val button_stop: Button = findViewById(R.id.button_stop)
        val button_pause: Button = findViewById(R.id.button_pause)
        val button_resume: Button = findViewById(R.id.button_resume)
        val text_view_nom_seance: TextView = findViewById(R.id.text_view_nom_seance)

        text_view_nom_seance.text = "Nom de la séance"

        // Count down timer start button

        button_start.setOnClickListener{
            // Start the timer
            timer(millisInFuture,countDownInterval).start()
            it.isEnabled = false
            button_stop.isEnabled = true
            button_pause.isEnabled = true

            isCancelled = false
            isPaused = false

            progr = 0
            playTune()
        }


        // Count down timer stop/cancel button
        button_stop.setOnClickListener{
            // Start the timer
            isCancelled = true
            isPaused = false

            it.isEnabled = false
            button_start.isEnabled = true
            button_pause.isEnabled = false

            progr = 0
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

    private fun playTune() {
        try {
            val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val track = RingtoneManager.getRingtone(applicationContext, notification)
            track.play()
        }
        catch (e : Exception){
            Toast.makeText(applicationContext, "Fail", Toast.LENGTH_SHORT).show()
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
                text_view_statut.text ="$nbfois"

                prog_bar.progress = progr.toInt()
                //text_view_statut.text = "$progr%"


                if (isPaused){
                    text_view.text = "${text_view.text}"
                    // To ensure start timer from paused time
                    resumeFromMillis = millisUntilFinished
                    cancel()
                }else if (isCancelled){
                    text_view.text = "${text_view.text}"
                    text_view.text = "STOP"
                    cancel()
                }else{
                    text_view.text = timeRemaining
                    progr += 10
                }
            }

            override fun onFinish() {
                text_view.text = "Done"
                progr = 0

                //timer(millisInFuture,countDownInterval).start()



                //for (i in nbfois downTo 1 step 1) {
                    //timer(millisInFuture,countDownInterval).start()
                //}


            }

        }

    }


}



// Extension function to show toast message
fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
