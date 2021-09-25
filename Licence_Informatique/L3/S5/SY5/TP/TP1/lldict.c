#include <stdio.h>
#include <stdlib.h>
#include "lldict.h"

void lldshw(lldict* const dic) {
	lldict *current = dic;
	while(current != NULL) {
		printf("%d - %d\n",current->llkey,current->llval);
		current = current->llnxt;
	}
	printf("\n");
}

lldict *lldnew(int key, int val) {
	lldict *new = malloc(sizeof(lldict));
	new->llkey = key;
	new->llval = val;
	new->llnxt = NULL;
	return new;
}	

lldict *lldadd(lldict *dic, int key, int val) {
	lldict *current = dic;
	lldict *new = lldnew(key,val);
	/*
	 * 5 distinct cases:
	 * 	- the dict is NULL (1)
	 * 	- the element must be added at the start (2)
	 * 	- the element must be added at the end (3)
	 * 	- the element must be added between two elements (4)
	 * 	- the element cannot be added because the key is already present
	 */

	if(dic == NULL)		//(1)
		return new;
	
	while(current->llnxt != NULL) {		//this loop sets the element "current" to the element before the place to add an element
		if(current->llnxt->llkey > key)
			break;
		current = current->llnxt;
	}

	if(current->llkey == key) {
		printf("Cannot add element: key already present\n");
		return dic;
	}
	//printf("current key: %d\nkey to add: %d\n",current->llkey,key);

	if(current == dic && current->llkey > key) {		//(2)
	//	printf("case 2\n");
		new->llnxt = dic;
		return new;
	} else if(current->llnxt == NULL) {	//(3)
	//	printf("case 3\n");
		current->llnxt = new;
	} else {				//(4)
	//	printf("case 4\n");
		new->llnxt = current->llnxt;
		current->llnxt = new;
	}
	return dic;
}

lldict *lldmod(lldict *dic,int key, int val) {
	lldict *current = dic;

	while(current->llnxt != NULL) {
		if(current->llkey == key)
			break;
		current = current->llnxt;
	}

	if(current != NULL)
		current->llval = val;
	else
		printf("Cette clé n'existe pas\n");

	return dic;
}	

lldict* llddel(lldict* dic, int key) {
	lldict *current = dic;

	/*
	 * 4 cases:
	 * 	- element in first place (1)
	 * 	- element last place (2)
	 * 	- element between two elements (3)
	 * 	- element des not exist (4)
	 */

	if(dic->llkey == key) { 		//(1)
		current = current->llnxt;
		free(dic);
		return current;
	}

	while(current->llnxt != NULL) {
		if(current->llnxt->llkey == key)
			break;
		current = current->llnxt;
	}

	if(current->llnxt == NULL)		//(4)
		printf("Cette clé n'existe pas\n");
	else {					//(3)
		lldict *toFree = current->llnxt;
		current->llnxt = current->llnxt->llnxt;
		free(toFree);
	}
	return dic;
}	

int lldlkp(lldict* const dic, int def, int key) {
	
	lldict *current = dic;
	while(current->llnxt != NULL)
		current = current->llnxt;

	if(current != NULL)
		return def;
	return current->llval;
}
