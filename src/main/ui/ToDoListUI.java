package ui;

import model.Task;
import model.ToDoList;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tools.add.AddTool;
import ui.tools.add.DeadlineField;
import ui.tools.add.DescriptionField;
import ui.tools.add.DifficultField;
import ui.tools.complete.CompleteTool;
import ui.tools.complete.TaskIndexField;
import ui.tools.delete.DeleteTool;
import ui.tools.delete.FinishedIndexField;
import ui.tools.delete.UnfinishedIndexField;
import ui.tools.load.LoadTool;
import ui.tools.save.SaveTool;
import ui.tools.view.ViewAllTool;


import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


// ToDoList teller application
public class ToDoListUI extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    public Font textFieldFont = new Font("TimesRoman",Font.PLAIN,14);

    // add
    public AddTool add;
    private DescriptionField descriptionField;
    private DeadlineField deadlineField;
    private DifficultField difficultField;

    //complete
    public CompleteTool complete;
    private TaskIndexField taskIndexField;

    //delete
    public DeleteTool delete;
    private FinishedIndexField finishedIndexField;
    private UnfinishedIndexField unfinishedIndexField;

    //save and load
    public SaveTool saveTool;
    public LoadTool loadTool;

    //view
    public ViewAllTool viewAllTool;

    private JTextArea textArea;
    private JPanel textPanel;
    private JPanel toolArea;

    private static final String JSON_STORE = "./data/todolist.json";
    private ToDoList toDoItem;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: creates the window for the todolist
    public ToDoListUI() {
        super("To-Do List Application");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setUpToDoList();
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS: draws the JFrame window for the to do list, and makes the tools that will work on the to do list
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        createTextPanel();
        createTools();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: create all the tool buttons and add/complete/delete areas on the downside of the window
    private void createTools() {
        Container toolContainer = getContentPane();
        toolArea = new JPanel();
        toolArea.setLayout(new GridBagLayout());
        toolArea.setPreferredSize(new Dimension(WIDTH, HEIGHT / 3));
        toolContainer.add(toolArea, BorderLayout.SOUTH);
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        gc.gridx = 0;
        gc.gridy = 0;

        toolArea.add(createAddPanel(), gc);

        gc.gridx = 1;
        toolArea.add(createCompletePanel(), gc);

        gc.gridx = 2;
        toolArea.add(createDeletePanel(), gc);

        gc.gridx = 3;
        toolArea.add(createViewAndSaveLoadPanel(), gc);

    }

    // MODIFIES: this
    // EFFECTS: constructs the area where users can save, load the todolist, or view the todolist
    private JPanel createViewAndSaveLoadPanel() {
        JPanel viewAndSaveLoadPanel = new JPanel();
        viewAndSaveLoadPanel.setLayout(new GridBagLayout());
        viewAndSaveLoadPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("View,Save,Load"),
                BorderFactory.createEmptyBorder(10,10,10,10)));
        GridBagConstraints viewAndSaveLoadPanelConstraints = new GridBagConstraints();
        viewAndSaveLoadPanelConstraints.weightx = 0.5;
        viewAndSaveLoadPanelConstraints.weighty = 0.5;
        viewAndSaveLoadPanelConstraints.insets = new Insets(0,0,10,0);

        viewAndSaveLoadPanelConstraints.gridx = 0;
        viewAndSaveLoadPanelConstraints.gridy = 0;
        saveTool = new SaveTool(this, viewAndSaveLoadPanel,viewAndSaveLoadPanelConstraints);

        viewAndSaveLoadPanelConstraints.gridy = 1;
        loadTool = new LoadTool(this, viewAndSaveLoadPanel,viewAndSaveLoadPanelConstraints);

        viewAndSaveLoadPanelConstraints.gridy = 2;
        viewAllTool = new ViewAllTool(this, viewAndSaveLoadPanel,viewAndSaveLoadPanelConstraints);
        return viewAndSaveLoadPanel;
    }



    // MODIFIES: this
    // EFFECTS: constructs the area where users can create the task to be added
    private JPanel createAddPanel() {
        JPanel addPanel = new JPanel();
        addPanel.setLayout(new GridBagLayout());
        addPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Add an Task:"),
                BorderFactory.createEmptyBorder(10,10,10,10)));
        GridBagConstraints addPanelConstraints = new GridBagConstraints();
        addPanelConstraints.weightx = 0.5;
        addPanelConstraints.weighty = 0.5;
        addPanelConstraints.insets = new Insets(0,0,10,0);

        createAndAddLabel("Description: ", 0, 0, addPanel, addPanelConstraints);
        addPanelConstraints.gridx = 1;
        descriptionField = new DescriptionField(this, addPanel, addPanelConstraints);


        createAndAddLabel("Deadline: ",0, 1, addPanel, addPanelConstraints);
        addPanelConstraints.gridx = 1;
        deadlineField = new DeadlineField(this, addPanel, addPanelConstraints);

        createAndAddLabel("Difficulty: ",0, 2, addPanel, addPanelConstraints);
        addPanelConstraints.gridx = 1;
        difficultField = new DifficultField(this, addPanel, addPanelConstraints);

        addPanelConstraints.gridy = 3;
        add = new AddTool(this, addPanel, addPanelConstraints);
        return addPanel;
    }

    //MODIFIES:panel, panelConstraints
    //EFFECTS: constructs a label with given name and add it to panel with given x and y
    private void createAndAddLabel(String labelName,
                                   int x, int y, JPanel panel, GridBagConstraints panelConstraints) {
        JLabel label = new JLabel(labelName);
        panelConstraints.gridx = x;
        panelConstraints.gridy = y;
        panel.add(label, panelConstraints);
    }

    // MODIFIES: this
    // EFFECTS: constructs the area where users can delete the task from finished tasks or unfinished tasks
    private JPanel createDeletePanel() {
        JPanel deletePanel = new JPanel();
        deletePanel.setLayout(new GridBagLayout());
        deletePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Delete task:"),
                BorderFactory.createEmptyBorder(10,10,10,10)));
        GridBagConstraints deletePanelConstraints = new GridBagConstraints();
        deletePanelConstraints.weightx = 0.5;
        deletePanelConstraints.weighty = 0.5;
        deletePanelConstraints.insets = new Insets(0,0,10,0);

        createAndAddLabel("Finished Task Index: ",0, 0, deletePanel, deletePanelConstraints);
        deletePanelConstraints.gridx = 1;
        finishedIndexField = new FinishedIndexField(this, deletePanel, deletePanelConstraints);

        createAndAddLabel("Unfinished Task Index: ",0, 1, deletePanel, deletePanelConstraints);
        deletePanelConstraints.gridx = 1;
        unfinishedIndexField = new UnfinishedIndexField(this, deletePanel, deletePanelConstraints);

        deletePanelConstraints.gridy = 2;
        delete = new DeleteTool(this, deletePanel, deletePanelConstraints);
        return deletePanel;
    }

    // MODIFIES: this
    // EFFECTS: constructs the area where the user can input the task index they want to be completed
    private JPanel createCompletePanel() {
        JPanel completePanel = new JPanel();
        completePanel.setLayout(new GridBagLayout());
        completePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Complete a task:"),
                BorderFactory.createEmptyBorder(10,10,10,10)));
        GridBagConstraints completePanelConstraints = new GridBagConstraints();
        completePanelConstraints.weightx = 0.5;
        completePanelConstraints.weighty = 0.5;
        completePanelConstraints.insets = new Insets(0,0,10,0);

        createAndAddLabel("Task Index: ", 0, 0, completePanel, completePanelConstraints);
        completePanelConstraints.gridx = 1;
        taskIndexField = new TaskIndexField(this, completePanel, completePanelConstraints);

        completePanelConstraints.gridy = 1;
        complete = new CompleteTool(this, completePanel, completePanelConstraints);
        return completePanel;
    }

    // MODIFIES: this
    // EFFECTS: creates the area where all messages will be printed to.
    private void createTextPanel() {
        textPanel = new JPanel();
        textArea = new JTextArea();
        textArea.setFont(textFieldFont);
        textPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT * 2 / 3));
        Color c = new Color(110,160,210);
        textPanel.setBackground(c);

        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setPreferredSize(new Dimension(WIDTH - 20, HEIGHT * 2 / 3 - 30));

        textArea.setEditable(false);
        textArea.setBackground(Color.WHITE);
        setAllTasksOnText();

        textPanel.add(scroll);

        add(textPanel, BorderLayout.WEST);
    }

    //MODIFIES: this
    //EFFECTS:  add the task to the todolist
    public void addNewTask(Task task) {
        toDoItem.addIncompletedTask(task);
        setText("\nAdded.");
    }

    //EFFECTS: set all tasks on the textArea
    public void setAllTasksOnText() {
        setText(unfinishedTasksReport() + finishedTasksReport());
    }

    //MODIFIES:this
    //EFFECTS:set the given txt on the textArea
    public void setText(String txt) {
        textArea.setText(txt);
    }

    //EFFECTS: returns all the unfinished tasks as a string
    public String unfinishedTasksReport() {
        String unfinished;
        if (toDoItem.getIncompletedNum() == 0) {
            unfinished = "\nThere is no unfinished task.";
        } else {
            unfinished = "\nThe unfinished tasks are the following:";
            int j = 0;
            for (Task i:toDoItem.getNotDoneTasks()) {
                String difficult = Integer.toString(i.getDiffLevel());
                String index = Integer.toString(j + 1);
                unfinished = unfinished + "\n\t" + index + ". Task Description:" + i.getDescription() + ". Deadline: "
                        + i.getDeadline() + ". Status: " + i.getStatus() + ". Difficult level:" + difficult;
                ++j;
            }
        }
        return unfinished;
    }

    //EFFECTS: returns all the finished tasks as a string
    public String finishedTasksReport() {
        String finished;
        if (toDoItem.getCompletedNum() == 0) {
            finished = "\nThere is no finished task.";
        } else {
            finished = "\nThe finished tasks are the following:";
            int j = 0;
            for (Task i:toDoItem.getDoneTasks()) {
                String difficult = Integer.toString(i.getDiffLevel());
                String index = Integer.toString(j + 1);
                finished = finished + "\n\t" + "Task" + index
                        + ". Task Description:" + i.getDescription() + ". Deadline: "
                        + i.getDeadline() + ". Status: " + i.getStatus() + ". Difficult level:" + difficult;
                ++j;
            }
        }
        return finished;
    }

    // EFFECTS: gets the text in the finishedIndex field in the delete area
    public String getFinishedIndex() {
        return finishedIndexField.getFieldText();
    }

    // EFFECTS: gets the text in the unfinishedIndex field in the delete area
    public String getUnfinishedIndex() {
        return unfinishedIndexField.getFieldText();
    }

    // EFFECTS: gets the text in the task index field in the complete area
    public String getCompleteIndex() {
        return taskIndexField.getFieldText();
    }

    // EFFECTS: gets the text in the difficulty field in the add area
    public String getDifficult() {
        return difficultField.getFieldText();
    }

    // EFFECTS: gets the text in the description field in the add area
    public String getDescription() {
        return descriptionField.getFieldText();
    }

    // EFFECTS: gets the text in the deadline field in the add area
    public String getDeadline() {
        return deadlineField.getFieldText();
    }

    //MODIFIES: this
    //EFFECTS: mark one task with index from unfinished tasks as complete and update the message on textarea
    public void markOneComplete(int index) {
        if (validIndex(toDoItem.getNotDoneTasks(),index - 1)) {
            toDoItem.markCompleted(index - 1);
            setText("\nSuccess! You have mark task " + index + " as complete.");
        } else {
            setText("\nFail, invalid task index. Please try again.");
        }
    }

    //MODIFIES:this
    //EFFECTS: initializes todolist
    public void setUpToDoList() {
        toDoItem = new ToDoList();
    }

    // EFFECTS: saves the todolist to file
    public void saveToDoList() {
        try {
            jsonWriter.open();
            jsonWriter.write(toDoItem);
            jsonWriter.close();
            setText("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            setText("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads todolist from file
    public void loadToDoList() {
        try {
            toDoItem = jsonReader.read();
            setText("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            setText("Unable to read from file: " + JSON_STORE);
        }
    }


    //EFFECTS: check that if the todolist contains the task with index i
    public boolean validIndex(List<Task> lot, int i) {
        if (lot.size() - 1 < i) {
            return false;
        }
        return true;
    }

    //MODIFIES: this
    //EFFECTS: delete a task with the given index from finished tasks
    public String deleteFinishedTask(int index) {
        try {
            String task = deleteHelper(toDoItem.getDoneTasks(), index);
            return "\nThe following task has been deleted from finished tasks: " + task;
        } catch (IOException e) {
            return "\nThere are no finished task with index " + index + ". Please input valid task index.";
        }
    }

    //MODIFIES: this
    //EFFECTS: delete one task with index from finished tasks
    public String deleteUnfinishedTask(int index) {
        try {
            String task = deleteHelper(toDoItem.getNotDoneTasks(), index);
            return "\nThe following item has been deleted from unfinished tasks: " + task;
        } catch (IOException e) {
            return "\nThere are no unfinished task with index " + index + ". Please input valid task index.";
        }
    }

    //MODIFIES:lot
    //EFFECTS:delete the task with given index from lot
    //        if there is no task with index, throws a TaskNotFound exception
    public String deleteHelper(List<Task> lot, int index) throws IOException {
        String task;
        if (validIndex(lot, index - 1)) {
            task = "\n\t" + "Task Description:"
                    + lot.get(index - 1).getDescription() + ". Deadline: "
                    + lot.get(index - 1).getDeadline() + ". Status: " + lot.get(index - 1).getStatus()
                    + ". Difficult level:" + lot.get(index - 1).getDiffLevel();
            lot.remove(index - 1);
            System.out.println(task);
            return task;
        } else {
            throw new IOException();
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the description field in the add area to empty
    public void setDescriptionField() {
        descriptionField.setEmpty();
    }

    // MODIFIES: this
    // EFFECTS: sets the deadline field in the add area to empty
    public void setDeadlineField() {
        deadlineField.setEmpty();
    }

    // MODIFIES: this
    // EFFECTS: sets the difficult field in the add area to empty
    public void setDifficultField() {
        difficultField.setEmpty();
    }


    // MODIFIES: this
    // EFFECTS: sets the finishedIndex field in the delete area to empty
    public void setFinishedIndexField() {
        finishedIndexField.setEmpty();
    }

    // MODIFIES: this
    // EFFECTS: sets the unfinishedIndex field in the delete area to empty
    public void setUnfinishedIndexField() {
        unfinishedIndexField.setEmpty();
    }

    // MODIFIES: this
    // EFFECTS: sets the taskIndex field in the complete area to empty
    public void setTaskIndexField() {
        taskIndexField.setEmpty();
    }
}
