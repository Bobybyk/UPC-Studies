package com.example.test_exam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList

class MyRecycleAdapter(
    val listeEtudiant: MutableList<Etudiant>,
    private val couleurPaire: Int,
    private val couleurImpaire: Int,
    private val couleurChecked: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val checked = mutableListOf<Int>()
    private val callback = object : SortedList.Callback<Etudiant>() {

        override fun compare(o1: Etudiant?, o2: Etudiant?): Int {
            val cmpNoms = o1!!.nom.compareTo(o2!!.nom)
            val cmpPrenoms = o1!!.prenom.compareTo(o2!!.prenom)
            return if (cmpNoms != 0) cmpNoms else cmpPrenoms
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

    val sortedList = SortedList(Etudiant::class.java, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        val viewHolder = VH(v)
        val listener = View.OnClickListener { _ ->
            if (viewHolder.absoluteAdapterPosition in checked) {
                if (viewHolder.absoluteAdapterPosition % 2 == 0) viewHolder.itemView.setBackgroundColor(this.couleurPaire)
                else viewHolder.itemView.setBackgroundColor(this.couleurImpaire)
                checked.remove(viewHolder.absoluteAdapterPosition)
            }
            else {
                viewHolder.itemView.setBackgroundColor(this.couleurChecked)
                checked.add(viewHolder.absoluteAdapterPosition)
            }
        }
        v.setOnClickListener(listener)
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val cardView = holder.itemView as CardView
        cardView.findViewById<TextView>(R.id.nom).text = sortedList[position].nom
        cardView.findViewById<TextView>(R.id.prenom).text = sortedList[position].prenom

        if (position % 2 == 0) cardView.setBackgroundColor(this.couleurPaire)
        else cardView.setBackgroundColor(this.couleurImpaire)
    }

    override fun getItemCount(): Int = sortedList.size()

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView)
}