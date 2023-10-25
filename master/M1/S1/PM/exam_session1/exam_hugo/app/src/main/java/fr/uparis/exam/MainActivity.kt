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

    // import et bind avec le layout (activity_main)
    private lateinit var binding : ActivityMainBinding
    // import de l'adapter
    private lateinit var adapter : MainRecyclerViewAdapter
    // instance de la classe MainViewModel
    private val model by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // pour lier la disposition XML de l'activité
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.layoutManager = LinearLayoutManager(this)

        /* Ecouteur de clic sur le bouton "ajoutButton",
         * lorsque le bouton est cliqué, une nouvelle instance de Intent
         * est créée pour démarrer la nouvelle activité AjouterActivity
         */
        binding.ajoutButton.setOnClickListener {
            val intent = Intent(this, AjouterActivity::class.java)
            startActivity(intent)
        }

        /*
         * définit un écouteur de clic sur le bouton "telButton",
         * lorsque le bouton est cliqué, on obtient un élément sélectionné
         * à partir de l'adapteur et crée un Intent pour lancer l'application
         * de numérotation téléphonique avec le numéro de téléphone
         * de l'élément sélectionné
         */
        binding.telButton.setOnClickListener {
            val sel = adapter.getSelected()

            if(sel != null) {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:${sel.numero}")
                }
                startActivity(intent)
            }
        }

        /*
         * définit un écouteur de clic sur le bouton "annotationButton",
         * lorsque le bouton est cliqué, on obtient à partir de l'adapteur
         * un élément sélectionné et on l'affiche sur une notification Toast
         * si elle n'est pas vide
         */
        binding.annotationButton.setOnClickListener {
            val sel = adapter.getSelected()

            if(sel != null && sel.annotation.isNotEmpty()) {
                Toast.makeText(this, "${sel.annotation}",Toast.LENGTH_SHORT)
            }
        }

        /*
         * appel la fonction loadItems() du model pour obtenir une liste d'éléments,
         * crée une instance de MainRecyclerViewAdapter avec la liste d'éléments et
         * l'attribue au RecyclerView via l'adapter
         */
        model.loadItems().observe(this) {
            adapter = MainRecyclerViewAdapter(it.toMutableList())
            binding.recycler.adapter = adapter
        }
    }
}