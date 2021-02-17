package ui.tools;

import ui.ToDoListUI;

import javax.swing.*;
import java.awt.*;

//Represents a text field
public abstract class TextField {
    protected ToDoListUI todoList;
    protected JTextField textField;

    public TextField(ToDoListUI todoList, JComponent parent, GridBagConstraints gc) {
        this.todoList = todoList;
        textField = new JTextField(8);
        textField.setFont(todoList.textFieldFont);
        addToParent(parent, gc);
        addListener();
    }

    // MODIFIES: parent
    // EFFECTS: add the text field to the parent component with the given constraints
    private void addToParent(JComponent parent, GridBagConstraints gc) {
        parent.add(textField, gc);
    }

    // EFFECTS: add listener to the text field
    protected abstract void addListener();

    // EFFECTS: return the string in the text field
    public String getFieldText() {
        return textField.getText();
    }

    // MODIFIES: this
    // EFFECT: sets the text field to empty
    public void setEmpty() {
        textField.setText("");
    }
}
