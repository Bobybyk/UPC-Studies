package com.example.tp7.adapter

import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.tp7.data.Pays
import com.example.tp7.databinding.ItemLayoutBinding

class PaysAdapter(
    val listPays: MutableList<Pays>
) : RecyclerView.Adapter<PaysAdapter.VH>() {

    class VH(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        lateinit var pays: Pays
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        val holder = VH(binding)

        return holder
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.pays = listPays[position]

        with(holder.binding) {
            pays.text = holder.pays.pays
            capitale.text = holder.pays.capitale
            superficie.text =
                if (holder.pays.superficie != null) holder.pays.superficie.toString() else ""
            continent.text = holder.pays.continent
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            holder.itemView.setBackgroundColor(
                if (holder.absoluteAdapterPosition % 2 == 0) {
                    Color.argb(0.1f, 0.3f, 0.3f, 0.0f)
                } else {
                    Color.argb(0.1f, 0.0f, 0.3f, 0.3f)
                }
            )
        }

    }

    override fun getItemCount(): Int = listPays.size

}