include "ldict.h";

void lldshw(lldict* const dic) {
	while (dic != NULL) {
		printf("%s\n", dic->llkey);
		printf(" : ");
		printf("%s\n", dic->llval);
		dic = dic->llnxt;
	}
}