package com.example.projet.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.projet.PopUpAjoutSon
import com.example.projet.R

class NotificationsFragment : Fragment() {

  private lateinit var notificationsViewModel: NotificationsViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)
    val root: View = inflater.inflate(R.layout.fragment_notifications, container, false)

    val button : View = root.findViewById(R.id.boutonPopUp)
    button.setOnClickListener {
      val intent = Intent(context, PopUpAjoutSon::class.java)
      startActivity(intent)
    }
    return root
  }
}