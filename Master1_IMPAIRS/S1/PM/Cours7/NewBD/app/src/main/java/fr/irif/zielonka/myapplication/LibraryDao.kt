package fr.irif.zielonka.myapplication

import androidx.lifecycle.LiveData
import androidx.room.*

data class AuthorName(
    val name : String,
    val firstName : String
)

class BooksAuthorsInfo(
    val name: String?,
    val firstName: String?,
    val title: String
)

class BookInfo(
    val idBook: Long
)

class AuthorInfo(
    val idAuthor : Long
)

@Dao
interface MyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBooks(vararg book: Book) : List<Long>


    @Insert(entity = Author::class, onConflict = OnConflictStrategy.IGNORE)
    fun insertAuthors(vararg authors: AuthorName) : List<Long>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAuthorsBooks(vararg ab: AuthorBook) : List<Long>

    @Delete
    suspend fun deleteAuthors( vararg authors: Author) : Int  //retourne le nombre de lignes supprimées

    @Delete
    suspend fun deleteAuthors( authors: MutableList<Author>) : Int  //retourne le nombre de lignes supprimées

    @Delete( entity = Author::class )
    fun deleteSomeAuthors(authors: MutableList<Author>) : Int  //retourne le nombre de lignes supprimées

    @Delete
    suspend fun deleteBooks( books : List<Book> ) : Int

    @Delete( entity = Book::class )
    suspend fun deleteSomeBooks(  books : List<BookInfo> ) : Int

    @Query( "DELETE FROM Book WHERE idBook = :key")
    suspend fun deleteOneBook( key : Long ) : Int

    @Query( "DELETE FROM Book" )
    suspend fun deleteAllBooks() : Int //retourne le nombre de lignes supprimées

    @Query( "DELETE FROM AuthorBook" )
    suspend fun deleteAllAuthorBook() : Int

    @Update
    suspend fun updateAuthors(vararg authors: Author)

    @Query("SELECT * FROM Author")
    fun loadAllAuthors(): LiveData<List<Author>>

    @Query("SELECT * FROM Author Where name like :pref || '%'")
    fun loadSomeAuthors( pref : String ): List<Author>

    @Query("SELECT * FROM Author WHERE name like :nom || '%'")
    fun loadAuthorsByNamePrefix(nom: String): LiveData<List<Author>>

    @Query( "SELECT * FROM Author where name LIKE :nom AND  firstName LIKE :prenom")
    fun loadAuthorsByPartialName( nom : String, prenom : String) : LiveData<List<Author>>


    @Query( "SELECT * FROM Author where name LIKE :nom || '%' AND  firstName LIKE :prenom || '%' ")
    fun getAuthorsByPartialName( nom : String, prenom : String) : LiveData<List<Author>>

    @Query( "SELECT title, idBook FROM  Book NATURAL JOIN  AuthorBook WHERE  " +
            " AuthorBook.idAuthor = :idAuthor ")
    fun loadAllBooksOf( idAuthor : Long ) : LiveData<List<Book>>

    @Query(
        "SELECT name, firstName, title FROM Author NATURAL JOIN  AuthorBook NATURAL JOIN Book "
    )
    fun loadAllAuthorsAndBooks(): LiveData<List<BooksAuthorsInfo>>

    @Query(
        "SELECT name, firstName, title FROM Author NATURAL JOIN AuthorBook NATURAL JOIN Book " +
                "WHERE Author.name LIKE :nom  AND Author.firstName LIKE :prenom  "
    )
    fun loadBooksOf( nom : String, prenom: String ): LiveData<List<BooksAuthorsInfo>>


}
