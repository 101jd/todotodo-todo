package org.example.todo_list.domain;

import lombok.Getter;
import org.example.todo_list.exc.UndefinedEnumValueRTException;

@Getter
public enum Priority implements Comparable<Priority> {
    HIGH(3), MEDIUM(2), LOW(1);

    private int value;
    private String name;

    Priority(int value){
        this.value = value;
        this.name = this.name();
    }

    public static Priority getByName(String name) throws UndefinedEnumValueRTException {
        switch (name){
            case "HIGH": return Priority.HIGH;
            case "MEDIUM": return Priority.MEDIUM;
            case "LOW": return Priority.LOW;
            default: throw new UndefinedEnumValueRTException();
        }
    }

}
