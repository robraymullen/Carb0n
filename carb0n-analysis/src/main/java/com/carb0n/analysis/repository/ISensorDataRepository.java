package com.carb0n.analysis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carb0n.analysis.message.model.SensorMessage;

@Repository
public interface ISensorDataRepository extends JpaRepository<SensorMessage, String>{

}
