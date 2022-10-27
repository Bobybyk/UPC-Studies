package fr.irif.zielonka.recyclerviewwithoutconfigchange

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import java.util.*
import kotlin.collections.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter: MyRecycleAdapter
    val recyclerView by lazy{ findViewById(R.id.recycler) as RecyclerView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.hasFixedSize() /* pour améliorer les pérformances*/

        /* spécifier le layout de RecyclerView */
        recyclerView.layoutManager = LinearLayoutManager(this)

        val colors = resources.getStringArray(R.array.colors).toMutableList()
        Log.d("CheckBoxList", "nombre =  ${colors.size}")

        /* associer l'adapter à RecyclerView*/
        adapter = MyRecycleAdapter(colors)
        recyclerView.adapter = adapter

    }
    fun supprimer(view: View?) {
        Toast.makeText(
            this,
            "suppression de ${adapter.checked.size} items",
            Toast.LENGTH_LONG
        ).show()
        adapter.removeChecked()
    }
}