package fr.irif.zielonka.bitsandpizzas

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import fr.irif.zielonka.bitsandpizzas.databinding.FragmentLayoutBinding


/**
 * A fragment representing a list of Items.
 */
class PastaItemFragment(val adapter : RecyclerView.Adapter<PastaAdapter.VH>, layout : Int ) : Fragment(layout) {

    private lateinit var title : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            title = it.getString(TITLE) ?: ""
        }
    }
    private var binding : FragmentLayoutBinding? = null

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLayoutBinding.bind(view)
        binding!!.title.text = title
        binding!!.recycler.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    companion object {
        private const val TITLE = "title"
        @JvmStatic
        fun newInstance(title: String, adapter : RecyclerView.Adapter<PastaAdapter.VH>,
        //RecyclerView.Adapter<RecyclerView.ViewHolder> ,
            layout : Int) =
            PastaItemFragment(adapter, layout).apply {
                arguments = Bundle().apply {
                    putString(TITLE, title)
                }
            }
    }
}