package fr.uparis.exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import fr.irif.zielonka.examjanviera.AnnuaireItem
import fr.uparis.exam.databinding.ActivityAjouterBinding

class AjouterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAjouterBinding
    private val model by lazy {
        ViewModelProvider(this)[AjoutViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAjouterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ajouter.setOnClickListener {
            val nom : String = binding.nom.text.toString()
            val prenom : String = binding.prenom.text.toString()
            val nbr : String = binding.telNumber.text.toString()
            val note : String = binding.annotation.text.toString()

            if(nom.isEmpty() || prenom.isEmpty() || nbr.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs !", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val new : AnnuaireItem = AnnuaireItem(nom, prenom, nbr, note)

            model.insertItem(new)

            model.insertInfo.observe(this) {
                var text : String = if(it < 0) "erreur !" else "succès !"

                Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            }
        }
    }
}