package com.example.tp7.adapter

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.text.HtmlCompat
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.example.tp7.data.Pays
import com.example.tp7.databinding.ItemLayoutBinding

class PaysAdapter(
    val listPays: MutableList<Pays>,
    val length: Int
) : RecyclerView.Adapter<PaysAdapter.VH>() {

    var paysChecked: Pays? = null

    class VH(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        lateinit var pays: Pays
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        val holder = VH(binding)

        holder.itemView.setOnClickListener {

            //if not this -> change attribut
            val index = holder.absoluteAdapterPosition
            if (paysChecked == null || paysChecked != listPays.get(index)){

                it.setBackgroundColor(Color.argb(0.5f, 0.2f, 0.2f, 0.2f))


                if (paysChecked!=null){
                    parent.get(listPays.indexOf(paysChecked))
                        .setBackgroundColor(
                            if (listPays.indexOf(paysChecked) % 2 == 0) {
                                Color.argb(0.1f, 0.3f, 0.3f, 0.0f)
                            } else {
                                Color.argb(0.1f, 0.0f, 0.3f, 0.3f)
                            }
                        )
                }

                paysChecked=listPays.get(index)

            }else{
                if (holder.absoluteAdapterPosition % 2 == 0) {
                    it.setBackgroundColor(Color.argb(0.1f, 0.3f, 0.3f, 0.0f))
                } else {
                    it.setBackgroundColor(Color.argb(0.1f, 0.0f, 0.3f, 0.3f))
                }
                if (listPays.get(index)==paysChecked){
                    paysChecked=null
                }
            }




        }


        return holder
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.pays = listPays[position]

        val t : String =
            "<font color=#cc0029>${holder.pays.pays.dropLast( (holder.pays.pays.length)-(length))}</font> <font color=#ffcc00>${holder.pays.pays.drop(length)}</font>"

        with(holder.binding) {
            pays.text = HtmlCompat.fromHtml(t, HtmlCompat.FROM_HTML_MODE_LEGACY)
            capitale.text = holder.pays.capitale
            superficie.text =
                if (holder.pays.superficie != null) holder.pays.superficie.toString() else ""
            continent.text = holder.pays.continent
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            holder.itemView.setBackgroundColor(
                if(paysChecked == listPays.get(position)){
                    Color.argb(0.5f, 0.2f, 0.2f, 0.2f)
                }else if (holder.absoluteAdapterPosition % 2 == 0) {
                    Color.argb(0.1f, 0.3f, 0.3f, 0.0f)
                } else {
                    Color.argb(0.1f, 0.0f, 0.3f, 0.3f)
                }
            )

        }

    }

    override fun getItemCount(): Int = listPays.size

}