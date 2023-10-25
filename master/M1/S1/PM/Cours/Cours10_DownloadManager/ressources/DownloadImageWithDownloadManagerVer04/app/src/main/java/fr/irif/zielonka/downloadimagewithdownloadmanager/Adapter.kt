package fr.irif.zielonka.downloadimagewithdownloadmanager

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.irif.zielonka.downloadimagewithdownloadmanager.databinding.ImageBinding

class Adapter : RecyclerView.Adapter<Adapter.VH>() {

    /* liste de Bitmaps à afficher */
    lateinit var bitmapList: List<Bitmap>

    /* le map qui pour chaque couple <i, b> où i une position sur la liste bitmapList
     * b == true si et seulement si le checkBox correspondant est sélectionné
     * Par défaut si (i !in mapListPositionToChecked ) alors b == false.
     */
    lateinit var mapListPositionToChecked: MutableMap<Int, Boolean>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.VH {
        val binding = ImageBinding.inflate( LayoutInflater.from(parent.context), parent, false)
        val holder = VH( binding )
        binding.checkbox.setOnClickListener{  mapListPositionToChecked[ holder.absoluteAdapterPosition ] = holder.binding.checkbox.isChecked    }
        return holder
    }

    override fun onBindViewHolder(holder: Adapter.VH, position: Int) {
        holder.binding.image.setImageBitmap(  bitmapList[position] )
        holder.binding.checkbox.isChecked = mapListPositionToChecked[position] ?: false
    }

    override fun getItemCount(): Int {
        return bitmapList.size
    }

    class VH(val binding : ImageBinding ) : RecyclerView.ViewHolder( binding.root )


}