LC3AS?=../lc3as
TARGETS=$(subst .asm,.obj,$(wildcard *.asm))

.PHONY: all clean

all: $(TARGETS)

clean:
	rm -f *.{obj,sym}

%.obj %.sym: %.asm
	$(LC3AS) $<
