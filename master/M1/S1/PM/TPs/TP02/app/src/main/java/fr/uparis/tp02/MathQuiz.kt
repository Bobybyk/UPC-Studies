package fr.uparis.tp02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MathQuiz : AppCompatActivity() {

    private var x : Int = 0
    private var y : Int = 0
    private var total : Int = 0
    private var correct : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math_quiz)

        Log.i(null,"prout")

        val launcher = intent;
        val nom = launcher.getStringExtra("name")

        val greetings: TextView = findViewById(R.id.viewNom)
        var response : EditText = findViewById(R.id.editReponse)
        val check : Button = findViewById(R.id.button)
        val again : Button = findViewById(R.id.button2)
        val stop : Button = findViewById(R.id.button3)

        greetings.text = "${nom}, ${greetings.text.toString()}"
        check.setOnClickListener{
            total++
           if(response.text.toString().toInt() == (x*y)) {
               correct++
               Toast.makeText(this,"Correct !",Toast.LENGTH_SHORT).show()
           } else {
               Toast.makeText(this,"Faux !",Toast.LENGTH_SHORT).show()
           }
            response.text.clear()
            genereQuestion()
        }

        again.setOnClickListener{
            response.text.clear()
            genereQuestion()
        }

        stop.setOnClickListener{
            val result : Intent = Intent(this,MainActivity::class.java)
            result.putExtra("percent", (correct*100)/total)
            setResult(RESULT_OK,result)
            finish()
        }

        genereQuestion()
    }

    fun genereQuestion() {
        x = Random.nextInt(10)
        y =  Random.nextInt(10)
        val question : TextView = findViewById(R.id.viewQuestion)
        question.text = "${x}x${y}="
    }
}