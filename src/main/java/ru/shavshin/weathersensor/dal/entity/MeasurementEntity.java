package ru.shavshin.weathersensor.dal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Entity
@Table(name = "Measurement")
@Getter
@Setter
@NoArgsConstructor
public class MeasurementEntity {

    @ManyToOne()
    @JoinColumn(name = "sensor", referencedColumnName = "sensor_name")
    private SensorEntity sensor;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "measurement_value")
    @Min(value = -100, message = "Value should not be lower than -100!")
    @Max(value = 100, message = "Value should not be greater than 100!")
    private Double value;
    @Column(name = "raining")
    private Boolean raining;
    @Column(name = "created_at")
    private LocalDateTime createdAt;



    public MeasurementEntity(Double value, Boolean raining, SensorEntity sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }



}
