package fr.irif.zielonka.downloadimagewithdownloadmanager


import android.content.res.Configuration

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.irif.zielonka.downloadimagewithdownloadmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy{ ActivityMainBinding.inflate(layoutInflater) }
    val viewModel by lazy { ViewModelProvider(this).get(MyViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        //recuprer le tableau d'adresse http dans les ressources
        viewModel.urlListe = resources
            .getStringArray(R.array.httpAdresses).toList()

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
        } else {
            binding.recyclerView.layoutManager = GridLayoutManager(this, 3)
        }

        /*créer Adapter et attache au recyclerView */
        val adapter = Adapter().apply {
            binding.recyclerView.adapter = this
            bitmapList = viewModel.bitmapList
            mapListPositionToChecked = viewModel.mapListPositionToChecked
        }

        viewModel.liveBitmapList.observe(this){
            adapter.notifyDataSetChanged()
        }

        /* commencer le téléchargement */
        viewModel.fetchBitmaps()
    }
}