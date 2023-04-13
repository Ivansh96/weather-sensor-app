package ru.shavshin.weathersensor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shavshin.weathersensor.dal.entity.SensorEntity;
import ru.shavshin.weathersensor.dal.repository.SensorRepository;


import java.util.Optional;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;
    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public void save(SensorEntity sensor) {
        sensorRepository.save(sensor);
    }

    public Optional<SensorEntity> findByName(String name) {
        return sensorRepository.findByName(name);
    }
}
