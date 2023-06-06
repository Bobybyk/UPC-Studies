package fr.uparis.tp02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class Conjugaison : AppCompatActivity() {

    val sujets = arrayOf("Je","Tu","Il/Elle/On","Nous","Vous","Ils/Elles")
    val conjugaison = arrayOf("suis","es","est","sommes","etes","sont")
    var index : Int = -1
    var total : Int = 0
    var correct : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conjugaison)

        val launcher = intent

        val name = launcher.getStringExtra("name")
        val greet : TextView = findViewById(R.id.viewNom)
        val res : EditText = findViewById(R.id.editVerbe)
        val check : Button = findViewById(R.id.button)
        val again : Button = findViewById(R.id.button2)
        val stop :  Button = findViewById(R.id.button3)

        greet.text = "${name}, ${greet.text.toString()}"

        check.setOnClickListener {
            total++
            if (res.text.toString().equals(conjugaison[index])) {
                Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                correct++
            } else
                Toast.makeText(this, "Faux", Toast.LENGTH_SHORT).show()

            res.text.clear()
            generateQuestion()
        }

        again.setOnClickListener {
            res.text.clear()
            generateQuestion()
        }

        stop.setOnClickListener {
            val result : Intent = Intent(this,MainActivity::class.java)
            result.putExtra("percent", (correct*100)/total)
            setResult(RESULT_OK,result)
            finish()
        }

        generateQuestion()
    }

    fun generateQuestion() {
        val question : TextView = findViewById(R.id.viewSujet)
        index = Random.nextInt(6)
        question.text = "${sujets[index]}"
    }
}