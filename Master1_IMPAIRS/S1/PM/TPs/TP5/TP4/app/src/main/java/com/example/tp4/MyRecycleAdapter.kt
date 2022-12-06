package com.example.tp4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedList.Callback

/*Implement VH au lieu de ViewHolder pour profiter des avantages des atr dans VH*/
class MyRecycleAdapter(
    private val etudiants: MutableList<Etudiant>,
    private val couleurPaire: Int,
    private val couleurImpair: Int,
    private val couleurChecked: Int,
    var sortColumn : String
) : RecyclerView.Adapter<MyRecycleAdapter.VH>() {

    val callback = object : Callback<Etudiant>() {
        override fun compare(o1: Etudiant?, o2: Etudiant?): Int {
            val cmpNoms = o1!!.nom.compareTo(o2!!.nom)
            val cmpPrenoms = o1!!.prenom.compareTo(o2!!.prenom)
            val cmpCheck = checked.contains(o2).compareTo(checked.contains(o1))

            return if (sortColumn=="prenom"){
                if (cmpPrenoms != 0) cmpPrenoms else cmpNoms
            }else if(sortColumn=="nom"){
                if (cmpNoms != 0) cmpNoms else cmpPrenoms
            }else{
                if (cmpCheck != 0) cmpCheck else (if (cmpNoms != 0) cmpNoms else cmpPrenoms)
            }

        }

        override fun onInserted(position: Int, count: Int) {
            notifyItemRangeInserted(position, count)
        }

        override fun onRemoved(position: Int, count: Int) {
            notifyItemRangeRemoved(position, itemCount)
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            notifyItemMoved(fromPosition, toPosition)
        }

        override fun onChanged(position: Int, count: Int) {
            notifyItemRangeChanged(position, count)
        }

        override fun areContentsTheSame(oldItem: Etudiant?, newItem: Etudiant?): Boolean =
            (oldItem == null && newItem == null) || newItem!!.equals(oldItem)

        override fun areItemsTheSame(item1: Etudiant?, item2: Etudiant?): Boolean =
            item1 === item2
    }

    private val checked: MutableList<Etudiant> = mutableListOf()

    private val sortedList = SortedList(Etudiant::class.java, callback)

    init {
        sortedList.addAll(etudiants)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        //créer View d'un élément de la liste à partir de fichier layout xml
        val v = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_layout, parent, false)

        val holder: VH = VH(v)

        /*installer le listener sur chaque View */

        v.findViewById<CardView>(R.id.cardView).setOnClickListener {
            val item = (it as CardView)
            val index = holder.absoluteAdapterPosition
            if (checked.contains(sortedList.get(index))) {
                checked.remove(sortedList.get(index))
                changeColor(index, item)
            } else {
                checked.add(sortedList.get(index))
                item.setCardBackgroundColor(couleurChecked)
            }
            println(checked)
            if (sortColumn=="checked"){
                sortedList.updateItemAt(index, sortedList.get(index))
                notifyDataSetChanged()
            }
        }

        //créer et retourner le ViewHolder
        return holder
    }

    override fun onBindViewHolder(holder: VH, position: Int) {

        /* recuperer la View :
         * holder.itemView c'est la View associée à ce holder */
        val element = holder.itemView as CardView

        /* mettre la valeur colors[position] dans la View */
        /* au lieu de element.findViewById<TextView>(R.id.prenom) */
        holder.nom.text = sortedList.get(position).nom
        holder.prenom.text = sortedList.get(position).prenom

        /* mettre à jour la propriété checked de la View */

        //ContextCompat.getColor(context, R.color.couleurPaire);
        changeColor(position, element.findViewById<CardView>(R.id.cardView))
    }

    fun update() {
        sortedList.clear()
        sortedList.addAll(etudiants)
    }

    fun getSelected(): MutableList<Etudiant> = checked

    override fun getItemCount(): Int = sortedList.size()

    //ViewHolder : Permet de ne pas a utiliser find dans onBind
    class VH(itemView: View) : ViewHolder(itemView) {
        val nom: TextView = itemView.findViewById(R.id.nom)
        val prenom: TextView = itemView.findViewById(R.id.prenom)
    }

    private fun changeColor(position: Int, element: CardView) {
        if (checked.contains(sortedList.get(position))){
            element.setCardBackgroundColor(couleurChecked)
        }else{
            if (position % 2 == 1) element.setCardBackgroundColor(couleurImpair)
            else element.setCardBackgroundColor(couleurPaire)
        }
    }

}