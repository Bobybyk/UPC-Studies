package fr.irif.zielonka.bitsandpizzas

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.irif.zielonka.bitsandpizzas.databinding.FragmentLayoutBinding

/* cration  de fragment, constructeur sans argument */
class PizzaItemFragment(val adapter : RecyclerView.Adapter<PizzaAdapter.VH>, layout : Int)
    : Fragment() {

    private lateinit var title : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            title = it.getString(TITLE) ?: ""
            Log.d(PizzaItemFragment.TAG, "title =$title")
        }
    }
    private  var binding : FragmentLayoutBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentLayoutBinding.inflate(inflater)
        binding!!.title.text = title
        binding!!.recycler.adapter = adapter
        return binding!!.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
    companion object {
        const val TAG = "PizzaItemFragment"
        private const val TITLE = "title"
        @JvmStatic
        fun newInstance(title: String, //adapter : PizzaAdapter,
        adapter : RecyclerView.Adapter<PizzaAdapter.VH> ,
            layout : Int) =
            PizzaItemFragment(adapter, layout).apply {
                arguments = Bundle().apply {
                    putString(TITLE, title)
                }
            }
    }
}