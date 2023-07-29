.PHONY: mvnme\
runme\

mvnme: 
	mvn clean package -Dmaven.test.skip=true

jarpath:='./target/excel-poi.jar'
runme: mvnme
	java -Dfile.encoding=utf-8 -jar $(jarpath) "-Xms64m -Xmx64m -Djava.security.egd=file:/dev/./urandom"