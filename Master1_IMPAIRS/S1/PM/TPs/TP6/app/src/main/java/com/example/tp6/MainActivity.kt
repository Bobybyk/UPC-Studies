package com.example.tp6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.tp6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var model: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this).get(MyViewModel::class.java)

        binding.boutonZero.setOnClickListener{
            val s = model.cumulBinaire.value ?: ""
            model.cumulBinaire.setValue(s + "0")
        }

        binding.boutonZero.setOnClickListener{
            val s = model.cumulBinaire.value ?: ""
            model.cumulBinaire.setValue(s + "1")
        }

        binding.boutonConvertir.setOnClickListener {
            val nbrDecimal = try {
                binding.nombreDecimal.text.toString().toInt()
            } catch (e:Exception) {
                0
            }
            model.cumulBinaire.setValue(Integer.toBinaryString(nbrDecimal))
        }

        binding.boutonEffacer.setOnClickListener {
            model.cumulBinaire.setValue("")
            binding.nombreBinaire.setText("")
        }

        model.cumulBinaire.observe(this) {
            binding.nombreBinaire.setText(it.toString())
        }
    }
}