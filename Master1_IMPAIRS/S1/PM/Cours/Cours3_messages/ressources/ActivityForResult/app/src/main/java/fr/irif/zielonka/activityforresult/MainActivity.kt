package fr.irif.zielonka.activityforresult

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

const val KEY = "int_sent"

class MainActivity : AppCompatActivity() {
    val text by lazy {   findViewById<TextView>(R.id.text)  }
    val edit by lazy {   findViewById<TextView>(R.id.edit)  }
    val button by lazy {   findViewById<TextView>(R.id.button)  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{  val s = edit.text
            val intent = Intent( this@MainActivity, ActiviteFille::class.java)
            val bundle = Bundle()
            bundle.putInt( KEY, edit.text.toString().toInt() )
            intent.putExtras( bundle )
            Log.d("main", "before launch")
            launcher.launch(intent)
        }
    }

    val launcher : ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){
        if( it.resultCode == Activity.RESULT_OK ){
            val intent = it.data  //recupérer Intent envoyé en retour
            val k = intent?.extras?.getInt( KEY ) ?: 0
            text.text = k.toString()
        }else{
            Toast.makeText(this, "sans resultat", Toast.LENGTH_LONG).show()
        }
    }
}


class  MessageContract : ActivityResultContract<Int, Int>(){

    override fun createIntent(context: Context, input: Int?): Intent {
        TODO("Not yet implemented")
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Int {
        TODO("Not yet implemented")
    }
}