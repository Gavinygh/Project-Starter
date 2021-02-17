package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//A todolist that contains finished tasks and unfinished tasks
public class ToDoList implements Writable {
    private List<Task> done;
    private List<Task> notDone;

    public ToDoList() {
        done = new ArrayList<>();
        notDone = new ArrayList<>();
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: return a list that contains all the tasks
    public List<Task> getAllTasks() {
        List<Task> allTasks = new ArrayList<>();
        for (Task i:notDone) {
            allTasks.add(i);
        }
        for (Task i:done) {
            allTasks.add(i);
        }
        return allTasks;
    }

    //EFFECTS:return a list that contains all the finished tasks
    public List<Task> getDoneTasks() {
        return done;
    }

    //EFFECTS:return a list that contains all the unfinished tasks
    public List<Task> getNotDoneTasks() {
        return notDone;
    }

    //REQUIRES:
    //MODIFIES:this
    //EFFECTS:add the new task into the notDone list
    public void addIncompletedTask(Task newTask) {
        notDone.add(newTask);
    }

    //REQUIRES:The task status has to be "finished"
    //MODIFIES:this
    //EFFECTS:add the finished task into the done list
    public void addCompletedTask(Task oddTask) {
        done.add(oddTask);
    }

    //REQUIRES: the task needs to be deleted is in the done list.
    //MODIFIES:this
    //EFFECTS:delete the task with index i in the done list
    public void deleteDoneTask(int i) {
        done.remove(i);
    }

    //REQUIRES:the task needs to be deleted is in the notDone list.
    //MODIFIES:this
    //EFFECTS: delete the task with index i in the notDone list
    public void deleteNotDoneTask(int i) {
        notDone.remove(i);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:return the number of incompleted tasks
    public int getIncompletedNum() {
        return notDone.size();
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:return the number of completed tasks
    public int getCompletedNum() {
        return done.size();
    }

    //REQUIRES:the task needs to be in the notDone list
    //MODIFIES: this
    //EFFECTS:mark the task with position i in the notDone completed, and move it to the done list
    public void markCompleted(int i) {
        notDone.get(i).setStatus("finished");
        done.add(notDone.get(i));
        notDone.remove(i);
    }

    // EFFECTS: returns this as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("finished tasks", doneTasksToJson());
        json.put("unfinished tasks", notDoneTasksToJson());
        return json;
    }

    // EFFECTS: returns tasks in done list as a JSON array
    private JSONArray doneTasksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Task t : done) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns tasks in notdone list as a JSON array
    private JSONArray notDoneTasksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Task t : notDone) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }
}

