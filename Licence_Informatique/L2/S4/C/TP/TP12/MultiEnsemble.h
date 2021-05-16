#include <stddef.h>

typedef struct node node;
typedef node* mset;


mset new_node(int val, unsigned num);
mset add_val(int val, unsigned num, mset m);
mset build(int *values, size_t size);
