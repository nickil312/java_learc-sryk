package com.example.demo.service;

import com.example.demo.model.ScheduleModel;
import com.example.demo.repository.ScheduleRepository;
import com.example.demo.repository.StudentProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    public final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }


    @Override
    public List<ScheduleModel> getSchedule() {
        return scheduleRepository.findAll();
    }

    @Override
    public ScheduleModel getScheduleById(int id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    @Override
    public ScheduleModel createSchedule(ScheduleModel scheduleModel) {
        return scheduleRepository.save(scheduleModel);
    }

    @Override
    public ScheduleModel updateSchedule(ScheduleModel scheduleModel) {
        return scheduleRepository.save(scheduleModel);
    }

    @Override
    public void deleteSchedule(int id) {
        scheduleRepository.deleteById(id);
    }
}
