package ru.shavshin.weathersensor.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.shavshin.weathersensor.dto.MeasurementDTO;
import ru.shavshin.weathersensor.dto.MeasurementResponse;
import ru.shavshin.weathersensor.service.MeasurementService;
import ru.shavshin.weathersensor.validation.MeasurementValidator;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/measurements")
@RequiredArgsConstructor
public class MeasurementController {

    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {
        measurementValidator.validate(measurementDTO, bindingResult);

        measurementService.save(measurementDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/get")
    public MeasurementResponse getMeasurements() {
        return new MeasurementResponse(measurementService.findAll());
    }

    @GetMapping("/rainyDaysCount")
    public int getRainyDays() {
        return measurementService.getRainyDays();
    }
}
