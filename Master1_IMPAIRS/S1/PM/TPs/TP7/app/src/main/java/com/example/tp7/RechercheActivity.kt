package com.example.tp7

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp7.adapter.PaysAdapter
import com.example.tp7.model.AjoutViewModel


class RechercheActivity : AppCompatActivity() {

    private lateinit var adapter: PaysAdapter
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }
    private val model by lazy { ViewModelProvider(this)[AjoutViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recherche)

        recyclerView.layoutManager = LinearLayoutManager(this)

        model.loadAll().observe(this) {
            adapter = PaysAdapter(it.toMutableList())
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }



        findViewById<EditText>(R.id.pays).addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //TODO("Not yet implemented")
            }

            override fun afterTextChanged(s: Editable?) {

                model.loadPartialName(s.toString())

                model.adptr.observe(this@RechercheActivity) {
                    adapter = PaysAdapter(it.toMutableList())
                    recyclerView.adapter = adapter
                    adapter.notifyDataSetChanged()

                }

            }
        })

    }

}