package ui.tools.delete;

import model.ToDoList;
import ui.ToDoListUI;
import ui.tools.TextField;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

//Represents a field that user can input the index of the finished task that needs to be deleted
public class FinishedIndexField extends TextField {

    public FinishedIndexField(ToDoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener and adds it to the text field
    @Override
    protected void addListener() {
        textField.getDocument().addDocumentListener(new FinishedIndexHandler());
    }

    private class FinishedIndexHandler implements DocumentListener {
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
            String finishedIndex = todoList.getFinishedIndex();
            if (!finishedIndex.isEmpty()) {
                todoList.delete.setEnabled(true);
            } else {
                todoList.delete.setEnabled(false);
            }
        }
    }
}
