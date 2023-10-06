package fr.irif.zielonka.troisboutonsfragmets.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import fr.irif.zielonka.troisboutonsfragmets.ActivityModel
import fr.irif.zielonka.troisboutonsfragmets.R
import fr.irif.zielonka.troisboutonsfragmets.databinding.MainFragmentBinding


/* un  fragment composé d'un Button et un TextView qui affiche le nombre de clicks sur le Button */
class ButtonFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance(buttonText: String, numeroFragment: Int): Fragment {
            return ButtonFragment().apply {
                arguments = Bundle().apply {
                    putString("buttonText", buttonText)
                    putInt("numeroFragment", numeroFragment)
                }
            }
        }
    }
    private lateinit var binding : MainFragmentBinding
    //private val activityViewModel : ActivityModel by activityViewModels()
    private  val activityViewModel : ActivityModel by lazy{ ViewModelProvider(requireActivity()).get(ActivityModel::class.java)}

    private var numeroFragment : Int = 0

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //activityViewModel = ViewModelProvider(requireActivity()).get(ActivityModel::class.java)
    }
*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /* créer le binding */
        binding = MainFragmentBinding.bind( view )
        binding.button.text = requireArguments().getString("buttonText", "invalide") //?: "empty"
        numeroFragment = requireArguments().getInt("numeroFragment")

        var l = activityViewModel.compteurs.value!![numeroFragment]
        binding.compteur.text="$l"

        binding.button.setOnClickListener{
            /* récupérer la liste de valeurs de compteurs */
            val list = activityViewModel.compteurs.value
            var l = ++ list!![numeroFragment]
            val newCompeurList = list.toMutableList()
            Log.d("listener frag", "$newCompeurList")
            Log.d("listener frag", "l=$l")
            activityViewModel.compteurs.value = newCompeurList
            binding.compteur.text = "$l"
        }

    }

    override fun onDetach() {
        super.onDetach()
        binding.button.setOnClickListener(null)
    }
}