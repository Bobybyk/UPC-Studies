package tp2.ex2;

/** L'interface BoundedQueue<T> décrit les méthodes associées au type de données
 * abstrait des files (<i>queue</i> en anglais) de taille bornée.
 *
 * Une file est une structure de donnée modifiable supportant l'insertion et
 * suppression de données, et assurant une politique <i>Last In, First Out</i>.
 */
public interface BoundedQueue<T> {
    /**
     * Renvoie le nombre total d'élément actuellement présents dans la file.
     */
    int occupancy();

    /**
     * Renvoie le nombre total de cases libres dans la file.
     */
    int freeSlots();

    /**
     * Supprime tous les éléments de la file.
     */
    void clear();

    /**
     * Ajoute un élément à la file.
     *
     * @param L'élément à ajouter.
     * @throws Lève EmptyQueueException si la file est vide.
     */
    void push(T elem) throws FullQueueException;

    /**
     * Supprime un élément de la file.
     *
     * @return L'élément supprimé.
     * @throws Lève EmptyQueueException si la file est vide.
     */
    T pop() throws EmptyQueueException;
}
