package ru.shavshin.weathersensor.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shavshin.weathersensor.dal.entity.SensorEntity;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<SensorEntity, Integer> {
      Optional<SensorEntity> findByName(String name);
}
