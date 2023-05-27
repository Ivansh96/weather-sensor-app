package ru.shavshin.weathersensor.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.shavshin.weathersensor.dal.entity.SensorEntity;
import ru.shavshin.weathersensor.service.SensorService;


@Component
@RequiredArgsConstructor
public class SensorValidator implements Validator {
    private final SensorService sensorService;

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
