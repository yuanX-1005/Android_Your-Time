package com.example.projet

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.projet.SeanceBD.Singleton.downloadUri
import java.util.*

class Ajout : AppCompatActivity() {

    private var uploadImage : ImageView? = null

    private var file: Uri?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajout)

        uploadImage = findViewById(R.id.preview_image)

        //bouton image
        val PickImageButton = findViewById<Button>(R.id.upload_button)

        PickImageButton.setOnClickListener{PickImage()}

        //bouton confirmation
        val confirmButton = findViewById<Button>(R.id.confirmer_button)
        confirmButton.setOnClickListener{sendform(R.layout.activity_ajout)}
    }

    private fun sendform(activityAjout: Int) {
        //hébeger sur le bucket
        val repo = SeanceBD()
        repo.uploadImage(file!!){
            val seanceName = findViewById<EditText>(R.id.nomSeance_input).text.toString()
            val Seq1 = findViewById<EditText>(R.id.nomSeq1_input).text.toString()
            val Seq2 = findViewById<EditText>(R.id.nomSeq2_input).text.toString()
            val Seq3 = findViewById<EditText>(R.id.nomSeq3_input).text.toString()
            val Seq4 = findViewById<EditText>(R.id.nomSeq4_input).text.toString()
            val Temps1 = findViewById<EditText>(R.id.tempsSeq1_input).text.toString()
            val Temps2 = findViewById<EditText>(R.id.tempsSeq2_input).text.toString()
            val Temps3 = findViewById<EditText>(R.id.tempsSeq3_input).text.toString()
            val Temps4 = findViewById<EditText>(R.id.tempsSeq4_input).text.toString()
            val downloadImageUrl = downloadUri

            //créer  un objet SeanceModel
            val seance = SeanceModel(
                UUID.randomUUID().toString(),
                seanceName,
                Seq1,
                Seq2,
                Seq3,
                Seq4,
                Temps1,
                Temps2,
                Temps3,
                Temps4
            )

            //envoyer a la BDd
            repo.InsertSeance(seance)
        }
    }

    private fun PickImage(){
        val intent = Intent()
        intent.type="image/"
        intent.action= Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 35)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 35 && resultCode == Activity.RESULT_OK){

            //Verif les données si nulle
            if(data == null || data.data==null) return

            // récup l'image
            file = data.data

            uploadImage?.setImageURI(file)

        }
    }
}