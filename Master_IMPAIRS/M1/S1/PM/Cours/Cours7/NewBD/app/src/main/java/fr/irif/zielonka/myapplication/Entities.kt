package fr.irif.zielonka.myapplication

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


// constraint UNIQUE for the pair (name, firstName),
// this cannot be done without defining an index for this couple
@Entity( indices = [Index( value = [ "name", "firstName" ], unique = true  )])
data class Author(
    @PrimaryKey(autoGenerate = true)
    val idAuthor: Long = 0,
    var name: String,
    var firstName: String

)
/*
@Entity
data class Book(private val titre: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    fun getTitre() = titre
}
*/

@Entity
data class Book(val title: String) {
    @PrimaryKey(autoGenerate = true)
    var idBook: Long = 0
}

// la table AuthorBook est composée de deux foreign keys,
// et le couple (idAuthor, idBook) est aussi la clé primaire de cette table
// en plus il y a un index sur la colonne idBook
@Entity(
    primaryKeys = ["idAuthor", "idBook"],  //array literal replaces arrayOf()
    indices = [Index(value = ["idBook"] )], //index sur idBook
    foreignKeys = [ForeignKey(
        entity = Book::class,           //l'autre table
        parentColumns = ["idBook"],     //la colonne dans la table Book
        childColumns = [ "idBook" ],    //la colonne correspondante de la table AuthorBook
        deferred = true,
        onDelete = ForeignKey.CASCADE
    ), ForeignKey(                           //deuxième foreignKey
        entity = Author::class,           //l'autre table
        parentColumns = [ "idAuthor" ],   //la colonne dans la table Author
        childColumns = [ "idAuthor" ],    //la colonne correspondante dans AuthorBook
        deferred = true,
        onDelete = ForeignKey.CASCADE
    )]

)
data class AuthorBook(
    val idAuthor: Long,
    val idBook: Long
)


