package ru.shavshin.weathersensor.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.shavshin.weathersensor.dal.entity.MeasurementEntity;
import ru.shavshin.weathersensor.service.SensorService;

@Component
@RequiredArgsConstructor
public class MeasurementValidator implements Validator {
    private final SensorService sensorService;

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementEntity measurement = (MeasurementEntity) target;

        if (sensorService.findByName(measurement.getSensor().getName()).isEmpty()) {
            errors.rejectValue("sensor", "There is no such sensor in database! Please, add it, first!");
        }
    }
}
