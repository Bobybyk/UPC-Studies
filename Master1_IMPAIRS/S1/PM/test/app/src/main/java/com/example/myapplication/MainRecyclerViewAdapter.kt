package com.example.myapplication

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemLayoutBinding

class MainRecyclerViewAdapter(private var items : MutableList<Library>) : RecyclerView.Adapter<MainRecyclerViewAdapter.VH>() {

    private var selected : Library? = null

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

    // returns the number of selected elements
    override fun getItemCount() : Int = items.size

    // retourne les éléments sélectionnées ou null s'il n'y en a pas
    fun getSelected() : Annuaire? = selected

    private fun setColor(item : CardView, position: Int) {
        if(position % 2 == 0)
            item.setCardBackgroundColor(ColorUtils.setAlphaComponent(Color.CYAN, 100))
        else
            item.setCardBackgroundColor(ColorUtils.setAlphaComponent(Color.GRAY, 100))
    }
}