package ru.shavshin.weathersensor.util.exception.measurement;

public class SensorNotFoundException extends RuntimeException {
    public SensorNotFoundException(String message) {
        super(message);
    }
}
