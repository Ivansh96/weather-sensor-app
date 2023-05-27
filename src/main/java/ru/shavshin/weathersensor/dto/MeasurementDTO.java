package ru.shavshin.weathersensor.dto;

import lombok.Data;
import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Value
public class MeasurementDTO {

    @Min(value = -100, message = "Value should not be lower than -100!")
    @Max(value = 100, message = "Value should not be greater than 100!")
    Double value;
    Boolean raining;
    SensorDTO sensor;
}
