int strlen(char *ptr) {
	
	int result = 0;
	
	while(*ptr != 0) {
		result++;
		ptr++;
	}
	
	return result;

}
