# CaseTracker

https://user-images.githubusercontent.com/43190631/144690115-0ffa4538-0824-436e-b57e-f40f4219aa88.mp4

## Project Description
Desktop application developed to track tasks using JavaFX. This application is meant to aid productivity and help with time management. To gain insight on the data stored, visit https://github.com/paulthz4/TaskTrackerWeb.

You can create as many tasks as you want with no dupilcates. Each task contains its date and time when it was created, as well as the time it has been worked on and is added the list of tasks, the list view. Each task is stored in MongoDB and can be retrieved.

#### Adding Tasks
Set the title of your new task in the text field under the label `New Task:` then click the `New Task` button. You will see the task in the list view. Click the `Summary` button to see all the tasks you've created in the left panel. At the bottom of the panel you will see the total time you've been working. 

#### Removing Tasks
The `Remove Task` button only removes the task from the list shown in the list view not from MongoDB. The 'Delete Task' button removes the task from the list view and from MongoDB. 

#### Searching Tasks
You can search for individual tasks from the list view or from MongoDB. Type the name in the text field beside the `Search` button. The task you are looking for should appear in the list view. If you are searching for a task that is stored in MongoDB click the `Search` button.
