package com.example.tp7

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.tp7.data.Pays
import com.example.tp7.model.AjoutViewModel

class ModifierActivity: AppCompatActivity() {

    private val model by lazy { ViewModelProvider(this)[AjoutViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modifier)

        val iii = intent
        val pays = iii.getStringExtra("pays").toString()
        val capitale = iii.getStringExtra("capitale").toString()
        val continent = iii.getStringExtra("continent").toString()
        val superficie = iii.getStringExtra("superficie").toString()

        findViewById<TextView>(R.id.ed_pays).text=pays
        findViewById<EditText>(R.id.ed_capitale).setText(capitale)
        findViewById<EditText>(R.id.ed_continent).setText(continent)
        if (superficie != "null"){
            findViewById<EditText>(R.id.ed_superficie).setText(superficie)
        }

        findViewById<Button>(R.id.b_ajouter).setOnClickListener{

            Log.wtf(findViewById<EditText>(R.id.ed_superficie).toString(), "---------")

            model.updatePays(
                Pays(
                    findViewById<TextView>(R.id.ed_pays).text.toString(),
                    findViewById<EditText>(R.id.ed_continent).text.toString(),
                    findViewById<EditText>(R.id.ed_capitale).text.toString(),
                    if (superficie == "null") null else superficie.toInt()
                )
            )

            model.updateInfo.observe(this) {
                startActivity(Intent(this, RechercheActivity::class.java))
            }

        }

    }

}