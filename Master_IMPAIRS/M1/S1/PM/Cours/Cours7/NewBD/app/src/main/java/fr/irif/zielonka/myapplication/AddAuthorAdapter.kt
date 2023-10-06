package fr.irif.zielonka.myapplication

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import fr.irif.zielonka.myapplication.databinding.AuthorItemLayoutBinding

class AddAuthorAdapter(private var selectedAuthors: MutableList<Author>, sortType : String) :
    RecyclerView.Adapter<AddAuthorAdapter.VH>() {

    /* la liste de tous les auteurs */
    private var allAuthors: List<Author> = listOf()

    var sortType : String = sortType
        /* setter method for sortType */
        set(value){
            Log.d("AddAuthorAdapter", "setter = $value")
            sortedList = if( value == AddAuthorViewModel.byName )
                sortedListNom
            else
                sortedListPrenom
            Log.d("AddAuthorAdapter", "set : sortedList size ${sortedList.size()}")
            field = value
            notifyDataSetChanged()
        }

    /* critère de tri */
    //var sortType : String = AddAuthorViewModel.byName

    /* createSortedList crée une liste d'auteur triées selon nopm ou prénom en fonction
    de parametre type
     */
    fun createSortedList(sortType: String): SortedList<Author> = SortedList(Author::class.java,
        object : SortedList.Callback<Author>() {
            override fun compare(o1: Author?, o2: Author?): Int {
                if (o1 == null || o2 == null)
                    throw Exception("author null, this should never happen ")
                when (sortType) {
                    AddAuthorViewModel.byName -> {
                        val c = (o1.name ).compareTo(o2.name )
                        if (c != 0)
                            return c
                        return (o1.firstName ).compareTo(o2.firstName )
                    }
                    AddAuthorViewModel.byFirstname -> {
                        val c = (o1.firstName ).compareTo(o2.firstName )
                        if (c != 0)
                            return c
                        return (o1.name ).compareTo(o2.name )
                    }
                    else -> throw Exception("impossible")
                }
            }

            override fun onInserted(position: Int, count: Int) {
                notifyItemRangeInserted(position, itemCount)
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

            override fun areContentsTheSame(oldItem: Author?, newItem: Author?): Boolean =
                oldItem?.name == newItem?.name && oldItem?.firstName == newItem?.firstName

            override fun areItemsTheSame(item1: Author?, item2: Author?): Boolean =
                item1 === item2
        })

    private val sortedListNom = createSortedList(AddAuthorViewModel.byName)
    private val sortedListPrenom = createSortedList( AddAuthorViewModel.byFirstname)
    private lateinit var sortedList : SortedList<Author>

    init {
        sortedListNom.addAll(allAuthors)
        sortedListPrenom.addAll(allAuthors)
        this.sortType=sortType
        Log.d("Adapter init", "sortedListNom.size = ${sortedListNom.size()}")
        Log.d("Adapter init", "sortedListPrenom.size = ${sortedListPrenom.size()}")
    }

    /* ViewHolder contient les réféences vers Author and bindiing*/
    class VH(val binding: AuthorItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        lateinit var author: Author
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = AuthorItemLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        val holder = VH(binding)

        /* Installer un listener qui met à jour la liste selectedAuthors en fonction
        * de l'état de la CheckBox (de la valeur de la propriété isChecked de la CheckBox)
        */
        binding.check.setOnClickListener {
            it as CheckBox    //it : le paramètre par défaut de lambda
            if (it.isChecked) {
                selectedAuthors.add(holder.author)
            } else {
                selectedAuthors.remove(holder.author)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        /* mémoriser la référence vers l'auteur dans le holder */
        holder.author = sortedList[position]

        /* mettre  le nom et prenom de l'auteur à afficher dans le RecuclerView */
        holder.binding.prenom.text = holder.author.firstName
        holder.binding.nom.text = holder.author.name

        holder.itemView.setBackgroundColor(
            if (position % 2 == 0)
                Color.argb(30, 0, 220, 0)
            else
                Color.argb(30, 0, 0, 220)
        )
        /* Mettre à jour l'état de la CheckBox.
         * la propriété  isChecked de la CheckBox est true si est seulement si
         * idAuthor de l'auteur affiché est  dans la  liste checkedIds
         */
        holder.binding.check.isChecked = holder.author in selectedAuthors
    }

    override fun getItemCount(): Int = allAuthors.size

    /* Remplacer la liste de tous les auteurs par une nouvelle liste */
    fun setAuthors(authors: List<Author>, selectedAuthors: MutableList<Author>) {
        this.allAuthors = authors
        this.selectedAuthors = selectedAuthors
        sortedListNom.replaceAll( authors )
        sortedListPrenom.replaceAll( authors )
        sortType = sortType
        notifyDataSetChanged()
        Log.d("Adapter ", "setListAuthors $authors")
    }
}
