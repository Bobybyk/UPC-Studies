.PHONY: all clean

all:
	dune build tree.exe
	ln -sf _build/default/tree.exe

clean:
	dune clean && rm -fr *~ tree.exe
