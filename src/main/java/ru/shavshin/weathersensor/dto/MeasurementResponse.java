package ru.shavshin.weathersensor.dto;

import java.util.List;

public record MeasurementResponse(
        List<MeasurementDTO> measurementDTOList) {
}
