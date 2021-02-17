package model;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void getDescriptionTest() {
        Task t = new Task("CPSC302","2020.10.16",5);
        assertEquals("CPSC302",t.getDescription());
    }

    @Test
    public void setDescriptionTest() {
        Task t = new Task("CPSC302","2020.10.16",5);
        t.setDescription("CPSC302 Ass5");
        assertEquals("CPSC302 Ass5",t.getDescription());
    }

    @Test
    public void getDeadlineTest() {
        Task t = new Task("CPSC302","2020.10.16",5);
        assertEquals("2020.10.16",t.getDeadline());
    }

    @Test
    public void setDeadlineTest() {
        Task t = new Task("CPSC302","2020.10.16",5);
        t.setDeadline("2020.10.26");
        assertEquals("2020.10.26",t.getDeadline());
    }

    @Test
    public void getStatusTest() {
        Task t = new Task("CPSC302","2020.10.16",5);
        assertEquals("unfinished",t.getStatus());
    }

    @Test
    public void setStatusTest() {
        Task t = new Task("CPSC302","2020.10.16",5);
        t.setStatus("finished");
        assertEquals("finished",t.getStatus());
    }

    @Test
    public void getDifficultLevelTest() {
        Task t = new Task("CPSC302","2020.10.16",5);
        assertEquals(5,t.getDiffLevel());
    }

    @Test
    public void setDifficultLevelTest() {
        Task t = new Task("CPSC302","2020.10.16",5);
        t.setDiffLevel(7);
        assertEquals(7,t.getDiffLevel());
    }

    @Test
    public void toJsonTest() {
        Task t = new Task("CPSC302","2020.10.16",5);
        JSONObject json = t.toJson();
        assertEquals("CPSC302",json.getString("description"));
        assertEquals("2020.10.16",json.getString("deadline"));
        assertEquals("unfinished",json.getString("status"));
        assertEquals(5,json.getInt("diffLevel"));
    }
}
