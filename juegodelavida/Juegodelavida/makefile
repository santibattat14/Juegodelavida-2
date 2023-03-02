

limpiar: 
	rm -rf bin
	rm -rf *.class
	rm -rf *.jar
	rm -rf html


compilar:limpiar
	mkdir bin
	find src -name "*.java" | xargs javac -cp bin -d bin

jar:compilar
	jar cvfm tablero.jar manifest.txt -C bin .

html:jar
	mkdir html
	find src -type f -name "*.java" | xargs javadoc -d html -encoding utf-8 -docencoding utf-8 -charset utf-8

jugar:html
	java -jar tablero.jar