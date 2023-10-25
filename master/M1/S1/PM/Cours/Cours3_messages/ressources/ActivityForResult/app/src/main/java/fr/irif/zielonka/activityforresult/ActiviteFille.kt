package fr.irif.zielonka.activityforresult

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class ActiviteFille : AppCompatActivity() {

    val text by lazy{ findViewById<TextView>( R.id.text )}
    val edit by lazy{ findViewById<EditText>( R.id.edit )}
    val button by lazy{ findViewById<Button>( R.id.button )}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activite_fille)

        val intent = getIntent()
        val i = intent.getExtras()?.getInt( KEY )  ?: 0
        text.text = "$i"


        button.setOnClickListener{
            val j = edit.text.toString().toInt()
            val intent = Intent()
            intent.putExtra(KEY , i+j)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }
}