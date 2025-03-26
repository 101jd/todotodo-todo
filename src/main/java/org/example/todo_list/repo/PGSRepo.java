package org.example.todo_list.repo;

import jakarta.persistence.EntityGraph;
import org.example.todo_list.domain.Task;
import org.example.todo_list.exc.UndefinedEnumValueException;
import org.example.todo_list.exc.UndefinedEnumValueRTException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Primary
@Repository
public class PGSRepo implements Repos {
    @Autowired
    SessionFactory factory;

    public boolean addTask(Task task){
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(task);
        transaction.commit();
        session.close();
        return true;
    }

    public Task getBy(UUID id) throws UndefinedEnumValueException {
        Session session = factory.openSession();
        Task task;
        try {
            task = (Task) session.byId(id.toString()).loadOptional(Task.class).get();
        }catch (UndefinedEnumValueRTException e){
            throw new UndefinedEnumValueException();
        }

        session.close();
        return task;
    }

    public void edit(Task task){
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(task);
        transaction.commit();
        session.close();
    }

    public List<Task> getTasks(){
        Session session = factory.openSession();

        List<Task> list = session.createQuery("FROM Task").list();

        session.close();

        return list;
    }
}
