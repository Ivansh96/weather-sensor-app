package ru.shavshin.weathersensor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.shavshin.weathersensor.util.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(SensorNotFoundException.class)
    public ResponseEntity<ErrorResponse> sensorNotFoundException(SensorNotFoundException e) {
        ErrorResponse response = new ErrorResponse("Sensor not found!", System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SensorAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> sensorAlreadyExistsException(SensorAlreadyExistsException e) {
        ErrorResponse response = new ErrorResponse("This sensor is already exists!", System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongMeasurementsException.class)
    public ResponseEntity<ErrorResponse> wrongMeasurementsException(WrongMeasurementsException e) {
        ErrorResponse response = new ErrorResponse("Measurement's data error!", System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
