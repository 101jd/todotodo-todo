package org.example.todo_list.domain;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
public class Task {
    private UUID id = UUID.randomUUID();
    private String name;
    private String status;
    private String priority;

    public void setStatus(Status status) {
        this.status = status.name();
    }

    public void setPriority(Priority priority) {
        this.priority = priority.name();
    }


}
