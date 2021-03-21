package com.example.projet

import android.Manifest
import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Environment
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.io.IOException


class PopUpAjoutSon : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            overridePendingTransition(0, 0)
            setContentView(R.layout.popup_add_sound)

            mediaRecorder = MediaRecorder()

            val cw = ContextWrapper(applicationContext)
            val chemin: String = cw.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString()

            output = chemin + "/recording" + PopUpAjoutSon.getValue() + ".mp3"

            var boutonMicro: ImageButton = findViewById(R.id.microButton);
            var boutonEcoute: ImageButton = findViewById(R.id.playButton);
            boutonMicro.setOnClickListener {
                if (ContextCompat.checkSelfPermission(this,
                                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    val permissions = arrayOf(android.Manifest.permission.RECORD_AUDIO, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    ActivityCompat.requestPermissions(this, permissions,0)

                } else {
                    if(!paramConfig) {
                        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
                        mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
                        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                        mediaRecorder?.setOutputFile(output)
                        paramConfig = true
                    }
                    if(!state) {
                        startRecording()
                        boutonMicro.setEnabled(false)
                    }
                    else if(state){
                        stopRecording()
                    }
                }
            }

            boutonEcoute.setOnClickListener{
                if(recorded){
                    playRecording()
                }
                else{
                    Toast.makeText(this,"Pas d'enregistrement", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // PERMET DE NUMEROTER LES FICHIERS AUDIOS

        companion object{
            var numeroRecord = 0
            fun  getValue(): String{
                return numeroRecord.toString()
            }
            fun increment(){
                numeroRecord++
            }
        }

        // BOUTON POP UP RECORD




        // TOUTE LA PARTIE RECORD / PLAY
        private var output: String? = null
        private var mediaRecorder: MediaRecorder? = null
        private var mediaPlayer: MediaPlayer? = null
        private var paramConfig: Boolean = false
        private var state: Boolean = false
        private var recorded: Boolean = false

        private fun startRecording() {
            try {
                PopUpAjoutSon.increment()
                Toast.makeText(this,output,Toast.LENGTH_SHORT).show()
                mediaRecorder?.prepare()
                mediaRecorder?.start()
                state = true

                Toast.makeText(this, "Recording started!", Toast.LENGTH_SHORT).show()
                object  : CountDownTimer(5000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                    }
                    override fun onFinish() {

                        stopRecording()
                    }
                }.start()

            } catch (e: IllegalStateException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        private fun stopRecording(){
            mediaRecorder?.stop()
            mediaRecorder?.release()
            state = false
            recorded = true
            var boutonMicro: ImageButton = findViewById(R.id.microButton);
            boutonMicro.setEnabled(true)
            Toast.makeText(this,"ENREGISTREMENT FINI",Toast.LENGTH_SHORT).show()

        }

        private fun playRecording(){
            mediaPlayer = MediaPlayer()
            mediaPlayer?.setDataSource(output)
            mediaPlayer?.prepare()
            mediaPlayer?.start()
        }
    }
