package com.example.exam.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exam.R
import com.example.exam.data.Destinataire

class DestinataireAdapter : RecyclerView.Adapter<DestinataireAdapter.DestinataireViewHolder>() {

    private var destinataires: List<Destinataire> = emptyList()

    fun setDestinataires(destinataires: List<Destinataire>) {
        this.destinataires = destinataires
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinataireViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.destinataire_item, parent, false)
        return DestinataireViewHolder(view)
    }

    override fun onBindViewHolder(holder: DestinataireViewHolder, position: Int) {
        val destinataire = destinataires[position]
        holder.bind(destinataire)
    }

    override fun getItemCount(): Int {
        return destinataires.size
    }

    inner class DestinataireViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nomTextView: TextView = itemView.findViewById(R.id.nomTextView)
        private val prenomTextView: TextView = itemView.findViewById(R.id.prenomTextView)
        private val mailTextView: TextView = itemView.findViewById(R.id.mailTextView)
        private val annotationTextView: TextView = itemView.findViewById(R.id.annotationTextView)

        fun bind(destinataire: Destinataire) {
            nomTextView.text = destinataire.nom
            prenomTextView.text = destinataire.prenom
            mailTextView.text = destinataire.mail
            annotationTextView.text = destinataire.annotation
        }
    }
}
