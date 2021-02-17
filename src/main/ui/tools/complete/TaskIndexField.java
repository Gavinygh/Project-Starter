package ui.tools.complete;

import ui.ToDoListUI;
import ui.tools.TextField;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

//Represents a field that user can input the task index of the task needs to be marked as complete
public class TaskIndexField extends TextField {

    public TaskIndexField(ToDoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener and adds it to the text field
    @Override
    protected void addListener() {
        textField.getDocument().addDocumentListener(new TaskIndexHandler());
    }

    private class TaskIndexHandler implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            changed();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            changed();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            changed();
        }

        // MODIFIES: todoList
        // EFFECTS: enables the button if the text field is not empty
        public void changed() {
            String taskIndex = todoList.getCompleteIndex();
            if (!taskIndex.isEmpty()) {
                todoList.complete.setEnabled(true);
            } else {
                todoList.complete.setEnabled(false);
            }
        }
    }
}
