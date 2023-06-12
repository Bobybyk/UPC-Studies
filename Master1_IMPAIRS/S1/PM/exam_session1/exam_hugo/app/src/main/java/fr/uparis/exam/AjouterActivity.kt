package fr.uparis.exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import fr.irif.zielonka.examjanviera.AnnuaireItem
import fr.uparis.exam.databinding.ActivityAjouterBinding

class AjouterActivity : AppCompatActivity() {

    // Déclare une variable binding qui sera utilisée pour lier le layout de l'activité à la classe.
    private lateinit var binding : ActivityAjouterBinding

    /*
     * Cette ligne crée une propriété model qui est une instance de AjoutViewModel.
     * La propriété est définie en utilisant la délégation by lazy, ce qui signifie que
     * l'initialisation de la propriété est retardée jusqu'à ce qu'elle soit réellement utilisée
     * pour la première fois.
     */
    private val model by lazy {
        ViewModelProvider(this)[AjoutViewModel::class.java]
    }

    /*
     * La méthode onCreate() est appelée lors de la création de l'activité.
     * Dans cette méthode, le layout de l'activité est gonflé en utilisant ActivityAjouterBinding et
     * affecté à la variable binding. Ensuite, le layout est défini comme
     * le contenu de l'activité en utilisant setContentView().
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAjouterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
         * Ce bloc de code définit un listener pour le bouton "ajouter" dans l'activité.
         * Lorsque le bouton est cliqué, il récupère les valeurs des champs de texte
         * (nom, prénom, numéro de téléphone, note) à partir de binding.
         *
         * Ensuite, il vérifie si les champs obligatoires (nom, prénom, numéro de téléphone)
         * sont vides. Si l'un d'entre eux est vide, un message d'erreur est affiché à l'aide de Toast.
         * Sinon, un nouvel objet AnnuaireItem est créé avec les valeurs des champs de texte.
         *
         * La méthode insertItem() de model (instance de AjoutViewModel) est appelée pour
         * insérer l'élément dans la base de données. Ensuite, la propriété insertInfo de
         * model est observée. Lorsque la valeur de insertInfo change, le code à l'intérieur de
         * observe est exécuté. Il affiche un message de succès ou d'erreur en fonction de
         * la valeur de insertInfo à l'aide de Toast.
         * En résumé, cette activité représente l'écran d'ajout d'un nouvel élément.
         * Lorsque le bouton "ajouter" est cliqué, il récupère les valeurs des champs de texte,
         * vérifie leur validité, insère l'élément dans la base de données à l'aide du ViewModel,
         * puis affiche un message de succès ou d'erreur en conséquence.
         */
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