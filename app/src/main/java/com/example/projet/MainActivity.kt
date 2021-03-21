package com.example.projet

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.projet.fragments.HomeFrag
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //injecter notre container dans notre fragment_container
      /* val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, HomeFrag())
        transaction.addToBackStack(null)
        transaction.commit()*/

       val buttonAjout : ImageButton = findViewById(R.id.ajoutSeance)
        buttonAjout.setOnClickListener{
             val intent = Intent(this, Ajout::class.java)
            startActivity(intent)
        }

        //BD

        val repo = SeanceBD()

        //repo.updateData()



        val button_parameter = findViewById<FloatingActionButton>(R.id.button_parameter)
        button_parameter.setOnClickListener(View.OnClickListener {
            val intent= Intent(this, ParametersActivity::class.java)
            startActivity(intent)
        })

             val dbHelper = DataBaseHelper(this)
            // Gets the data repository in write mode
            val db = dbHelper.writableDatabase

           val values = ContentValues().apply {
                put(FeedReaderContract.FeedEntry.COLUMN_NAME_DATEDEBUT,"10000")
                put(FeedReaderContract.FeedEntry.COLUMN_NAME_DATEFIN,"15000")
            }

// Insert the new row, returning the primary key value of the new row
        val newRowId = db?.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values)


        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_stats, R.id.navigation_perso))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }
}