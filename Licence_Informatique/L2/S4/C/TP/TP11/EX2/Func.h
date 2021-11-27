struct file{
void *first; /*pointeur debut de tableau*/
void *last; /*pointeur fin de tableau*/
size_t te; /*taille d’un element en octets*/
void *occupe; /*pointeur premier element de la file*/
void *libre; /*pointeur le premier element libre*/
};
typedef struct file *fifo;


fifo create_fifo( size_t capacite_init, size_t taille_elem);
void delete_fifo(fifo f);