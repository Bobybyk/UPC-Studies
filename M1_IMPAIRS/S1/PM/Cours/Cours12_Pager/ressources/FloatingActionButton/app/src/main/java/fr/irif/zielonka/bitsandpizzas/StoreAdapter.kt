package fr.irif.zielonka.bitsandpizzas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.irif.zielonka.bitsandpizzas.data.StoreItem
import fr.irif.zielonka.bitsandpizzas.databinding.RecyclerItemBinding

class StoreAdapter(
    private val values: MutableList<StoreItem>
) : RecyclerView.Adapter<StoreAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

        return VH(
            RecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = values[position]
        holder.store = item
        holder.binding.content.text = item.content
    }

    override fun getItemCount(): Int = values.size

    inner class VH(var binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        lateinit var store: StoreItem
    }

}