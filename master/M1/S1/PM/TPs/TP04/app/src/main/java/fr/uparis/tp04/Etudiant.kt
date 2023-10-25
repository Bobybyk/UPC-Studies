package fr.uparis.tp04

data class Etudiant(val prenom: String, val nom: String) {
    override fun toString(): String {
        return "${prenom} ${nom}"
    }
}