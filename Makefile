run: compile
	javac SubwayFrontEnd.java
	java SubwayFrontEnd
	make clean

compile: 
	javac Station.java
	javac StationLine.java
	javac Connection.java
	javac LoadLondonSubwaySystem.java
	javac GraphADT.java
	javac Graph.java
	javac SubwayADT.java
	javac SubwayBackEnd.java
	
test: compile
	javac -cp .:junit5.jar LondonSubwayTester.java
	java -jar junit5.jar --class-path . --scan-classpath --details tree --include-classname '.*'
	make clean

clean:
	$(RM) *.class
