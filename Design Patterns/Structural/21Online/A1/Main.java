public class Main {
    public static void main(String[] args) {
// Legacy service instance
    LegacyWeatherService legacyWeatherService = new LegacyWeatherService();
    WeatherProvider pro = new Adapter(legacyWeatherService);
    WeatherApp app = new WeatherApp(pro);
    app.displayWeather(); // Output: Legacy weather data
}
}
