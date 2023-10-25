.PHONY: init tarexamples

init: work tarexamples

work: templates
	cp -r templates work
	rm -f work/*.tar.broken

tarexamples :
	bash tarexamples.sh
