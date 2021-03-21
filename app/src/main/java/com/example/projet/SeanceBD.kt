package com.example.projet

import android.net.Uri
import com.example.projet.SeanceBD.Singleton.databaseRef
import com.example.projet.SeanceBD.Singleton.downloadUri
import com.example.projet.SeanceBD.Singleton.seanceList
import com.example.projet.SeanceBD.Singleton.storageReference
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.net.URI
import java.util.*
import javax.security.auth.callback.Callback

class SeanceBD  {

    object Singleton{

        private val BUCKET_URL : String = "gs://your-time-b4ff9.appspot.com"

        //Connexion storage
        val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(BUCKET_URL)

        //Se connecter a Seance
        val databaseRef = FirebaseDatabase.getInstance().getReference("seance")

        //Liste qui contient les séances
        val seanceList = arrayListOf<SeanceModel>()

        //Lien de l'image
        var downloadUri: Uri? = null
    }

   /* fun updateData(){
        //Absorber les données depuis la database
        databaseRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //retirer  les anciennes
                seanceList.clear()

                //récolte la liste de seance
                for( ds in snapshot.children){
                    val seance = ds.getValue(SeanceModel::class.java)

                    if(seance != null){
                        seanceList.add(seance)
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }*/
    fun uploadImage(file : Uri, callback:() -> Unit){
        if(file!=null){
            val fileName = UUID.randomUUID().toString() + ".jpg"
            val ref = storageReference.child(fileName)
            val uploadTask = ref.putFile(file)

            uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>>{ task ->
                //prob ?
                if(!task.isSuccessful){
                    task.exception?.let{ throw it}
                }

                return@Continuation ref.downloadUrl

            }).addOnCompleteListener{task ->
                if(task.isSuccessful){
                    downloadUri = task.result
                    callback()
                }

            }
        }
    }

    fun UpdateSeance(seance : SeanceModel) = databaseRef.child(seance.id).setValue(seance)

    fun InsertSeance(seance : SeanceModel) = databaseRef.child(seance.id).setValue(seance)

    fun deleteSeance(seance : SeanceModel) = databaseRef.child(seance.id).removeValue()
}
