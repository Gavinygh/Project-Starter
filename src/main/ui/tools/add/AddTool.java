package ui.tools.add;

import model.Task;
import ui.ToDoListUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents an add tool that a user can use to add a new task
public class AddTool extends ui.tools.Tool {
    private Task task;

    public AddTool(ToDoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new add button and disables it initially
    @Override
    public void createButton() {
        button = new JButton("Add task");
        button.setEnabled(false);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object and adds it to the button
    @Override
    protected void addListener() {
        button.addActionListener(new AddToolClickHandler());
    }

    private class AddToolClickHandler implements ActionListener {

        // MODIFIES: todoList
        // EFFECTS: when button is pressed, get data from the fields in the panel and construct
        //          a task based on the data, and reset text field
        @Override
        public void actionPerformed(ActionEvent e) {
            Toolkit.getDefaultToolkit().beep();
            String description = todoList.getDescription();
            String deadline = todoList.getDeadline();
            String difficult = todoList.getDifficult();

            task = new Task(description, deadline, Integer.parseInt(difficult));
            todoList.addNewTask(task);

            todoList.setDescriptionField();
            todoList.setDeadlineField();
            todoList.setDifficultField();
        }
    }
}
