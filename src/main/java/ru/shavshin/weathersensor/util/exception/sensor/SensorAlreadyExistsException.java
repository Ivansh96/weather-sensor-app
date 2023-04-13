package ru.shavshin.weathersensor.util.exception.sensor;

public class SensorAlreadyExistsException extends RuntimeException {

    public SensorAlreadyExistsException (String message) {
        super(message);
    }
}
