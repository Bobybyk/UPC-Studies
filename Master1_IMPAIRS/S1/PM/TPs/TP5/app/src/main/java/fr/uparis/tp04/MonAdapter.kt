package fr.uparis.tp04

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList

class MonAdapter(var students : MutableList<Etudiant>, var couleurPair : Int, var couleurImpair : Int, var couleurChecked : Int, var sortColumn : String) : RecyclerView.Adapter<MonAdapter.VH>() {

    val checked = mutableListOf<Etudiant>()

    val callback = object : SortedList.Callback<Etudiant>() {

        override fun compare(o1: Etudiant?, o2: Etudiant?): Int {
            val cmpNoms = o1!!.nom.compareTo(o2!!.nom)
            val cmpPrenoms = o1!!.prenom.compareTo(o2!!.prenom)

            return if (sortColumn=="nom") {
                if (cmpNoms != 0) cmpNoms else cmpPrenoms;
            } else if (sortColumn=="prenom") {
                if (cmpPrenoms  != 0) cmpPrenoms else cmpNoms;
            } else {
                cmpNoms;
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

    val sortedList = SortedList<Etudiant>(Etudiant::class.java, callback)

    init {
        sortedList.addAll(students)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_layout,
                parent, false)

        val holder = VH(v)

        val card : CardView = v.findViewById(R.id.cardView)
        var listener = View.OnClickListener { view ->
            val item = (view as CardView)
            val index = holder.absoluteAdapterPosition
            if(checked.contains(sortedList[index])) {
                checked.remove(sortedList[index])
                when {
                    index % 2 == 0 -> item.setCardBackgroundColor(couleurPair)
                    index % 2 == 1 -> item.setCardBackgroundColor(couleurImpair)
                }
            } else {
                checked.add(sortedList[index])
                item.setCardBackgroundColor(couleurChecked)
            }
        }
        card.setOnClickListener(listener)

        return holder
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val card = holder.itemView as CardView
        holder.nom.text = sortedList[position].nom
        holder.prenom.text = sortedList[position].prenom
        when {
            checked.contains(sortedList[position]) -> card.setCardBackgroundColor(couleurChecked)
            position % 2 == 0 -> card.setCardBackgroundColor(couleurPair)
            position % 2 == 1 -> card.setCardBackgroundColor(couleurImpair)
        }
    }

    override fun getItemCount(): Int = sortedList.size()

    fun update() {
        sortedList.clear()
        sortedList.addAll(students)
    }

    fun getSelected() : MutableList<Etudiant> = checked

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nom : TextView = itemView.findViewById(R.id.nom)
        val prenom : TextView = itemView.findViewById(R.id.prenom)
    }
}
