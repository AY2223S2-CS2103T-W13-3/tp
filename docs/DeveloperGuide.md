---
layout: page
title: Developer Guide
---
* Table of Contents
  * [1. Introduction](#1-introduction)
    * [1.1 Acknowledgements](#11-acknowledgements)
    * [1.2 Setting up, getting started](#12-setting-up-getting-started)
  * [2. Design](#2-design)
    * [2.1 Architecture](#21-architecture)
    * [2.2 UI Component](#22-ui-component)
    * [2.3 Logic Component](#23-logic-component)
    * [2.4 Model Component](#24-model-component)
    * [2.5 Storage Component](#25-storage-component)
    * [2.6 Common Classes](#26-common-classes)
  * [3. Implementation](#3-implementation)
    * [3.1 Add Feature](#31-add-feature)
    * [3.2 Clear Feature](#32-clear-feature)
    * [3.3 Delete Feature](#33-delete-feature)
    * [3.4 Edit Feature](#34-edit-feature)
    * [3.5 Find Feature](#35-find-feature)
    * [3.6 List Feature](#36-list-feature)
    * [3.7 Help Feature](#37-help-feature)
    * [3.8 Stats Feature](#38-stats-feature)
    * [3.9 Sort Feature](#39-sort-feature)
    * [3.10 Alert Feature](#310-alert-feature)
    * [3.11 Plan Feature](#311-plan-feature)
  * [4. Documentation, Logging, Testing, Configuration, DevOps](#4-documentation-logging-testing-configuration-dev-ops)
  * [5. Appendix: Requirements](#5-appendix-requirements)
  * [6. Appendix: Instructions for manual testing](#6-appendix-instructions-for-manual-testing)

--------------------------------------------------------------------------------------------------------------------

## 1. Introduction

This Developer Guide details Clock-Work's design and implementation details.

Clock-Work is a **desktop application for managing tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).
It is an application curated for the average Computer Science student, and supports basic commands like `add`, `edit`, `delete`, and advanced commands like `alert`, `schedule`, `find`.
By helping Computer Science students plan their time more efficiently, Clock-Work aims to make the busy Computing life a little more manageable.

This guide is meant for developers who are interested in Clock-Work, and want to contribute to it.

If you are only interested in using the application, do take a look at our [User Guide](UserGuide.md) instead.

If you would like to contact the development team, we can be contacted at our [About Us](AboutUs.md).

### 1.1 **Acknowledgements**

* This project is built from the AddressBook-Level3 project created by the SE-EDU initiative.
* Tag's color code examples courtesy of https://sashamaps.net/docs/resources/20-colors/.

### 1.2 **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## 2. **Design**

**This section provides an overview of the classes used within Clock-Work. The general interactions between components, and the internal structure of a component is explained here.
A good understanding of the content in this section is useful in understanding how the individual features are implements in [section 3 - Implementation](#3-implementation)**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### 2.1 Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### 2.2 UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `TaskListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Task` object residing in the `Model`.

### 2.3 Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `TaskBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a task).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `TaskBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### 2.4 Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Task` objects (which are contained in a `UniqueTaskList` object).
* stores the currently 'selected' `Task` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Task>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* * stores the generated plan, i.e. all `DailyPlan` objects (which are contained within a `MonthlyPlan` object)
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Task` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Task` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### 2.5 Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data, user preference data, and planner data in json format, and read them back into corresponding objects.
* inherits from `AddressBookStorage`, `UserPrefStorage` and `PlannerStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### 2.6 Common classes

Classes used by multiple components are in the `seedu.task.commons` package.

--------------------------------------------------------------------------------------------------------------------

## 3. **Implementation**

**This section describes some noteworthy details on how certain features are implemented.**

**You are encouraged to look at the documentation for individual features before making any changes to prevent unintended behaviour.**

### 3.1 Add Feature

The add feature now supports three types of additions: simple tasks, events and deadlines. The AddCommandParser will handle the multiple prefixes in the input before the AddCommand adds a list of tasks into the taskbook.
Also, our feature allows the user to input multiple tasks with the same descriptions and tags but with different names. This makes it easier for user to add repetitive tasks with similar details.

You can find the specific implementation in the 'AddCommandParser' class and the 'AddCommand' class

Given below is a scenario of how the add command is used and behaves.

Step 1. The user inputs an `add` Command with parameters `n/CS2109S n/CS2103T n/homework`.

Step 2. The AddCommandParser recognises that the input command has two names and one description as the parameters. Since there is no deadline prefix 'D' and event prefixes 'f' and 't', the parser will create tasks that are simple tasks. The tasks will have the same description but different names

Step 3. All the tasks are added to a temporary task list and the list is stored in the add command.

Step 4. The add command is returned to the logic manager for execution.

Step 5. For each of the task in the temporary task list, we add them to the task list in the model.

Step 6. The result of execution is returned to the logic manager and the UI will display the result as a message.

The following diagram summarises the sequence of events happening during the execution.
![AddCommandSequenceDiagram](images/AddCommand.png)

The following diagram summarises how the activities unfold after the user types 'Find' Command.
![SortCommandSequenceDiagram](images/AddActivityDiagram.png)

### 3.2 Clear Feature

Replaces the current taskBook to be a completely new TaskBook with no tasks.

Given below is an example usage scenario and how clear is executed.

Step 1. The user inputs `clear` command. The parser recognises the command word and calls `ClearCommand`.

Step 2. `ClearCommand` is executed, and a fresh new TaskBook replaces the current TaskBook.

Step 3. Results are shown immediately on UI.

The following diagram summarises the sequence of events happening during the execution.
![ClearCommandSequenceDiagram](images/ClearCommandSequenceDiagram.png)

### 3.3 Delete Feature
Deletes a task based on index(es) of tasks from the list currently being shown to users.

Previously in AB3, deletion of task must be done 1 index at a time, but this feature is extended to support deletion at multiple indices in Clock-Work to improve the efficiency of the program.
Input index(es) is checked for validity (has a task at supposed index and is entered in ascending order) within _DeleteCommandParser_, and an error prompt will be displayed to users should the input be invalid.

Multiple deletions within a single command must be done in the following manner:
1. Indices must be separated by whitespace, such as `delete 1 2 3`
2. Indices must be entered in ascending order

In the event where one of the multiple indices entered is invalid, the entire command is rejected and no deletion is executed, and users will be informed about the non-execution.
This is to enforce atomicity and date safety, as deletion is irreversible, so it should only be executed when it is certain that the user is clear about the intended behavior of the command.

Given below is an example usage scenario and how `delete` is executed.

Step 1. The user inputs a `delete` command with parameter `1 2`. The parser recognises the command word and calls _DeleteCommandParser_.

Step 2. The _DeleteCommandParser_ interprets the indices and saves it as an IndexList.

Step 3. _DeleteCommandParser_ calls _DeleteCommand_.

Step 4. _DeleteCommand_ is executed and all relevant tasks are removed from TaskBook.

Step 5. Results are shown immediately on UI.

The following sequence diagram summarizes what happens in this example usage scenario:

![DeleteCommandSequenceDiagram](images/DeleteCommandSequenceDiagram.png)

The following activity diagram summarizes what happens when a user executes a `delete`:

![DeleteCommandSequenceDiagram](images/DeleteCommandActivityDiagram.png)

#### 3.3.1 Design Consideration

#### Option 1: Allow multiple deletions
Pros: Saves user some time as they do not need to enter `delete` multiple times.
Cons: Entire command must be rejected if one of the input is wrong to avoid unintended behaviours.

#### Option 2: Only allow single deletion
Pros: User can pinpoint which index is causing the rejection.
Cons: Takes more time to delete multiple task at once.

#### Eventual Decision
Option 1 is chosen as a more experienced user will know what types of input are accepted by Clock-Work. Our target users, Computer Science students, should be quick on the uptake of syntax, and should be able to reap the efficiency benefits of multiple deletions.


### 3.4 Edit Feature

### 3.5 Find Feature

Before, the `find` feature on AB3 would find persons whose names contain any of the given keywords. This limited the search functionality to purely names. With our new enhancement, `find` can now search via any field.
The FindCommandParser does the heavy lifting where it will automatically create appropriate predicates based on user input.

You can find the specific implementation in the `FindCommandParser` class and the `FindCommand` class.

Given below is an example usage scenario and how the find mechanism behaves.

Step 1. The user inputs a `find` command with parameter `n/read`. The parser recognises the command word and calls the FindCommandParser.

Step 2. The `FindCommandParser` recognises that the parameter is being searched is the name field.

Step 3. `FindCommandParser` calls `FindCommand` with appropriate predicate (`NameContainsAllKeywordsPredicate`).

Step 4. `FindCommand` is executed and model filters with the predicate passed.

Step 5. The result of the filtered list is passed back to the UI.

The following sequence diagram summarizes what happens in this example usage scenario:

![SortCommandSequenceDiagram](images/FindSequenceDiagram.png)

The following activity diagram summarizes what happens when a user executes a new `find` command:

![SortCommandSequenceDiagram](images/FindActivityDiagram.png)

#### 3.5.1 Design Consideration

#### Option 1 (current choice):
* Find can search any attribute
* It searches via matched substring
* In the case of a sample search term `n/read book`, the whole string must be in the name of the task.
* Only accepts 1 attribute at a time.
* Can accept multiple of same attribute search terms along with a flag to indicate type of searching.
* e.g. `all/`

Pros: Simple for users while still being flexible and powerful.
Cons: Cannot find by multiple attributes at a time for more powerful functionality.

#### Option 2:
* Allow mix-and-matching of attributes for searching
* Can still have a flag to indicate any or all search term matching

Pros: More powerful functionality
Cons: More complicated to implement and unwieldy for users.

### 3.6 List Feature

The list feature shows every task and its associated fields to the user. In scenarios whereby the user has used commands like `sort` or `find`, The user will use `list` to get back to the original page.

Given below is an example usage scenario for the `list` command.

Step 1. The user inputs `list` command. The parser recognises the command word and calls `listCommand`.

Step 2. `listCommand` is executed. A full list of all tasks are displayed.

Step 3. Results are shown immediately on UI.

![ListCommandSequenceDiagram](images/ListCommandSequenceDiagram.png)

### 3.7 Help Feature

In a scenario whereby the user does not know how to proceed, `help` is intended to assist the user by giving them the link to the User Guide for the project.

Given below is an example usage scenario for the `help` command.

Step 1. The user inputs `help` command. The parser recognises the command word and calls `HelpCommand`.

Step 2. `HelpCommand` is executed. A pop-up with the link to our User Guide is shown.

Step 3. Results are shown immediately on UI.

The sequence diagram below summarizes what happens during this usage scenario:
![HelpCommandSequenceDiagram](images/HelpCommandSequenceDiagram.png)

### 3.8 Stats Feature
Statistics is a useful way for users to get an overview of all open tasks in the TaskBook. Currently, `stats` supports 1 view - categorise by tags.
Entering `stats` counts the number of tasks that fall under each tag, and displays the count in descending order, for up to a maximum of 10 tags.

Given below is an example usage scenario for `stats`.

Step 1. User inputs `stats` and presses enter.

Step 2. The application displays a list of tags and the corresponding number of times it appears in the application.

The following sequence diagram provides an overview of how `stats` works:

![StatsCommandSequenceDiagram](images/StatsCommandSequenceDiagram.png)

The following activity diagram summarizes what happens when a user executes a new command:

![StatsCommandActivityDiagram](images/StatsCommandActivityDiagram.png)

#### 3.8.1 Design Considerations

What type of information should be displayed by `stats`

#### Option 1: Number of tasks per tag
Pros: Assuming user uses tags to categorise tasks into subject folders, this will be useful in showing the user which subject requires more time on.

#### Option 2: Number of completed tasks
Pros: Motivational for users to know their progress
Cons: May have a reverse effect as users feel complacent about their progress

#### Option 3: Number of tasks under each category (_SimpleTask, Deadline, Event_)
Pros: Users can get an overview of their task composition
Cons: Does not improve productivity

#### Eventual Decision
As Clock-Work is an application to help Computer Science students improve their productivity, option 1 is chosen, as it can provide the most benefit to our target user group.

### 3.9 Sort Feature

Given below is an example usage scenario and how the sort command behaves at each step

Step 1. The user launches the application.

Step 2. The application displays a list of tasks (that can also be empty).

Step 2. The user executes `sort` command to sort the list. Look at [Sort Design Consideration](#391-design-consideration) for the sorting logic.

Step 3. The sequence diagram below shows how the sort operation works:

![SortCommandSequenceDiagram](images/SortCommandSequenceDiagram.png)

The following activity diagram summarizes what happens when a user executes a new command:

![SortCommandActivityDiagram](images/SortCommandActivityDiagram.png)

#### 3.9.1 Design Consideration

Sorts the list using the following format:

Aspect: How are tasks sorted:

#### Option 1(current choice):

* SimpleTask is listed above Deadline and Event.
* Deadline is  listed below SimpleTask and above Event.
* Event is  listed below SimpleTask and Event.
* When comparing 2 tasks of the same class:

  * SimpleTask
    * The task with lesser tags is listed above the task with more tags.
    * Else if both tasks have the same number of tags, the task with a smaller lexicographical name is listed above the other.
  * Deadline
    * The task with the earlier deadline is listed above the task with later deadline.
    * Else if both tasks have the same deadline, the task with lesser tags is listed above the task with more tags.
    * Else if both tasks have the same number of tags, the task with a smaller lexicographical name is listed above the other.
  * Event
    * The task with the earlier `from` attribute is listed above the task with a later `from` attribute.
    * Else if both task have the same `from` attribute, the task with the earlier `to` attribute is listed above the task with later `to` attribute.
    * Else if both task have the same `to` attribute, the task with lesser tags is listed above the task with more tags.
    * Else if both tasks have the same number of tags, the task with a smaller lexicgraphical name is listed above the other.

Pros: Neater and more intuitive
Cons: Will have to scroll down to see the order for Events if there are too many SimpleTasks.

#### Option 2:

Same as above, but:
* Event is listed above SimpleTask and Deadline.
* Deadline is  listed below Event and above SimpleTask.
* SimpleTask is listed below Deadline and Event.

Pros: Able to see the Events happening close to date.
Cons: Have to scroll down to see SimpleTasks.

### 3.10 Alert Feature
The `alert` feature is a new feature that when called will display tasks which fall within the specified **alert window**.
You can find the specific implementation in the `AlertCommandParser` class and the `AlertCommand` class.
:bulb: Note: The addition of the `alert` feature includes a new attribute to all tasks `alertWindow`.

Given below is an example usage scenario and how the find mechanism behaves.

Step 1. The user inputs a `alert` command with parameter `48`. The parser recognises the command word and calls the AlertCommandParser.

Step 2. The `AlertCommandParser` processes the parameter and notices it is not empty.

Step 3. `AlertCommandParser` calls `AlertCommand` with the timeframe specified.

Step 4. `AlertCommand` is executed and model filters the `AlertTaskList` with the predicate `TaskWithinTimelinePredicate(timeframe)`.

Step 5. The result of the filtered list is passed back to the UI.

The following sequence diagram summarizes what happens in this example usage scenario:

![SortCommandSequenceDiagram](images/AlertSequenceDiagram.png)

The following activity diagram summarizes what happens when a user executes a new `alert` command:

![SortCommandSequenceDiagram](images/AlertActivityDiagram.png)


### 3.11 Schedule Feature

`schedule` displays a planned daily schedule according to the tasks currently stored. It takes in a compulsory input, `D/SHORTDATE`, and an optional input, `E/EFFORT`.

#### 3.11.1 How it Works
Entering `schedule D/SHORTDATE E/EFFORT` generates a new 30-day plan for users based on their intended `E/EFFORT`, and display a list of tasks to be done on `D/SHORTDATE`.
Entering `schedule D/SHORTDATE` displays a list of tasks to be done on `D/SHORTDATE` based on the previously generated plan.

#### 3.11.2 Generating a New Plan
When the schedule command is ran with an `E/EFFORT` flag, an internal planning algorithm is run, and all tasks will be allocated to a 30-day plan starting from the day the command is run.
As much as possible, tasks allocated to a day should not exceed the intended `E/EFFORT` level indicated by users.
However, if the need arises (as specified below), the algorithm allows the effort required for a particular day to exceed the user preferred `E/EFFORT` level.

The algorithm allocates tasks as such:

Step 1: Allocate all events to the day(s) it is supposed to be happening. Events will be allocated, even if the effort required exceeds the user preferred effort.
<details>

<summary>More about scheduling for Events</summary>

After getting a list of _Events_ from all tasks within _TaskBook_, a scheduling algorithm for events will be run. The following diagram shows its behaviour.

![Event Scheduling Example](images/EventAllocation.png)

**Some Rules**
* Events are allocated to the date they are scheduled to happen, regardless of time.
* Effort for event is added every day the event is scheduled to happen

**Allocation Example**
* Event A is allocated every day from 5 Mar 2023 to 2 June 2023, and its effort count of 10 is added to each of the 4 days.
* Event B is allocated to 31 May 2023, and its effort count of 5 is added to the existing effort count of 10 (from event A).
* Event C is allocated to 1 Jun 2023 and 2 Jun 2023 even though this means that the workload allocated to those days (28 units of effort) are greater than the workload user planned (20 units of effort).
* No event is scheduled to occur on 3 Jun 2023. It is left empty
</details>


Step 2: Allocate all deadlines to the first free day before it is due (exclusive of due date), as we assume that it is better to complete a time-sensitive task as soon as possible. If it is not possible to find a free day, the algorithm will allocate task to a day before deadline with the least amount of work allocated (in terms of effort). If multiple of such days exist, the algorithm chooses the first of such days.
<details>

<summary>More about scheduling for Deadlines</summary>

After getting a list of _Deadlines_ from all tasks within _TaskBook_, a scheduling algorithm for events will be run. The following diagram shows its behaviour.

![Deadline Scheduling Example](images/DeadlineAllocation.png)

**Some Rules**
* Overdue deadlines still in the TaskBook is not considered in the algorithm
* Deadlines are allocated to one of the days before the deadline
* If there are multiple free days, Deadline will be added to the earliest free day
* If there are no free days before the deadline, Deadline will be added to the least busy day (in terms of effort)

**Allocation Example**
* Deadline D is due on 31 May 2023, so the only date it can be allocated to is 30 May 2023. Thus, it is allocated to 30 May 2023 even though this means that the workload for 30 May is greater than the desired workload.
* Deadline E is allocated to 31 May 2023 because there are no free dates (30 May and 31 May) before 1 Jun 2023. Among the two possible dates, 31 May has a lower current workload. Thus, it is allocated to 31 May.
* Deadline F is allocated to 30 May 2023 as adding task to any dates before the deadline will result in exceeding the desired workload, and it is the date with the lowest workload among all possible dates.
</details>


Step 3: Allocate all SimpleTasks in descending order of effort required. As we assume that SimpleTasks are not time-sensitive, the algorithm allocates each task to the most busy free day (greedy approach). If such a day is not available, the algorithm will allocate the task to a day with the least amount of work allocated (in terms of effort). If multiple of such days exist, the algorithm chooses the first of such days.

<details>

<summary>More about scheduling for Simple Tasks</summary>

After getting a list of _Simple Tasks_ from all tasks within _TaskBook_, a scheduling algorithm for simple tasks will be run. The following diagram shows its behaviour.

![Simple Task Scheduling Example](images/SimpleTaskAllocation.png)

**Some Rules**
* SimpleTasks will be allocated to the most busy free day, which is a day with highest current workload where adding a simple task does not result in workload exceeding intended workload. If multiple of such days are available, allocate to the first of such days.
* If there are no such days, assigned workload will be allowed to exceed intended workload. Algorithm allocates simple task to a day with the lowest current workload.
* SimpleTasks are allocated in descending order of effort.
* SimpleTasks are assumed to be non-time-sensitive

**Allocation Example**
* Task I is the first task to be allocated because it has the highest effort required. It will be allocated to 3 Jun 2023 because there are 2 free days (3 Jun and 4 Jun) with the same current workload, and 3 Jun is before 4 Jun.
* Task H is the second to be allocated since it has the next highest effort required. It will be allocated to 4 Jun 2023 because it is the only free day, such that adding task H does not result in exceeding the desired workload.
* Task G is then allocated to 3 Jun. Among the 2 days that Task G can be added to without exceeding desired workload (3 Jun and 4 Jun), 3 Jun has a higher workload. Thus, task G will be allocated to 3 Jun.
</details>


Given below is an example usage scenario for viewing a generated plan and how the schedule command behaves at each step:

Step 1. The user launches the application.

Step 2. The application displays a list of tasks (that can also be empty).

Step 2. The user executes `schedule D/TODAY'S DATE` command to sort the list.

Step 3. The sequence diagram below shows how the sort operation works:

![ScheduleCommandNoPlanSequenceDiagram](images/ScheduleCommandNoPlanSequenceDiagram.png)

Given below is an example usage scenario for generating and viewing a plan and how the schedule command behaves at each step:

Step 1. The user launches the application.

Step 2. The application displays a list of tasks (that can also be empty).

Step 2. The user executes `schedule D/TODAY'S DATE E/EFFORT` command to sort the list.

Step 3. The sequence diagram below shows how the sort operation works:

![ScheduleCommandHavePlanSequenceDiagram](images/ScheduleCommandHavePlanSequenceDiagram.png)

The following activity diagram summarizes what happens when a user executes a new command:

![ScheduleCommandActivityDiagram](images/ScheduleCommandActivityDiagram.png)

### 3.12 Tag color codes

Color coded tags are a new feature aimed at providing a better User experience such that users are able to identify similar tags easily.

It is important to note that there are currently only 20 colors available and colors are assigned by the program (within TaskCard.java) and not the user.

The color is decided by calculating the hashcode of the tag's name then module 20 (the current size of the available colors).

The color of this tag is then decided by looking up the result in an array of colors and taking its corresponding color.

--------------------------------------------------------------------------------------------------------------------

## 4. **Documentation, logging, testing, configuration, dev-ops**

**This section contains some links to the guides that are used when developing Clock-Work. You are highly encouraged to adhere to these guides when working on Clock-Work.**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## 5. **Appendix: Requirements**

**This section documents project requirements written by the Clock-Work development team.**

### Product scope

**Target user profile**:

* needs to manage a significant number of assignments/tasks.
* prefer desktop apps over other types (eg. mobile apps)
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps
* gains familiarity with specific command input formats easily

**Value proposition**: manage tasks faster than a typical mouse/GUI driven app and has convenient features bundled in.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​         | I want to …​                                  | So that I can…​                                 |
|----------|-----------------|-----------------------------------------------|-------------------------------------------------|
| `* * *`  | user            | add a task                                    |                                                 |
| `* * *`  | user            | delete a task                                 | delete a task that I erroneously added          |
| `* * *`  | user            | find a task                                   | perform queries more efficiently                |
| `* * *`  | user            | view my tasks                                 | plan my time more efficiently                   |
| `* *`    | user            | sort my tasks by deadline                     | So that I can see which tasks are due sooner    |
| `* *`    | fickle user     | edit a task                                   | correct mistakes without deleting a task        |
| `* *`    | user            | have a project folder                         | look at only my projects at a glance            |
| `* *`    | organized user  | add tags to tasks                             | organize them better                            |
| `* *`    | indecisive user | know what tasks to prioritise                 | not have to dedicate time to thinking about it  |
| `* *`    | user            | keep track of how many tasks I have completed | keep track of my projects                       |
| `* *`    | forgetful user  | be prompted when assignments are due          | don't miss deadlines                            |
| `*`      | organized user  | organize tasks by modules/subjects            | easily find my tasks                            |
| `*`      | user            | see motivational quotes                       | be motivated to do my tasks                     |
| `*`      | lazy user       | automate typing in repetitive tasks           | save time entering similar entries              |
| `*`      | lazy user       | have a schedule planned out for me            | not have to dedicate time to administration     |
| `*`      | busy user       | add colour codes                              | easily identify my tasks at a glance            |
| `*`      | forgetful user  | view my previously generated schedule         | continue working on what i was doing previously |


*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `Clock-Work` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Add a task**

**MSS**

1. User requests to add a task
2. TaskBook adds a task based on parameters supplied.

    Use case ends.

**Extensions**

* 1a. The input syntax is invalid

    * 1a1. TaskBook shows an error message.

      Use case resumes at step 1.

* 1b. The supplied name already exists in the TaskBook.

    * 3a2. TaskBook shows an error message

      Use case resumes at step 1.


**Use case: Delete a task**

**MSS**

1.  User requests to list tasks
2.  TaskBook shows a list of tasks
3.  User requests to delete a specific task in the list
4.  TaskBook deletes the task

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. TaskBook shows an error message.

      Use case resumes at step 2.


**Use case: Edit a task**

**MSS**

1.  User requests to list tasks
2.  TaskBook shows a list of tasks
3.  User requests to edit a specific task in the list
4.  TaskBook edits the task based on the parameters supplied.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

  * 3a1. TaskBook shows an error message.

    Use case resumes at step 2.

* 3b. The input syntax is invalid

  * 3a2. TaskBook shows an error message

    Use case resumes at step 2.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 tasks without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others

--------------------------------------------------------------------------------------------------------------------

## 6. **Appendix: Instructions for manual testing**

**This section contains instructions to test Clock-Work manually. Here are some ways you can test Clock-Work.**

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a task

1. Deleting a task while all tasks are being shown

   1. Prerequisites: List all tasks using the `list` command. Multiple tasks in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No task is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
