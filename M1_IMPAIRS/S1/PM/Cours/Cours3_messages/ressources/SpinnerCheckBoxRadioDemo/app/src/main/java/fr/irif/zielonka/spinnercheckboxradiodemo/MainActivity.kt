package fr.irif.zielonka.spinnercheckboxradiodemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    val text by lazy { findViewById(R.id.text) as TextView }
    val spinner by lazy { findViewById<Spinner>(R.id.spinner)  }
    val checkedTextView by lazy { findViewById(R.id.checked) as CheckedTextView }
    val group by lazy { findViewById<RadioGroup>(R.id.group) }
    val gauche by lazy { findViewById<RadioButton>(R.id.gauche)  }
    val droit by lazy { findViewById(R.id.droit) as RadioButton }
    val up by lazy{ findViewById( R.id.up) as CheckBox}
    val down by lazy{ findViewById( R.id.down) as CheckBox}
    val image by lazy{ findViewById(R.id.image) as ImageView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner.setOnItemSelectedListener(
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    pos: Int,
                    id: Long
                ) {
                    text.text = (view as TextView).text
                }
                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }
        )

        group.setOnCheckedChangeListener{
            _, id: Int ->
                when( id ) {
                    R.id.gauche -> Toast.makeText(MainActivity@ this, "gauche", Toast.LENGTH_LONG)
                        .show()
                    R.id.droit -> Toast.makeText(MainActivity@ this, "droit", Toast.LENGTH_LONG)
                        .show()
                }
        }



    }
    fun updown(v :View){
        if( up.isChecked && down.isChecked )
        {  image.setImageResource( R.drawable.updown ) }
        else if( up.isChecked )
        {  image.setImageResource( R.drawable.up ) }
        else if( down.isChecked)
        {  image.setImageResource( R.drawable.down ) }
        else
            image.setImageDrawable(null)
    }
}
