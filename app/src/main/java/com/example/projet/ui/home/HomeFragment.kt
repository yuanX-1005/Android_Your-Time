package com.example.projet.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.projet.Commencer
import com.example.projet.R

class HomeFragment : Fragment() {

  private lateinit var homeViewModel: HomeViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_home, container, false)
    val button: Button = root.findViewById(R.id.button_commencer)
    button.setOnClickListener(View.OnClickListener {
      val intent= Intent(context, Commencer::class.java)
      startActivity(intent)
    })


    return root
  }
}