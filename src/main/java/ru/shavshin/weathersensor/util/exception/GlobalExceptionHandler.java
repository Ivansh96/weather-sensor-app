package ru.shavshin.weathersensor.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.shavshin.weathersensor.util.exception.measurement.SensorNotFoundException;
import ru.shavshin.weathersensor.util.exception.sensor.SensorAlreadyExistsException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(SensorNotFoundException.class)
    public ResponseEntity<ErrorResponse> SensorNotFoundException (SensorNotFoundException e) {
       ErrorResponse response = new ErrorResponse(e.getMessage(), System.currentTimeMillis());
       return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(SensorAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> SensorAlreadyExistsException (SensorAlreadyExistsException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
