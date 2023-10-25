val callback = object : SortedList.Callback<Etudiant>() {

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
