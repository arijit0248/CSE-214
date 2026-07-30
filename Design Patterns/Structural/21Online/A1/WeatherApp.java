public class WeatherApp {
    private WeatherProvider weatherProvider;
    public WeatherApp(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }
    public void displayWeather() {
        System.out.println(weatherProvider.fetchWeather());
    }
}
