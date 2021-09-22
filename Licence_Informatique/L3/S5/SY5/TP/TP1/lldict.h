typedef struct lldict lldict;

struct lldict {
	int llkey;
	int llval;
	lldict* llnxt;
};

void lldshw(lldict* const dic);
lldict* lldadd(lldict* dic, int key, int val);
lldict* lldmod(lldict* dic, int key, int val);
lldict* llddel(lldict* dic, int key);
int lldlkp(lldict* const dic, int def, int key);