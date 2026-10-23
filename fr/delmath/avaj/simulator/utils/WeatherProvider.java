package fr.delmath.avaj.simulator.utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import fr.delmath.avaj.simulator.Coordinates;

public class WeatherProvider {
    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() {}

    public static WeatherProvider getProvider() {
        return (weatherProvider);
    }

    public String getCurrentWeather(Coordinates p_coordinates) {
        int longitude = p_coordinates.getLongitude() % 360;
        int latitude = Math.max(-90, Math.min(90, p_coordinates.getLatitude()));
        int height = Math.max(0, Math.min(100, p_coordinates.getHeight()));

        if (longitude > 180)
            longitude -= 360;
        else if (longitude < -180)
            longitude += 360;

        int realAltitudeInMeters = (height * 1000) / 100;

        try {
            HttpClient client = HttpClient.newHttpClient();

            String url = String.format(
                "https://api.open-meteo.com/v1/forecast?latitude=%d&longitude=%d&elevation=%d&current=temperature_2m,relative_humidity_2m",
                latitude, longitude, realAltitudeInMeters
            );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String body = response.body();

                double temperature = extractJsonValue(body, "\"temperature_2m\":");
                double humidity = extractJsonValue(body, "\"relative_humidity_2m\":");

                if (temperature <= 0)
                    return "SNOW";
                else if (humidity > 85)
                    return "FOG";
                else if (humidity > 70)
                    return "RAIN";
                else
                    return "SUN";
            }

        } catch (Exception e) {
            System.out.println("Erreur API, bascule sur l'algorithme local.");
        }

        int seed = longitude + latitude + height;
        return weather[Math.abs(seed) % 4];
    }

    private double extractJsonValue(String json, String key) {
        try {
            int currentIndex = json.indexOf("\"current\":");
            int index = json.indexOf(key, currentIndex);

            if (index == -1)
                return 0.0;

            int startIndex = index + key.length();
            int endIndex = json.indexOf(",", startIndex);
            if (endIndex == -1)
                endIndex = json.indexOf("}", startIndex);

            String valStr = json.substring(startIndex, endIndex);
            return (Double.parseDouble(valStr));
        } catch (Exception e) {
            return (0.0);
        }
    }
}
