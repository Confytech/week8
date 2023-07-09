package com.activitytracker.services;

import com.activitytracker.dtos.taskdto.CreateTaskDto;
import com.activitytracker.entities.ApiResponse;
import com.activitytracker.entities.Task;
import com.activitytracker.entities.User;
import com.activitytracker.enums.Status;
import com.activitytracker.exceptions.NotFoundException;
import com.activitytracker.exceptions.NotNullException;


import java.util.List;

public interface TaskService {
    User findLoggedInUser();

    Task getTaskById(Long taskId);

    ApiResponse<Task> createTask(CreateTaskDto createTaskDto) throws NotNullException;
    ApiResponse<List<Task>> getAllUserTasks();
    ApiResponse<Task> viewTaskById(Long taskId) throws NotFoundException;
    ApiResponse<List<Task>> getTaskByStatus(Status status) throws NotFoundException;

    ApiResponse<Task> updateTaskStatus(Long taskId, Task newTask);

    ApiResponse<Task> updateTaskTitleOrDescription(Long taskId, Task newTask) throws NotFoundException;
    void deleteTask(Long taskId);
}
