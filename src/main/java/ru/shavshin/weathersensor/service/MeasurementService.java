package ru.shavshin.weathersensor.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.shavshin.weathersensor.dal.entity.MeasurementEntity;
import ru.shavshin.weathersensor.dal.repository.MeasurementRepository;
import ru.shavshin.weathersensor.dto.MeasurementDTO;
import ru.shavshin.weathersensor.exception.WrongMeasurementsException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final ModelMapper modelMapper;

    public List<MeasurementDTO> findAll() {
        return measurementRepository.findAll().stream()
                .map(this::toMeasurementDTO)
                .collect(Collectors.toList());
    }

    public MeasurementDTO save(MeasurementDTO measurementDTO) {
        return Optional.of(measurementDTO)
                .map(this::toMeasurementEntity)
                .map(measurementRepository::save)
                .map(this::toMeasurementDTO)
                .orElseThrow(WrongMeasurementsException::new);
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

    private MeasurementEntity toMeasurementEntity(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, MeasurementEntity.class);
    }

    private MeasurementDTO toMeasurementDTO(MeasurementEntity measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

}
