package persistence;

import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JasonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ToDoList tol = new ToDoList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyToDolist() {
        try {
            ToDoList tol = new ToDoList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyToDolist.json");
            writer.open();
            writer.write(tol);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyToDolist.json");
            tol = reader.read();
            assertEquals(0, tol.getCompletedNum());
            assertEquals(0, tol.getIncompletedNum());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralToDolist() {
        try {
            ToDoList tol = new ToDoList();
            tol.addIncompletedTask(new Task("CPSC210","2020.10.16",6));
            tol.addCompletedTask(new Task("CPSC302","2020.10.16","finished",5));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralToDolist.json");
            writer.open();
            writer.write(tol);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralToDoList.json");
            tol = reader.read();
            List<Task> notDone = tol.getNotDoneTasks();
            assertEquals(1, notDone.size());
            assertEquals("CPSC210",notDone.get(0).getDescription());
            assertEquals("2020.10.16",notDone.get(0).getDeadline());
            assertEquals("unfinished",notDone.get(0).getStatus());
            assertEquals(6,notDone.get(0).getDiffLevel());
            List<Task> done = tol.getDoneTasks();
            assertEquals(1, done.size());
            assertEquals("CPSC302",done.get(0).getDescription());
            assertEquals("2020.10.16",done.get(0).getDeadline());
            assertEquals("finished",done.get(0).getStatus());
            assertEquals(5,done.get(0).getDiffLevel());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
