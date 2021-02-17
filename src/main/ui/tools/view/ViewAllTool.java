package ui.tools.view;

import ui.ToDoListUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents a view tool that user can use to view the todolist
public class ViewAllTool extends ui.tools.Tool {
    public ViewAllTool(ToDoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    // EFFECTS: creates a viewAll button for the tool
    @Override
    protected void createButton() {
        button = new JButton("ViewAll");
        button.setEnabled(true);
    }

    // EFFECTS: adds listener to the button
    @Override
    protected void addListener() {
        button.addActionListener(new ViewAllToolClickHandler());
    }

    private class ViewAllToolClickHandler implements ActionListener {

        // MODIFIES: todoList
        // EFFECTS: when button is pressed, print all the tasks on the textArea
        @Override
        public void actionPerformed(ActionEvent e) {
            Toolkit.getDefaultToolkit().beep();
            todoList.setAllTasksOnText();
        }
    }
}
