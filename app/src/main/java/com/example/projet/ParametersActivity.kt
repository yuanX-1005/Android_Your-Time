package com.example.projet

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import petrov.kristiyan.colorpicker.ColorPicker

class ParametersActivity : AppCompatActivity() {
    private val IMAGE_PICK_CODE = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parameters)
        val btnColor = findViewById<FloatingActionButton>(R.id.colorPick)
        val btnImg = findViewById<Button>(R.id.addImage)
        btnColor.setOnClickListener(View.OnClickListener() {
                val colorPicker = ColorPicker(this@ParametersActivity)
                val colors: ArrayList<String> = ArrayList()
                colors.add("#82B926")
                colors.add("#a276eb");
                colors.add("#6a3ab2");
                colors.add("#666666");
                colors.add("#FFFF00");
                colors.add("#3C8D2F");
                colors.add("#FA9F00");
                colors.add("#FF0000");
                colorPicker
                    .setTitle(getString(R.string.colorPickTitle))
                    .setDefaultColorButton(Color.parseColor("#f84c44"))
                    .setColors(colors)
                    .setColumns(4)
                    .setRoundColorButton(true)
                    .setOnChooseColorListener(object : ColorPicker.OnChooseColorListener {
                        override fun onChooseColor(pos: Int, color: Int) {
                            Log.d("position", "" + pos) // will be fired only when OK button was tapped
                        }
                        override fun onCancel() {}
                    }).show()
        });
        btnImg.setOnClickListener(View.OnClickListener() {
                pickImageFromGallery();
        });
    }
    private fun pickImageFromGallery() {
        //intent to pick image
        intent = Intent(Intent.ACTION_PICK);
        intent.type = "image/*";
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            val iv: ImageView = findViewById<View>(R.id.v) as ImageView
            iv.setImageURI(data?.data) // handle chosen image
        }
    }
}