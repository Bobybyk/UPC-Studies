package com.example.tp6

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.tp6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var model: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate( layoutInflater )
        setContentView( binding.root)

        model = ViewModelProvider(this).get(MyViewModel::class.java)

        /* binding button zero */
        binding.boutonZero.setOnClickListener {
            val s = model.cumulBinaire.value ?: ""
            model.cumulBinaire.setValue(s + '0')
        }

        /* binding button un */
        binding.boutonUn.setOnClickListener {
            val s = model.cumulBinaire.value ?: ""
            model.cumulBinaire.setValue(s + '1')
        }

        /* convert binary to decimal */
        binding.boutonConvertir.setOnClickListener {
            val nbrDecimal = try {
                 binding.nombreDecimal.text.toString().toInt()
            }catch (e:Exception){
                0
            }
            model.cumulBinaire.setValue(Integer.toBinaryString(nbrDecimal))
        }

        binding.boutonEffacer.setOnClickListener {
            model.cumulBinaire.setValue("")
            binding.nombreDecimal.setText("")
            //binding.root.
        }

        model.cumulBinaire.observe(this) {
            binding.nombreBinaire.setText(it.toString())
        }

        binding.boutonColor.setOnClickListener {
            try {
                binding.root.setBackgroundColor(Color.parseColor(binding.colorBg.text.toString()))

                val perf = getSharedPreferences("userLog", Context.MODE_PRIVATE)
                val edit = perf.edit()
                edit.putString("color", binding.colorBg.text.toString())
                edit.apply()

            }catch (_:Exception){
                //TODO
            }
        }

        try {

            val perf = getSharedPreferences("userLog", Context.MODE_PRIVATE)
            val colorLog = perf.getString("color", "").toString()
            binding.root.setBackgroundColor(Color.parseColor(colorLog))
        }catch (_:Exception){

        }





    }

}