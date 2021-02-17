package ui.tools.add;

import ui.ToDoListUI;
import ui.tools.TextField;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

//Represents a field that user can input the difficulty of the task
public class DifficultField extends TextField {
    public DifficultField(ToDoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener and adds it to the text field
    @Override
    protected void addListener() {
        textField.getDocument().addDocumentListener(new DifficultHandler());
    }

    private class DifficultHandler implements DocumentListener {
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
        // EFFECTS: enables the button if all the text fields in add area is not empty
        public void changed() {
            String difficult = todoList.getDifficult();
            String description = todoList.getDescription();
            String deadline = todoList.getDeadline();
            if (!difficult.isEmpty() && !description.isEmpty() && !deadline.isEmpty()) {
                todoList.add.setEnabled(true);
            } else {
                todoList.add.setEnabled(false);
            }
        }
    }
}
