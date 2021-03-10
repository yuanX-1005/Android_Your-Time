package com.example.projet

import android.Manifest
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
import java.io.IOException

class PopUpAjoutSon : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            overridePendingTransition(0, 0)
            setContentView(R.layout.popup_add_sound)

            mediaRecorder = MediaRecorder()
            output = Environment.getExternalStorageDirectory().absolutePath + "/recording" + PopUpAjoutSon.getValue() + ".mp3"

            mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
            mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            mediaRecorder?.setOutputFile(output)

            var boutonMicro: ImageButton = findViewById(R.id.microButton);
            var boutonEcoute: ImageButton = findViewById(R.id.playButton);

            boutonMicro.setOnClickListener {
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    val permissions = arrayOf(android.Manifest.permission.RECORD_AUDIO, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    ActivityCompat.requestPermissions(this, permissions,0)
                } else {
                    if(!state) {
                        startRecording()
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
            fun  getValue(): Int{
                return numeroRecord
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
        private var audioFilePath: String? = null
        private var state: Boolean = false
        private var recorded: Boolean = false

        private fun startRecording() {
            try {
                PopUpAjoutSon.increment()
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
        }

        private fun playRecording(){
            mediaPlayer = MediaPlayer()
            mediaPlayer?.setDataSource(audioFilePath)
            mediaPlayer?.prepare()
            mediaPlayer?.start()
        }
    }
