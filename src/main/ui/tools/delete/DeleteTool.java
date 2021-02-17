package ui.tools.delete;

import ui.ToDoListUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents the delete tool that user can use to delete a task
public class DeleteTool extends ui.tools.Tool {

    public DeleteTool(ToDoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new delete button and disables it initially
    @Override
    protected void createButton() {
        button = new JButton("Delete");
        button.setEnabled(false);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object and adds it to the button
    @Override
    protected void addListener() {
        button.addActionListener(new DeleteToolClickHandler());
    }

    private class DeleteToolClickHandler implements ActionListener {

        // MODIFIES: todoList
        // EFFECTS: when button is pressed, get data from the fields in the panel and delete
        //          the task with the corresponding index, and reset text fields
        @Override
        public void actionPerformed(ActionEvent e) {
            Toolkit.getDefaultToolkit().beep();
            if (!todoList.getFinishedIndex().isEmpty() && !todoList.getUnfinishedIndex().isEmpty()) {
                todoList.setText(todoList.deleteFinishedTask(Integer.parseInt(todoList.getFinishedIndex()))
                        + todoList.deleteUnfinishedTask(Integer.parseInt(todoList.getUnfinishedIndex())));
            } else if (!todoList.getFinishedIndex().isEmpty()) {
                todoList.setText(todoList.deleteFinishedTask(Integer.parseInt(todoList.getFinishedIndex())));
            } else {
                todoList.setText(todoList.deleteUnfinishedTask(Integer.parseInt(todoList.getUnfinishedIndex())));
            }
            todoList.setFinishedIndexField();
            todoList.setUnfinishedIndexField();
        }
    }
}
