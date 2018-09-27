package vsushko.patterns.behavioral.observer;

/**
 * Observer interface.
 */
public interface WeatherObserver {
    void update(WeatherType currentWeather);
}
