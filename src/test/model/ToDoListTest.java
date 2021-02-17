package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoListTest {
    ToDoList toDoList;

    @BeforeEach
    public void setup() {
        toDoList = new ToDoList();
    }

    @Test
    public void getNoTasksTest() {
        assertEquals(0, toDoList.getAllTasks().size());
    }

    @Test
    public void getAllTasksTest() {
        toDoList.addIncompletedTask(new Task("CPSC302 Ass4","2020.10.16",5));
        toDoList.addIncompletedTask(new Task("CPSC210 project","2020.10.14",5));
        toDoList.markCompleted(0);
        assertEquals(2,toDoList.getAllTasks().size());
    }

    @Test
    public void getNoDoneTaskTest() {
        assertEquals(0, toDoList.getDoneTasks().size());
    }

    @Test
    public void getDoneTasksTest() {
        toDoList.addIncompletedTask(new Task("CPSC210 project","2020.10.14",5));
        toDoList.markCompleted(0);
        assertEquals(1,toDoList.getDoneTasks().size());
    }

    @Test
    public void getZeroNotDoneTest() {
        assertEquals(0,toDoList.getNotDoneTasks().size());
    }

    @Test
    public void getNotDoneTest() {
        toDoList.addIncompletedTask(new Task("CPSC210 project","2020.10.14",5));
        assertEquals(1,toDoList.getNotDoneTasks().size());
    }

    @Test
    public void addNewTaskTest() {
        Task t = new Task("MATH307 HW1","2020.10.14",6);
        toDoList.addIncompletedTask(t);
        assertEquals(1,toDoList.getIncompletedNum());
    }

    @Test
    public void addCompletedTaskTest() {
        Task t = new Task("MATH307 HW1","2020.10.14","finished",6);
        toDoList.addCompletedTask(t);
        assertEquals(1,toDoList.getCompletedNum());
    }

    @Test
    public void deleteNotDoneTaskTest() {
        Task t = new Task("CPSC302 Ass4","2020.10.16",5);
        toDoList.addIncompletedTask(t);
        toDoList.deleteNotDoneTask(0);
        assertEquals(0,toDoList.getNotDoneTasks().size());
    }

    @Test
    public void deleteDoneTaskTest() {
        Task t = new Task("CPSC302 Ass4","2020.10.16",5);
        toDoList.addIncompletedTask(t);
        toDoList.markCompleted(0);
        toDoList.deleteDoneTask(0);
        assertEquals(0,toDoList.getDoneTasks().size());
    }

    @Test
    public void getIncompletedNumTest() {
        assertEquals(0,toDoList.getIncompletedNum());
    }

    @Test
    public void getIncompletedNumTest2() {
        toDoList.addIncompletedTask(new Task("CPSC302","2020.10.16",5));
        assertEquals(1,toDoList.getIncompletedNum());
    }

    @Test
    public void getCompletedNumTest() {
        toDoList.addIncompletedTask(new Task("CPSC302","2020.10.16",5));
        assertEquals(0,toDoList.getCompletedNum());
    }

    @Test
    public void getCompletedNumTest2() {
        toDoList.addIncompletedTask(new Task("CPSC302","2020.10.16",5));
        toDoList.markCompleted(0);
        assertEquals(1,toDoList.getCompletedNum());
    }

    @Test
    public void markCompletedTest() {
        toDoList.addIncompletedTask(new Task("CPSC302","2020.10.16",5));
        toDoList.markCompleted(0);
        assertEquals(0,toDoList.getIncompletedNum());
    }

    @Test
    public void toJsonTest() {
        ToDoList tol = new ToDoList();
        tol.addIncompletedTask(new Task("CPSC210","2020.10.16",6));
        tol.addCompletedTask(new Task("CPSC302","2020.10.16","finished",5));
        JSONObject json = tol.toJson();
        JSONArray jsonArray = json.getJSONArray("finished tasks");
        JSONArray jsonArray2 = json.getJSONArray("unfinished tasks");
        assertEquals("CPSC302",jsonArray.getJSONObject(0).getString("description"));
        assertEquals("2020.10.16",jsonArray.getJSONObject(0).getString("deadline"));
        assertEquals(5,jsonArray.getJSONObject(0).getInt("diffLevel"));
        assertEquals("finished",jsonArray.getJSONObject(0).getString("status"));

        assertEquals("CPSC210",jsonArray2.getJSONObject(0).getString("description"));
        assertEquals("2020.10.16",jsonArray2.getJSONObject(0).getString("deadline"));
        assertEquals(6,jsonArray2.getJSONObject(0).getInt("diffLevel"));
        assertEquals("unfinished",jsonArray2.getJSONObject(0).getString("status"));
    }
}
