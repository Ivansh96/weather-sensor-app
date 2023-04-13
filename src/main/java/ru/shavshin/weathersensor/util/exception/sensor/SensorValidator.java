package ru.shavshin.weathersensor.util.exception.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.shavshin.weathersensor.dal.entity.SensorEntity;
import ru.shavshin.weathersensor.service.SensorService;


@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorEntity sensor = (SensorEntity) target;
        if (sensorService.findByName(sensor.getName()).isPresent())
            errors.rejectValue("name", "This sensor is in database already!");
    }
}
