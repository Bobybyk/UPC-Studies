package fr.uparis.exam

import android.R
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import fr.uparis.exam.databinding.ActivityAjouterBinding
import fr.uparis.exam.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter : MainRecyclerViewAdapter
    private val model by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.layoutManager = LinearLayoutManager(this)

        binding.ajoutButton.setOnClickListener {
            val intent = Intent(this, AjouterActivity::class.java)
            startActivity(intent)
        }

        binding.telButton.setOnClickListener {
            val sel = adapter.getSelected()

            if(sel != null) {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:${sel.numero}")
                }
                startActivity(intent)
            }
        }

        binding.annotationButton.setOnClickListener {
            val sel = adapter.getSelected()

            if(sel != null && sel.annotation.isNotEmpty()) {
                Toast.makeText(this, "${sel.annotation}",Toast.LENGTH_SHORT)
            }
        }

        model.loadItems().observe(this) {
            adapter = MainRecyclerViewAdapter(it.toMutableList())
            binding.recycler.adapter = adapter
        }
    }
}