package com.example.tp7

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp7.adapter.PaysAdapter
import com.example.tp7.data.Pays
import com.example.tp7.model.AjoutViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class RechercheActivity : AppCompatActivity() {

    private lateinit var adapter: PaysAdapter
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }
    private val model by lazy { ViewModelProvider(this)[AjoutViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recherche)

        recyclerView.layoutManager = LinearLayoutManager(this)

        model.loadAll().observe(this) {
            adapter = PaysAdapter(it.toMutableList(), 0)

            if (savedInstanceState != null) {
                val pays = savedInstanceState.getSerializable("Pays")
                if (pays!=null) adapter.paysChecked = (pays as Pays)
            }

            recyclerView.adapter = adapter
        }

        filterPays()

        findViewById<Button>(R.id.actionButton).setOnClickListener{

            val radioGroup = findViewById<View>(R.id.group) as RadioGroup
            val radioButtonID = radioGroup.checkedRadioButtonId
            val radioButton = radioGroup.findViewById<View>(radioButtonID) as RadioButton
            val selectedText = radioButton.text as String

            if (selectedText=="supprimer"){
                MaterialAlertDialogBuilder(this)
                    .setTitle("Suppression pays")
                    .setMessage("Voulez-vous vraiment supprimer ce pays!")
                    .setPositiveButton("oui") { _, _ ->

                        if (adapter.paysChecked!=null){

                            model.deletePays(adapter.paysChecked!!)

                            model.deleteInfo.observe(this) {
                                Toast.makeText(this, if (it==0) "Aucun élément supprimé" else "Supression réussie", Toast.LENGTH_LONG).show()
                                adapter.paysChecked = null
                            }

                        }else Toast.makeText(this, "Veuillez selectionner un pays.", Toast.LENGTH_LONG).show()

                    }
                    .setNegativeButton("non"){_, _ ->

                    }
                    .setCancelable(false)
                    .show()
            }else{
                val iii = Intent(this@RechercheActivity, ModifierActivity::class.java)
                iii.putExtra("pays", adapter.paysChecked!!.pays)
                iii.putExtra("capitale", adapter.paysChecked!!.capitale)
                iii.putExtra("continent", adapter.paysChecked!!.continent)
                iii.putExtra("superficie", adapter.paysChecked!!.superficie)
                startActivity(iii)
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val selected = adapter.paysChecked
        if (selected != null) outState.putSerializable("Pays", selected)
    }

    private fun filterPays(){
        findViewById<EditText>(R.id.pays).addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //TODO("Not yet implemented")
            }

            override fun afterTextChanged(s: Editable?) {

                if (model.resultPartialName!=null) {
                    model.resultPartialName!!.removeObservers(this@RechercheActivity)
                    model.loadPartialName(s.toString())
                    model.resultPartialName!!.observe(this@RechercheActivity) {
                        adapter = PaysAdapter(it.toMutableList(), s.toString().length)
                        recyclerView.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                }

            }
        })
    }

}