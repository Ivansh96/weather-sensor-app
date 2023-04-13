package ru.shavshin.weathersensor.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shavshin.weathersensor.dal.entity.MeasurementEntity;

@Repository
public interface MeasurementRepository extends JpaRepository<MeasurementEntity, Integer> {
}
