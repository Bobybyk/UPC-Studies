package fr.irif.zielonka.recyclerviewwithoutconfigchange

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import androidx.recyclerview.widget.RecyclerView

/* colors  :  une liste mutable de tous les couleurs à afficher */
class MyRecycleAdapter(val colors: MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    //checked : contient les Strings colors  à l'état "checked"
    val checked = mutableListOf<String>()

    val listener = View.OnClickListener { view ->
        (view as CheckedTextView).toggle()  /* il faut changer la valeur de la propriété checked explicitement */

        /* mettre à jour la liste de colors qui sont à l'état "checked" */
        if (view.isChecked) {
            checked.add(view.text.toString())
        } else {
            checked.remove(view.text.toString())
        }
    }

    fun removeChecked() {
        colors.removeAll(checked)
        checked.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        //créer View d'un élément de la liste à partir de fichier layout xml
        val v = LayoutInflater
            .from(parent.getContext())
            .inflate(android.R.layout.simple_list_item_checked, parent, false)

        /*installer le listener sur chaque View */
        v.setOnClickListener(listener)

        //créer et retourner le ViewHolder
        return VH(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        /* recuperer la View :
         * holder.itemView c'est la View associée à ce holder */
        val checkedTextView = holder.itemView as CheckedTextView

        /* mettre la valeur colors[position] dans la View */
        checkedTextView.text = colors[position]

        /* mettre à jour la propriété checked de la View */
        checkedTextView.isChecked = checked.contains(colors[position])
    }

    /* retourner le nombre d'élément à afficher dans le RecyclerView */
    override fun getItemCount(): Int = colors.size

    //ViewHolder : ici il ne contient rien d'utile sauf une référence vers la View
    class VH(itemView: View) : RecyclerView.ViewHolder(itemView)
}