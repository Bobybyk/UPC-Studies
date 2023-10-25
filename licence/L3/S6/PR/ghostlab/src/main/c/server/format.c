#include "format.h"

char *format_3digits(int x) {
	char *format = malloc(4);
	sprintf(format,"%03d",x);
	return format;
}

char *format_4digits(int x) {
	char *format = malloc(5);
	sprintf(format,"%04d",x);
	return format;
}

u_int16_t to_little_endian(u_int16_t value) {
	u_int16_t uint16_value = htons(value);

	if (uint16_value == value) {
		return (u_int16_t)((value >> 8)) | (value << 8);
	}

	return value;
}

