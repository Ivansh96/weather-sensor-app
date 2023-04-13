package ru.shavshin.weathersensor.rest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.shavshin.weathersensor.dal.entity.SensorEntity;
import ru.shavshin.weathersensor.model.dto.SensorDTO;
import ru.shavshin.weathersensor.service.SensorService;

import ru.shavshin.weathersensor.util.exception.sensor.SensorValidator;

import javax.validation.Valid;

import static ru.shavshin.weathersensor.util.exception.ErrorsUtil.returnErrorsToClient;


@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final SensorValidator sensorValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorController(SensorService sensorService, SensorValidator sensorValidator, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        SensorEntity sensorToAdd = convertToSensor(sensorDTO);

        sensorValidator.validate(sensorToAdd, bindingResult);

        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        sensorService.save(sensorToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    private SensorEntity convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, SensorEntity.class);
    }
}
