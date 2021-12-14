#if no recipe is specified, this one will apply
default: clearT run

#the build and run recipes for the whole project
build:
	./gradlew build
run: build
	./gradlew run


#the build and run recipes for the client side of the project only
client: clearT buildClient
	./gradlew :client:run --console=plain
buildClient: clearT
	./gradlew :client:build
runClient: clearT 
	./gradlew :client:run --console=plain
runClientCfg: clearT 
	./gradlew :client:run --console=plain --args="src/main/java/client/customConfig.json"
clientCustom: clearT
	./gradlew :client:run --console=plain --args="$(MAKECMDGOALS)"

#the build and run recipes for the server side of the project only
server: clearT buildServer
	./gradlew :server:run --console=plain
buildServer: clearT
	./gradlew :server:build
runServer: clearT
	./gradlew :server:run --console=plain

#convenience recipes
clearT:
	clear
clean:
	rm -r build/classes