# clue
How to compile and run on aviary
Add the following statements:
   	
	make
	java Clue

How to compile and run the unit tests
Add the following commands:

	javac -cp .:junit-platform-console-standalone-1.6.0.jar TestComputerGuess.java
	java -jar junit-platform-console-standalone-1.6.0.jar --class-path . --scan-class-path