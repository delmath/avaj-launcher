package ro.academyplus.avaj.simulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Simulator {
	private static FileWriter		writer;
	private static WeatherTower		weatherTower;
	private static List<Flyable>	aircrafts;
	private static int				simulations;

	public static void writeLog(String message) {
		try {
			writer.write(message + "\n");
		} catch (IOException e) {
			System.out.println("Error writing to file.");
		}
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java ro.academyplus.avaj.simulator.Simulator <scenario.txt>");
			return;
		}

		try {
			writer = new FileWriter("simulation.txt");
			BufferedReader reader = new BufferedReader(new FileReader(args[0]));
			parseInput(reader);
			for (int i = 0; i < simulations; i++)
				weatherTower.changeWeather();
			writer.close();
			System.out.println("Simulation completed. Check simulation.txt");
		} catch (InvalidFileException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Error during simulation: " + e.getMessage());
		}
	}

	private static void parseInput(BufferedReader reader) throws InvalidFileException, IOException {
		String line = reader.readLine();

		if (line == null) {
			reader.close();
			writer.close();
			throw new InvalidFileException("Error: Empty file.");
		}

		simulations = Integer.parseInt(line.trim());
		weatherTower = new WeatherTower();
		aircrafts = new ArrayList<>();

		while ((line = reader.readLine()) != null) {
			if (line.trim().isEmpty()) continue;
			String[] parts = line.split("\\s+");
			if (parts.length == 5) {
				String type = parts[0];
				String name = parts[1];
				int lon = Integer.parseInt(parts[2]);
				int lat = Integer.parseInt(parts[3]);
				int height = Integer.parseInt(parts[4]);

				Flyable aircraft = AircraftFactory.newAircraft(type, name, lon, lat, height);
				if (aircraft != null) {
					aircrafts.add(aircraft);
					aircraft.registerTower(weatherTower);
				}
			}
		}
		reader.close();
	}
}
