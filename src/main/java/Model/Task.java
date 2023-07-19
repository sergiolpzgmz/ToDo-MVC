package Model;

import java.util.Date;

public class Task {
    private enum priority{HIGH,MID,LOW}
    private String name;
    private String description;
    private Date deadline;
    private String priority;


    public Task() {
    }

    public Task(String name, String description, Date deadline, String priority) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
