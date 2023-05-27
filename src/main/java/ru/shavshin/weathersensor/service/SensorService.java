package ru.shavshin.weathersensor.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.shavshin.weathersensor.dal.entity.SensorEntity;
import ru.shavshin.weathersensor.dal.repository.SensorRepository;
import ru.shavshin.weathersensor.dto.SensorDTO;
import ru.shavshin.weathersensor.exception.SensorAlreadyExistsException;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SensorService {
    private final SensorRepository sensorRepository;
    private final ModelMapper modelMapper;

    public SensorDTO save(SensorDTO sensorDTO) {
        return Optional.of(sensorDTO)
                .map(this::toSensorEntity)
                .map(sensorRepository::save)
                .map(this::toSensorDTO)
                .orElseThrow(SensorAlreadyExistsException::new);
    }

    public Optional<SensorEntity> findByName(String name) {
        return sensorRepository.findByName(name);
    }

    private SensorEntity toSensorEntity(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, SensorEntity.class);
    }

    private SensorDTO toSensorDTO(SensorEntity sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }
}
