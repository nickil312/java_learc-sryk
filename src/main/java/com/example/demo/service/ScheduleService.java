package com.example.demo.service;

import com.example.demo.model.ScheduleModel;

import java.util.List;

public interface ScheduleService {
    public List<ScheduleModel> getSchedule();
    public ScheduleModel getScheduleById(int id);
    public ScheduleModel createSchedule(ScheduleModel scheduleModel);
    public ScheduleModel updateSchedule(ScheduleModel scheduleModel);
    public void deleteSchedule(int id);
}
