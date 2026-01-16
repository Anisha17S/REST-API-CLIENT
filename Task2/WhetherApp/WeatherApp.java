import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherApp {

    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter city name: ");
            String city = sc.nextLine();

            // Put your OpenWeather API Key here
            String apiKey = "747496548b9dd858b7bdbb1019903f98";

            String urlString =
                    "https://api.openweathermap.org/data/2.5/weather?q="
                    + city + "&appid=" + apiKey + "&units=metric";

            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            String response = "";

            while ((line = reader.readLine()) != null) {
                response = response + line;
            }

            reader.close();

            // -------- SIMPLE JSON PARSING (without library) --------

            String temp = response.split("\"temp\":")[1].split(",")[0];
            String humidity = response.split("\"humidity\":")[1].split("}")[0];
            String weather = response.split("\"description\":\"")[1].split("\"")[0];

            System.out.println("\n--- Weather Report ---");
            System.out.println("City: " + city);
            System.out.println("Temperature: " + temp + " °C");
            System.out.println("Humidity: " + humidity + "%");
            System.out.println("Condition: " + weather);

        } catch (Exception e) {
            System.out.println("Something went wrong!");
        }
    }
}
