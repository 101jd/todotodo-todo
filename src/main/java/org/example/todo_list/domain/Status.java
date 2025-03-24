package org.example.todo_list.domain;

import lombok.Getter;
import org.example.todo_list.exc.UndefinedEnumValueRTException;

@Getter
public enum Status implements Comparable<Status> {
    COMPLETE(3), IN_PROGRESS(2), NOT_STARTED(1);

    private int value;
    private String name;

    Status(int value){
        this.value = value;
        this.name = this.name();
    }

    public static Status getByName(String name) throws UndefinedEnumValueRTException {
        switch (name){
            case "COMPLETE": return Status.COMPLETE;
            case "IN_PROGRESS": return Status.IN_PROGRESS;
            case "NOT_STARTED": return Status.NOT_STARTED;
            default: throw new UndefinedEnumValueRTException();
        }
    }

}
