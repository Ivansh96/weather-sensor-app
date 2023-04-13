package ru.shavshin.weathersensor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shavshin.weathersensor.dal.entity.MeasurementEntity;
import ru.shavshin.weathersensor.dal.repository.MeasurementRepository;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;
    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    public List<MeasurementEntity> findAll() {
        return measurementRepository.findAll();
    }

    public void save(MeasurementEntity measurement) {
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    public int getRainyDays() {
        List<MeasurementEntity> measurements = measurementRepository.findAll();
        int count = 0;
        for (MeasurementEntity m : measurements) {
            if (m.getRaining()) {
                count++;
            }
        }
        return count;
    }

    public void enrichMeasurement(MeasurementEntity measurement) {
        measurement.setSensor(sensorService.findByName(measurement.getSensor().getName()).get());
        measurement.setCreatedAt(LocalDateTime.now());
    }
}
