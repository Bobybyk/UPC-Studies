package fr.irif.zielonka.myapplication

import android.content.res.Resources
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckedTextView
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    val button: Button by lazy { findViewById(R.id.button) as Button }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val colors = resources.getStringArray(R.array.colors).toMutableList()
        val setOfChecked: MutableSet<String> = mutableSetOf()
        val arrayAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_checked, colors
        )

        val lv: ListView = findViewById<ListView>(R.id.listView).apply {
            adapter = arrayAdapter
            setBackgroundColor(Color.LTGRAY)
            setOnItemClickListener { parent, view, position, id ->
                //(view as CheckedTextView).setChecked(!view.isChecked())
                (view as CheckedTextView).toggle()
                if (view.isChecked())
                    setOfChecked.add(view.text.toString())
                else
                    setOfChecked.remove(view.text.toString())
                view.setBackgroundColor(
                    if (view.isChecked())
                        Color.CYAN
                    else
                        Color.LTGRAY
                )
            }
        }
        
        button.setOnClickListener {
            for (x in setOfChecked)
                arrayAdapter.remove(x)
            setOfChecked.clear()
            arrayAdapter.notifyDataSetChanged()
        }
    }

}