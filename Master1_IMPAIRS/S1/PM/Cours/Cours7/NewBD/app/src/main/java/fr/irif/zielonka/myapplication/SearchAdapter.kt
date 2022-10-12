package fr.irif.zielonka.myapplication

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import fr.irif.zielonka.myapplication.databinding.AuthorItemLayoutBinding

class SearchAdapter(var checkedAuthors : MutableList<Author> ) : RecyclerView.Adapter<SearchAdapter.VH>() {

    var authors : List<Author> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = AuthorItemLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        val holder = SearchAdapter.VH( binding )
        holder.binding.check.setOnClickListener{
            it as CheckBox
            if(it.isChecked)
                checkedAuthors.add(holder.author)
            else
                checkedAuthors.remove(holder.author)
        }
        return holder
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        /* holder contient une référence vers Author */
        holder.author = authors[position]
        /* holder contient une référence vers binding ce qui permet de recupérer
        * les views sans findViewById() */
        holder.binding.nom.text = holder.author.name
        holder.binding.prenom.text = holder.author.firstName
        holder.binding.check.isChecked = holder.author in checkedAuthors
        if(position % 2 == 0)
            holder.binding.root.setBackgroundColor(Color.argb(0.5f, 1.0f, 0.0f, 0.0f))
        else
            holder.binding.root.setBackgroundColor(Color.argb( 0.5f, 0.0f, 1.0f, 0.0f))
    }

    override fun getItemCount(): Int = authors.size

   /* viewHolder contient maintenant une reference vers Author et une reference vers binding
   * d'un item de la liste  affiché dans le RecyclerView */
    class VH( val binding : AuthorItemLayoutBinding ) : RecyclerView.ViewHolder( binding.root ){
        lateinit var author : Author
    }
}