package org.example.todo_list.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "tasks")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Column(name = "id")
    @Id
    private UUID id = UUID.randomUUID();
    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private String status;
    @Column(name = "priority")
    private String priority;

    public void setStatus(Status status) {
        this.status = status.name();
    }

    public void setPriority(Priority priority) {
        this.priority = priority.name();
    }


}
