package ru.shavshin.weathersensor.rest;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.shavshin.weathersensor.dal.entity.MeasurementEntity;
import ru.shavshin.weathersensor.model.dto.MeasurementDTO;
import ru.shavshin.weathersensor.model.dto.MeasurementResponse;
import ru.shavshin.weathersensor.service.MeasurementService;
import ru.shavshin.weathersensor.util.exception.measurement.MeasurementValidator;


import javax.validation.Valid;
import java.util.stream.Collectors;

import static ru.shavshin.weathersensor.util.exception.ErrorsUtil.returnErrorsToClient;


@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementController(MeasurementService measurementService, MeasurementValidator measurementValidator, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
        this.modelMapper = modelMapper;
    }


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {
        MeasurementEntity measurementToAdd = convertToMeasurement(measurementDTO);
        measurementValidator.validate(measurementToAdd, bindingResult);

        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        measurementService.save(measurementToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }



    @GetMapping()
    public MeasurementResponse getMeasurements() {
        return new MeasurementResponse(measurementService.findAll().stream().map(this::convertToMeasurementDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/rainyDaysCount")
    public int getRainyDays() {
        return measurementService.getRainyDays();
    }

    public MeasurementEntity convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, MeasurementEntity.class);
    }

    public MeasurementDTO convertToMeasurementDTO(MeasurementEntity measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

}
