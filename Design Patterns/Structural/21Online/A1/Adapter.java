public class Adapter implements WeatherProvider{
    private LegacyWeatherService legacy;
    public Adapter(LegacyWeatherService legacy)
    {
        this.legacy = legacy;
    }
    @Override
    public String fetchWeather() {
        return legacy.getWeatherData();
    }    
}