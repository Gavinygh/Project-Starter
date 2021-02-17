package model;

import org.json.JSONObject;
import persistence.Writable;

// represents a task object with description, deadline, status, and difficult level.
public class Task implements Writable {
    private String description;
    private String deadline;
    // this represent the status of the task. When status is true, the task is done.
    private String status;
    private int diffLevel;


    //Constructors:
    //create unfinished task
    public Task(String description, String deadline, int d) {
        this.description = description;
        this.deadline = deadline;
        status = "unfinished";
        diffLevel = d;
    }

    //create finished task
    public Task(String description, String deadline, String status, int d) {
        this.description = description;
        this.deadline = deadline;
        this.status = status;
        diffLevel = d;
    }


    //getters:

    // get the discription of the task
    public String getDescription() {
        return description;
    }

    //get the deadline of the task
    public String getDeadline() {
        return deadline;
    }

    //get the status of the status
    public String getStatus() {
        return status;
    }

    //get the difficult level of the task
    public int getDiffLevel() {
        return diffLevel;
    }

    //setters

    //set the discription of a task
    public void setDescription(String description) {
        this.description = description;
    }

    //set the deadline of the task
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    //set the status of the task
    public void setStatus(String status) {
        this.status = status;
    }

    //set the difficult level of the task
    public void setDiffLevel(int newLevel) {
        diffLevel = newLevel;
    }

    // EFFECTS: returns this as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("description", description);
        json.put("deadline", deadline);
        json.put("status", status);
        json.put("diffLevel", diffLevel);
        return json;
    }
}
