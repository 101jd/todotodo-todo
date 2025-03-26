package org.example.todo_list.repo;

import org.example.todo_list.domain.Priority;
import org.example.todo_list.domain.Status;
import org.example.todo_list.domain.Task;
import org.example.todo_list.exc.UndefinedEnumValueException;
import org.example.todo_list.exc.UndefinedEnumValueRTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class Repo implements Repos {

    @Autowired
    JdbcTemplate template;

    public boolean addTask(Task task){
        String query =
                "INSERT INTO todolist VALUES (?, ?, ?, ?)";
        try {
            template.update(query,
                    task.getId().toString(),
                    task.getName(),
                    task.getStatus(),
                    task.getPriority());

            return true;
        }catch (DataAccessException e){
            return false;
        }
    }

    public List<Task> getTasks() throws UndefinedEnumValueRTException {
        String query = "SELECT * FROM todolist";
        RowMapper<Task> mapper;

            mapper = (rs, rowNum) -> {
                Task task = new Task();

                    task.setId(UUID.fromString(rs.getString("id")));
                    task.setName(rs.getString("name"));
                    task.setStatus(Status.getByName(rs.getString("status")));
                    task.setPriority(Priority.getByName(rs.getString("priority")));


                return task;
            };


        return template.query(query, mapper);
    }

    public void edit(Task task){
        String query = "UPDATE todolist SET name = ?, status = ?, priority = ? WHERE id = ?";

        template.update(query, task.getName(), task.getStatus(), task.getPriority(), task.getId());
    }

    public Task getBy(UUID id) throws UndefinedEnumValueException {
        return getTasks().stream().filter(task -> task.getId().equals(id)).findFirst().orElse(null);
    }
}
