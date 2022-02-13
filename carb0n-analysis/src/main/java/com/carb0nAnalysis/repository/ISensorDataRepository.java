package com.carb0nAnalysis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carb0nAnalysis.model.SensorData;

public interface ISensorDataRepository extends JpaRepository<SensorData, String> {

}
