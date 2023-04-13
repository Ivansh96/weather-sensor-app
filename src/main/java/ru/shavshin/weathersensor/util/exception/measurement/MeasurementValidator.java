package ru.shavshin.weathersensor.util.exception.measurement;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.shavshin.weathersensor.dal.entity.MeasurementEntity;
import ru.shavshin.weathersensor.service.SensorService;

@Component
public class MeasurementValidator implements Validator {

    private final SensorService sensorService;

    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementEntity measurement = (MeasurementEntity) target;

        if(measurement.getSensor() == null)
            return;

        if(sensorService.findByName(measurement.getSensor().getName()).isEmpty())
            errors.rejectValue("sensor", "There is no such sensor in database! Please, add it, first!");
    }
}
