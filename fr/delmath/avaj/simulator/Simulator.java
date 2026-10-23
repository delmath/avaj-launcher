package fr.delmath.avaj.simulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.delmath.avaj.simulator.exception.InvalidFileException;
import fr.delmath.avaj.simulator.utils.AircraftFactory;
import fr.delmath.avaj.simulator.utils.Flyable;

public class Simulator {
    private static FileWriter       writer;
    private static WeatherTower     weatherTower;
    private static List<Flyable>    aircrafts;
    private static int              simulations;

    public static void writeLog(String message) {
        try {
            writer.write(message + "\n");
        } catch (IOException e) {
            System.err.println("Error writing to file.");
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java fr.delmath.avaj.simulator.Simulator <scenario.txt>");
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
            System.err.println("Error during simulation: " + e.getMessage());
        }
    }

    private static void parseInput(BufferedReader reader) throws InvalidFileException, IOException {
        String line = reader.readLine();

        if (line == null) {
            reader.close();
            writer.close();
            throw new InvalidFileException("Error: Empty file.");
        }

        try {
            simulations = Integer.parseInt(line.trim());
        } catch (NumberFormatException e) {
            reader.close();
            writer.close();
            throw new InvalidFileException("Error: Invalid number of simulations on the first line.");
        }

        weatherTower = new WeatherTower();
        aircrafts = new ArrayList<>();
        int i = 1;

        while ((line = reader.readLine()) != null) {
            i++;
            if (line.trim().isEmpty())
                continue;

            String[] parts = line.split("\\s+");
            if (parts.length == 5) {
                String type = parts[0];
                String name = parts[1];
                int lon = Integer.parseInt(parts[2]);
                int lat = Integer.parseInt(parts[3]);
                int height = Integer.parseInt(parts[4]);
                Coordinates coords = new Coordinates(lon, lat, height);

                Flyable aircraft = AircraftFactory.getFactory().newAircraft(type, name, coords);
                if (aircraft != null) {
                    aircrafts.add(aircraft);
                    aircraft.registerTower(weatherTower);
                }
            } else {
                System.err.println("Error: L" + i + " invalid line continue");
            }
        }
        reader.close();
    }
}
