package fr.uparis.exam

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.RecyclerView
import fr.irif.zielonka.examjanviera.Annuaire
import fr.uparis.exam.databinding.ItemLayoutBinding

/*
 * Cette ligne définit la classe MainRecyclerViewAdapter qui étend RecyclerView.Adapter et
 * est paramétrée avec la classe interne VH comme type générique. MainRecyclerViewAdapter utilise
 * une liste mutable d'objets Annuaire comme source de données pour les éléments de la liste
 */
class MainRecyclerViewAdapter(private var items : MutableList<Annuaire>) : RecyclerView.Adapter<MainRecyclerViewAdapter.VH>() {

    private var selected : Annuaire? = null

    /*
     * Cette ligne définit la classe interne VH qui hérite de RecyclerView.ViewHolder et
     * est paramétrée avec une instance de ItemLayoutBinding pour lier
     * les vues de l'élément de la liste à sa représentation dans le code.
     */
    class VH(val binding : ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    /*
     * Cette fonction onCreateViewHolder est appelée lorsque le RecyclerView a besoin de créer
     * une nouvelle instance de la vue d'un élément de la liste. Elle inflates le layout
     * ItemLayoutBinding en utilisant le LayoutInflater, crée une instance de VH avec
     * la liaison (binding) et définit un écouteur de clic sur la vue cardView.
     * Lorsque l'élément est cliqué, il met à jour l'élément sélectionné et
     * change la couleur de fond de la vue en conséquence.
     */
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

    /*
     * Cette fonction onBindViewHolder est appelée lorsque le RecyclerView a besoin de lier les
     * données d'un élément de la liste à sa vue correspondante. Elle récupère les données de
     * l'élément à la position spécifiée dans la liste items et les attribue aux
     * vues correspondantes dans ItemLayoutBinding. Elle appelle également la fonction setColor
     * pour définir la couleur de fond de la vue en fonction de la position de l'élément.
     */
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