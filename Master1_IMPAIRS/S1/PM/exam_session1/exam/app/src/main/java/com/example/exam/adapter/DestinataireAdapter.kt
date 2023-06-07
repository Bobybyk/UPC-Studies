package com.example.exam.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exam.R
import com.example.exam.data.Destinataire
import androidx.core.graphics.ColorUtils


class DestinataireAdapter : RecyclerView.Adapter<DestinataireAdapter.DestinataireViewHolder>() {

    private var destinataires: List<Destinataire> = emptyList()
    private val selectedItems = mutableListOf<Int>()

    fun setDestinataires(destinataires: List<Destinataire>) {
        this.destinataires = destinataires
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinataireViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.destinataire_item, parent, false)
        val viewHolder = DestinataireViewHolder(itemView)

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            if (selectedItems.contains(position)) {
                selectedItems.remove(position)
            } else {
                selectedItems.add(position)
            }
            notifyItemChanged(position)
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: DestinataireViewHolder, position: Int) {
        val destinataire = destinataires[position]
        holder.bind(destinataire)

        val isSelected = selectedItems.contains(position)
        // Définir les couleurs de fond pour les positions paires et impaires et la liste de positions sélectionnées
        val backgroundColor = if (isSelected) {
            ColorUtils.setAlphaComponent(Color.RED, 120)
        } else {
            if (position % 2 == 0) {
                ColorUtils.setAlphaComponent(Color.CYAN, 50)
            } else {
                ColorUtils.setAlphaComponent(Color.GRAY, 50)
            }
        }
        holder.itemView.setBackgroundColor(backgroundColor)
    }

    override fun getItemCount(): Int {
        return destinataires.size
    }

    inner class DestinataireViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nomTextView: TextView = itemView.findViewById(R.id.nomTextView)
        private val prenomTextView: TextView = itemView.findViewById(R.id.prenomTextView)
        private val mailTextView: TextView = itemView.findViewById(R.id.mailTextView)
        private val annotationTextView: TextView = itemView.findViewById(R.id.annotationTextView)
        private val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)

        fun bind(destinataire: Destinataire) {
            nomTextView.text = destinataire.nom
            prenomTextView.text = destinataire.prenom
            mailTextView.text = destinataire.mail
            annotationTextView.text = destinataire.annotation
            // Mettre à jour l'état isChecked du CheckBox en fonction de l'annotation
            checkBox.isChecked = destinataire.annotation != ""
        }

    }
}
