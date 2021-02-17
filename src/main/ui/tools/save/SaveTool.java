package ui.tools.save;

import ui.ToDoListUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents a save tool that user can use to save the todolist
public class SaveTool extends ui.tools.Tool {

    public SaveTool(ToDoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new save button and enables it initially
    @Override
    protected void createButton() {
        button = new JButton("Save");
        button.setEnabled(true);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object and adds it to the button
    @Override
    protected void addListener() {
        button.addActionListener(new SaveToolClickHandler());
    }

    private class SaveToolClickHandler implements ActionListener {

        // MODIFIES: todoList
        // EFFECTS: when button is pressed, save the todolist to jason Objects
        @Override
        public void actionPerformed(ActionEvent e) {
            Toolkit.getDefaultToolkit().beep();
            todoList.saveToDoList();
        }
    }
}
