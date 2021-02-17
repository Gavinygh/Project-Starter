package ui.tools.complete;

import ui.ToDoListUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents a complete tool that user can use to mark an unfinished task as complete
public class CompleteTool extends ui.tools.Tool {

    public CompleteTool(ToDoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new complete button and disables it initially
    @Override
    public void createButton() {
        button = new JButton("Complete Task");
        button.setEnabled(false);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object and adds it to the button
    @Override
    protected void addListener() {
        button.addActionListener(new CompleteToolCLickHandler());
    }

    private class CompleteToolCLickHandler implements ActionListener {

        // MODIFIES: todoList
        // EFFECTS: when button is pressed, complete the task with the given index and clear text field
        @Override
        public void actionPerformed(ActionEvent e) {
            Toolkit.getDefaultToolkit().beep();
            int index = Integer.parseInt(todoList.getCompleteIndex());
            todoList.markOneComplete(index);
            todoList.setTaskIndexField();
        }
    }
}
