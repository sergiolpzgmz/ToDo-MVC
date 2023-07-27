package Model;

import java.util.Date;

public class Task {
    private int id;
    private String name;
    private String description;
    private Date deadline;
    private String priority;
    private boolean isFinished;


    public Task() {
    }

    public Task(String name, String description, Date deadline, String priority, boolean isFinished) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.isFinished = isFinished;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isFinished() { return isFinished; }

    public void setFinished(boolean finished) { isFinished = finished; }
}
