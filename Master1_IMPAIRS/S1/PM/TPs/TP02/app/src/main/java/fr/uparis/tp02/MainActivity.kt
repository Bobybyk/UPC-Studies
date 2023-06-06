package fr.uparis.tp02

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import java.io.Console

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edit: EditText = findViewById(R.id.editNom)
        val boutonMaths: Button = findViewById((R.id.buttonMath))
        val boutonFr: Button = findViewById((R.id.buttonFr))

        boutonMaths.setOnClickListener {
            val mathI = Intent(this, MathQuiz::class.java)
            mathI.putExtra("name", edit.text.toString())

            launcher.launch(mathI)
        }

        boutonFr.setOnClickListener {
            val FrI = Intent(this,Conjugaison::class.java)
            FrI.putExtra("name", edit.text.toString())

            launcher.launch(FrI)
        }
    }

    val launcher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK){
                val ret = it?.data?.getIntExtra("percent",-1)
                Toast.makeText(this,"Vous avez bien répondu à ${ret}% des questions",Toast.LENGTH_LONG).show()
            }
        }
}