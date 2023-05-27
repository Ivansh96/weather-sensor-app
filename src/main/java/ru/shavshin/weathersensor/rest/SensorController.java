package ru.shavshin.weathersensor.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.shavshin.weathersensor.dto.SensorDTO;
import ru.shavshin.weathersensor.service.SensorService;

import ru.shavshin.weathersensor.validation.SensorValidator;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/sensor")
@RequiredArgsConstructor
public class SensorController {
    private final SensorService sensorService;
    private final SensorValidator sensorValidator;


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {

        sensorValidator.validate(sensorDTO, bindingResult);

        sensorService.save(sensorDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
