package fr.uparis.exam

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.RecyclerView
import fr.irif.zielonka.examjanviera.Annuaire
import fr.uparis.exam.databinding.ItemLayoutBinding

class MainRecyclerViewAdapter(private var items : MutableList<Annuaire>) : RecyclerView.Adapter<MainRecyclerViewAdapter.VH>() {

    private var selected : Annuaire? = null

    class VH(val binding : ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val view =  VH(binding)

        binding.cardView.setOnClickListener {
            val item = items[view.absoluteAdapterPosition]

            if(selected == item) {
                selected = null
                setColor(view.binding.cardView, view.absoluteAdapterPosition)
            } else {
                selected = item
                view.binding.cardView.setCardBackgroundColor(ColorUtils.setAlphaComponent(Color.RED, 100))
            }
        }

        return view
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.nom.text = items[position].nom
        holder.binding.prenom.text = items[position].prenom
        holder.binding.tel.text = items[position].numero
        holder.binding.checkBox.isChecked = items[position].annotation.isNotEmpty()

        setColor(holder.binding.cardView, position)
    }

    //returns the number of selected elements
    override fun getItemCount() : Int = items.size

    fun getSelected() : Annuaire? = selected

    private fun setColor(item : CardView, position: Int) {
        if(position % 2 == 0)
            item.setCardBackgroundColor(ColorUtils.setAlphaComponent(Color.CYAN, 100))
        else
            item.setCardBackgroundColor(ColorUtils.setAlphaComponent(Color.GRAY, 100))
    }
}