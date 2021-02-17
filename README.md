# My Personal Project

## Task Manager


Task Manager is an offiline, desktop application that allows you manage your task properly.
The application can help the user to remember their undo tasks and finished tasks.
This application is designed for students, office worker, and others who often need to finish tasks
before deadline.
As a student, I usually have several projects, assignment, and quizs during a week. And usually
these tasks come out together. Although I have a good memory, I still miss some of them and this
makes me very disappointed. I believe this situation will also exist for other. So I design 
the application to help myself and others.


##As a user
 > - you can add a new task to your to-do list.
 
 > - you can view the tasks on the to-do list.
 
 > - you can mark a task as complete on the to-do list.
 
> - you can delete a task from the to-do-list.
 
> - you can see the number of incomplete and number of completed tasks 
 on the to-do list.

> - you can save the to-do list to file

> - you can load your to-do list from file

> - you are able to be reminded to save your to-do list 
to file and have the option to do so or not when you quit

> - you will be given the option to load your to-do list from file 
when you start the application
 
##Phase 4: Task 2
 I included a type hierarchy in this project.
 Tool and TextField are superclasses. 
 
 Each Tool object have a JButton object, and a ToDoListUI object.
 The corresponding operations are performed on the ToDoListUI object
 once the user press the button. I have ViewAllTool, SaveTool, LoadTool, DeleteTool
 , CompleteTool, and AddTool in my ui package as subclasses of Tool class.
 
 The TextField object represents the text field that can receive and display
 the input from user. Each TextField have a JTextField object, and a ToDoListUI object.
 The corresponding operations are performed on the ToDoListUI object once the user has
 input something in the text field. I have FinishedIndexField, UnfinishedIndexField, 
 TaskIndexField, DifficultField, DescriptionField, and DeadlineField as subclasses of 
 TextField class.
 
 ##Phase 4: Task 3
 In the UML, abstract class TextField is the superclass of 
 DeadlineField, DifficultField, FinishedIndexField,
 DescriptionField, UnfinishedIndexField, and TaskIndexField. So in the UML,
 these classes extend the abstract class TextField.
 
 Also, abstract class Tool is the superclass of DeleteTool, ViewAllTool, CompleteTool,
 LoadTool, SaveTool, AddTool. So in the UML, these classes extend the abstract class Tool.
 
 ToDoListUI contains different tools and textFields exactly once. So each ToDoListUI
 is associated with the above tools and textFields with multiplicity 1.
 
 ToDoListUI also contains one JasonReader object and one jasonWriter object, so
 ToDoListUI have a uni-directional association to them with multiplicity 1.
 
 Each ToDoListUI is associated with one ToDoList object. 
 And each ToDoList object is associated with many Task objects. So we consider ToDoList
 to be the "whole" and Task objects to be the parts. Both ToDoList and Task implement 
 Writable.