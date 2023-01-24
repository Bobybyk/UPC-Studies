package com.example.pcmexamen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.pcmexamen.databinding.ActivityMainBinding
import com.example.pcmexamen.databinding.ManageMailViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter : RecyclerView
    private val model by lazy {
        ViewModelProvider(this)[ManageMailViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ajouterDestinataireButton.setOnClickListener {
            if (binding.nom.text.isNotEmpty() && binding.prenom.text.isNotEmpty() && binding.mail.text.isNotEmpty()) {
                var anot = "empty"
                if (binding.annotation.text.isNotEmpty()) {
                    anot = binding.annotation.text.toString()
                }
                val sendMail = Destinataire(0, binding.nom.text.toString(), binding.prenom.text.toString(), binding.mail.text.toString(), anot)
                model.insertDestinataire(sendMail)
            }
        }

        /* model.loadInfo.observe(this) {
            if (it.toMutableList().isNotEmpty()) {
                //adapter = RecyclerView(it.toMutableList())
                //binding.recyclerView.adapter = adapter
            }
        } */

    }
}