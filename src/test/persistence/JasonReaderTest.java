package persistence;

import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JasonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ToDoList tol = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyToDolist.json");
        try {
            ToDoList tol = reader.read();
            assertEquals(0, tol.getCompletedNum());
            assertEquals(0, tol.getIncompletedNum());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralToDolist.json");
        try {
            ToDoList tol = reader.read();
            List<Task> done = tol.getDoneTasks();
            assertEquals(1, done.size());
            assertEquals("CPSC210",done.get(0).getDescription());
            assertEquals("2020.10.26",done.get(0).getDeadline());
            assertEquals("finished",done.get(0).getStatus());
            assertEquals(4,done.get(0).getDiffLevel());
            List<Task> notDone = tol.getNotDoneTasks();
            assertEquals(1, notDone.size());
            assertEquals("CPSC302",notDone.get(0).getDescription());
            assertEquals("2020.10.16",notDone.get(0).getDeadline());
            assertEquals("unfinished",notDone.get(0).getStatus());
            assertEquals(3,notDone.get(0).getDiffLevel());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}