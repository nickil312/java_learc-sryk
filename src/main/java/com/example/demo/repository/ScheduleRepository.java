package com.example.demo.repository;

import com.example.demo.model.ScheduleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleModel,Integer> {
}
