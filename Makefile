all:
	find . -name "*.java" > sources.txt
	javac @sources.txt

run: all
	java ro.academyplus.avaj.simulator.Simulator scenario.txt

clean:
	rm -f sources.txt
	find . -name "*.class" -delete

fclean: clean
	rm -f simulation.txt

re: fclean all
