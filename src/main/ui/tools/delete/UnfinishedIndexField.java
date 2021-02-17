package ui.tools.delete;

import ui.ToDoListUI;
import ui.tools.TextField;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

//Represents a field that user can input the index of the unfinished task that needs to be deleted
public class UnfinishedIndexField extends TextField {

    public UnfinishedIndexField(ToDoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener and adds it to the text field
    @Override
    protected void addListener() {
        textField.getDocument().addDocumentListener(new UnfinishedIndexHandler());
    }

    private class UnfinishedIndexHandler implements DocumentListener {
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
            String unfinishedIndex = todoList.getUnfinishedIndex();
            if (!unfinishedIndex.isEmpty()) {
                todoList.delete.setEnabled(true);
            } else {
                todoList.delete.setEnabled(false);
            }
        }
    }
}
