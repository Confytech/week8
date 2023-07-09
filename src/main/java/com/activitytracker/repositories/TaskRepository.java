package com.activitytracker.repositories;

import com.activitytracker.entities.Task;
import com.activitytracker.entities.User;
import com.activitytracker.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTasksByUser(User user);
    Task findTaskByUserAndId(User user, Long taskId);
    List<Task> findTasksByUserAndStatus(User user, Status status);
    //Task findTaskByUserAndIdAndStatus(User user, Long id, Status status);
}
