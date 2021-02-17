package persistence;


import model.Task;
import model.ToDoList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads todolist from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads todolist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ToDoList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseToDoList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8);
        stream.forEach(s -> contentBuilder.append(s));

        return contentBuilder.toString();
    }

    // EFFECTS: parses todolist from JSON object and returns it
    private ToDoList parseToDoList(JSONObject jsonObject) {
        //String name = jsonObject.getString("name");
        ToDoList tol = new ToDoList();
        addCompletedTasks(tol, jsonObject);
        addIncompletedTasks(tol, jsonObject);
        return tol;
    }

    // MODIFIES: tol
    // EFFECTS: parses finished tasks from JSON object and adds them to todolist
    private void addCompletedTasks(ToDoList tol, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("finished tasks");
        for (Object json : jsonArray) {
            JSONObject nextTask = (JSONObject) json;
            addCompletedTask(tol, nextTask);
        }
    }

    // MODIFIES: tol
    // EFFECTS: parses finished task from JSON object and adds it to todolist
    private void addCompletedTask(ToDoList tol, JSONObject jsonObject) {
        String description = jsonObject.getString("description");
        String deadline = jsonObject.getString("deadline");
        int diffLevel = jsonObject.getInt("diffLevel");
        Task task = new Task(description, deadline, diffLevel);
        task.setStatus("finished");
        tol.addCompletedTask(task);
    }

    // MODIFIES: tol
    // EFFECTS: parses finished tasks from JSON object and adds them to todolist
    private void addIncompletedTasks(ToDoList tol, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("unfinished tasks");
        for (Object json : jsonArray) {
            JSONObject nextTask = (JSONObject) json;
            addIncompletedTask(tol, nextTask);
        }
    }

    // MODIFIES: tol
    // EFFECTS: parses finished task from JSON object and adds it to todolist
    private void addIncompletedTask(ToDoList tol, JSONObject jsonObject) {
        String description = jsonObject.getString("description");
        String deadline = jsonObject.getString("deadline");
        int diffLevel = jsonObject.getInt("diffLevel");
        Task task = new Task(description, deadline,diffLevel);
        tol.addIncompletedTask(task);
    }
}

