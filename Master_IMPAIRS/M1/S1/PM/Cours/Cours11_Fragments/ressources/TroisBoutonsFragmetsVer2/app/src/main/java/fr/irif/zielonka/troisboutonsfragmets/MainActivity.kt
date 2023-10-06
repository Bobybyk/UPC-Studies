package fr.irif.zielonka.troisboutonsfragmets


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import fr.irif.zielonka.troisboutonsfragmets.databinding.MainActivityBinding
import fr.irif.zielonka.troisboutonsfragmets.ui.main.ButtonFragment

class MainActivity : AppCompatActivity() {
    val binding : MainActivityBinding by lazy{ MainActivityBinding.inflate( layoutInflater ) }
    val  buttonsAjouter by lazy{  listOf(binding.buttonA, binding.buttonB, binding.buttonC ) }

    //val model : ActivityModel by viewModels()
    val model by lazy{ ViewModelProvider(this).get(ActivityModel::class.java)  }
    /* les fragemnt tags pour les trois fragments */
    private val fragmentsTags = arrayOf("A","B","C")

    /* les RadioGroups */
    private val radioGroups by lazy{  listOf<RadioGroup>( binding.rg0, binding.rg1, binding.rg2 )   }
    /* id de parents de trois fragments
    : voir le fichier layout */
    val idOnFrameLayout = listOf(R.id.one, R.id.two, R.id.three )
    //var indexOnFrameLayout = 0

    val idOffFrameLayout = listOf(R.id.four, R.id.five, R.id.six )
    //var indexOffFrameLayout = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /* mémoriser le numéro de bouton comme un tag, à faire dans xml ? */
        for(i in buttonsAjouter.indices )
            buttonsAjouter[i].tag = i

        ajouterListeners()

        model.compteurs.observe(this){
            val v = it[0] + it[1] + it[2]
            Log.d("observe", "somme=$v" )
            binding.somme.text = "$v"
        }

    }// end onCreate

    //listener pour les boutons ajouterFragment
    val listenerAjouter = View.OnClickListener{
        /* decoder quel bouton a été actioné en regardant le tag du bouton */
        val numeroBouton = it.tag as Int  /* le numero du bouton */
        Log.d("listener", "numeroButton=$numeroBouton")
        /* si le fragment déjà affiché alors rien à faire */
        val fragment = supportFragmentManager.findFragmentByTag( fragmentsTags[numeroBouton] )
        if(fragment != null ){
            AlertDialog.Builder(this)
                .setPositiveButton("OK"){ d, _ -> d.dismiss() }
                .setMessage("le fragment est déjà affiché").show()
            return@OnClickListener
        }

        var suffix: String =
            when (radioGroups[numeroBouton].checkedRadioButtonId) {
                R.id.r0, R.id.r2, R.id.r4 -> "on stack"
                R.id.r1, R.id.r3, R.id.r5 -> "off stack"
                else -> {
                    AlertDialog.Builder(this)
                        .setPositiveButton("OK"){ d, _ -> d.dismiss() }
                        .setMessage("sélectionner on/off").show()
                    return@OnClickListener
                }
            }

        /* label à afficher dans le bouton du fragment composé de tag du fragment et de suffix */
        val label = "\"${fragmentsTags[numeroBouton]}\" $suffix"
        Log.d( "listener", "label=$label")

        /* ajputer un listener qui observe les changements dans le backstack */
        supportFragmentManager.addOnBackStackChangedListener {  model.indexOnFrameLayout = supportFragmentManager.backStackEntryCount }

       // FragmentManager.OnBackStackChangedListener {  }
        val transaction = supportFragmentManager.beginTransaction()
            .add( if( suffix == "on stack") idOnFrameLayout[model.indexOnFrameLayout++] else idOffFrameLayout[model.indexOffFrameLayout++], /* identifiant de FrameLayout qui accueillira le fragment */
                ButtonFragment.newInstance( label, numeroBouton ), /* le fragment */
                fragmentsTags[numeroBouton]  /* le tag du fragment */)

        Log.d("transaction", "idOn=${model.indexOnFrameLayout}, idOff=${model.indexOffFrameLayout}")

        when( radioGroups[ numeroBouton ].checkedRadioButtonId  ){
            R.id.r0,  R.id.r2,  R.id.r4 -> transaction.addToBackStack(null)
            R.id.r1,  R.id.r3,  R.id.r5 -> {}
            else -> throw Exception("which radioButton?")
        }

        /* terminer la transaction */
        transaction.commit()
        return@OnClickListener
    }/* end listener */

    fun ajouterListeners(){
        for( b in buttonsAjouter ) {
            b.setOnClickListener(listenerAjouter)
            Log.d("boucle 2", "listener pour bouton ")
        }
    }

}