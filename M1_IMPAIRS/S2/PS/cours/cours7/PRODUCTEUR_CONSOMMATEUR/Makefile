CC=gcc
CFLAGS=-Wall -g -std=c11 -pedantic
LDLIBS=-lrt -pthread  #linux



ALL =   producer consumer

all : $(ALL)

open_memory.o : open_memory.c open_memory.h
prefix_slash.o: prefix_slash.c prefix_slash.h
init_mutex.o: init_mutex.c init_mutex.h

thread_error.o : thread_error.c thread_error.h

producer : producer.c thread_error.o prefix_slash.o open_memory.o init_mutex.o
consumer : consumer.c thread_error.o prefix_slash.o open_memory.o init_mutex.o

clean:
	rm -rf *~
cleanall:
	rm -rf *~ $(ALL) *.o
