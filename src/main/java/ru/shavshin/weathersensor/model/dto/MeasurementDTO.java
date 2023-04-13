package ru.shavshin.weathersensor.model.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
public class MeasurementDTO {

    private SensorDTO sensor;

    @Min(value = -100, message = "Value should not be lower than -100!")
    @Max(value = 100, message = "Value should not be greater than 100!")
    private Double value;

    private Boolean raining;




}
