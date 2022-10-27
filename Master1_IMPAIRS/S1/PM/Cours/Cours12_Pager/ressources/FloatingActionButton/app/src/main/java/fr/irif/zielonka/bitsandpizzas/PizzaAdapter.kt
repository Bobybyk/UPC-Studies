package fr.irif.zielonka.bitsandpizzas

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import fr.irif.zielonka.bitsandpizzas.data.PizzaItem

import fr.irif.zielonka.bitsandpizzas.databinding.RecyclerItemBinding

private const val TAG="PizzaAdapter"

class PizzaAdapter( val values: MutableList<PizzaItem> )
    : RecyclerView.Adapter<PizzaAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val holder = VH( RecyclerItemBinding.inflate( LayoutInflater.from(parent.context), parent,
            false))
        return holder
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        Log.d(TAG, "position=$position")
        val item = values[position]
        Log.d(TAG, "pizza=$item")
        holder.binding.content.text  = item.content
        holder.pizza = item
    }

    override fun getItemCount(): Int = values.size

    class VH(val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        lateinit var pizza: PizzaItem

    }
}