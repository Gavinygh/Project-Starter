package ui.tools.load;

import ui.ToDoListUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents a load tool that user can use to load the todolist
public class LoadTool extends ui.tools.Tool {
    public LoadTool(ToDoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new load button and enables it initially
    @Override
    protected void createButton() {
        button = new JButton("Load");
        button.setEnabled(true);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object and adds it to the button
    @Override
    protected void addListener() {
        button.addActionListener(new LoadToolClickHandler());
    }

    private class LoadToolClickHandler implements ActionListener {

        // MODIFIES: todoList
        // EFFECTS: when button is pressed, load the todolist from jason Objects
        @Override
        public void actionPerformed(ActionEvent e) {
            Toolkit.getDefaultToolkit().beep();
            todoList.loadToDoList();
        }
    }
}
