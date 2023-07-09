package com.activitytracker.dtos.taskdto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CreateTaskDto {
    private String title;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime completedAt;
}
