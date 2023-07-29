.PHONY: mvnme\
runme\
mvn_native\
run_native\

mvnme: 
	mvn clean package -Dmaven.test.skip=true

jarpath:='./target/spring-boot3-native.jar'
runme: mvnme
	java -Dfile.encoding=utf-8 -jar $(jarpath) "-Xms64m -Xmx64m -Djava.security.egd=file:/dev/./urandom"

mvn_native: 
	mvn clean package -Pnative
	mvn native:compile-no-fork 

run_native:
	./target/spring-boot3-native