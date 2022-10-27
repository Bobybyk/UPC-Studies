package fr.irif.zielonka.bitsandpizzas

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import fr.irif.zielonka.bitsandpizzas.databinding.FragmentLayoutBinding

/* création de fragment, constructeur avec l'argument layout */
class StoreItemFragment(val adapter : RecyclerView.Adapter<StoreAdapter.VH>, layout : Int ) : Fragment(layout) {

    private lateinit var title : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            title = it.getString(TITLE) ?: ""
        }
    }
    private  var binding : FragmentLayoutBinding?  = null
/*
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLayoutBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }
    */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLayoutBinding.bind(view)
        binding!!.title.text = title
        binding!!.recycler.adapter = adapter
       // adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
    companion object {
        private const val TITLE = "title"

        @JvmStatic
        fun newInstance(title: String, adapter : RecyclerView.Adapter<StoreAdapter.VH>,
            layout : Int) =
            StoreItemFragment(adapter, layout).apply {
                arguments = Bundle().apply {
                    putString(TITLE, title)
                }
            }
    }
}