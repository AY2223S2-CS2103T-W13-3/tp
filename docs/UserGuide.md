---
layout: page
title: User Guide
---

Clock-Work is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Clock-Work can get your assignment management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `clockwork.jar` from [here](https://github.com/AY2223S2-CS2103T-W13-3/tp/releases). (Currently not available)

1. Copy the file to the folder you want to use as the _home folder_ for your Clock-Work.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar clockwork.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/Meeting d/CSXXXX project meeting` : Adds a task named Meeting to task book.

   * `delete 3` : Deletes the 3rd task shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>
* First word is assumed to be a command word (add/delete/list/find).
* Each task must have a corresponding description.
* Words in `ALL CAPS` are the parameters to be supplied by the user.<br>

* Items in square brackets are optional.<br>
  e.g `t/TASK [tag/TAG]` can be used as `t/Read Book tag/relax` or as `t/Read Book`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[tag/TAG]…​` can be used as ` ` (i.e. 0 times), `tag/important`, `tag/important t/urgent` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `t/TASK d/DESCRIPTION`, `d/DESCRIPTION t/TASK` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `d/do it fast d/do it slow`, only `d/do it slow` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a task: `add`

Adds a task to the address book. There are 3 types of tasks. `SimpleTask`, `Deadline` and `Event`.
For `Deadline` and `Event` date(s) are required. Dates should be in the format `YYYY-MM-DD HHmm`.
By default, effort level is 24.

Format:

- SimpleTask: `add n/TASKNAME d/DESCRIPTION [t/TAGS]…​ [E/EFFORT]`

- Deadline: `add n/TASKNAME d/DESCRIPTION [t/TAGS]…​ [E/EFFORT] D/DEADLINE`

- Event: `add n/TASKNAME d/DESCRIPTION [t/TAGS]…​ [E/EFFORT] F/FROMDATE T/TODATE`

You can add multiple tasks with the same parameters except for name with this command:`add n/TASKNAME1 n/TASKNAME2 d/DESCRIPTION [t/TAGS]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can add multiple Events and Deadlines as well! However, they have to share the same timings.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A task can have any number of tags (including 0)
</div>

Examples:
* `add n/Read Book d/Make sure to take notes t/Leisure`
* `add n/Return Book d/NUS library t/Urgent D/2023-01-01 1800`

### Listing all tasks : `list`

Shows a list of all tasks in the address book.

Format: `list`

### Editing a task : `edit`

Edits an existing task in the address book.

Format: `edit INDEX [n/TASKNAME] [d/DESCRIPTION] [E/EFFORT] [t/TAG]…​`

* Edits the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the task will be removed i.e adding of tags is not cumulative.
* You can remove all the task’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 t/CS2102 t/URGENT` Edits the tags of the first task to now be `CS2102` and `URGENT`
*  `edit 2 n/CS2102 Finals t/` Edits the name of the 2nd task to be `CS2102 Finals` and clears all existing tags.

### Locating tasks by name: `find`

Find tasks whose attribute best match the user input string.

Format: `find n/NAME` OR `find d/DESCRIPTION` OR `find t/TAG...`

* The search is case-insensitive. e.g `book` will match `Book`
* Use only 1 attribute at a time.
* Substrings will be matched e.g. `book` will match `Books`
* For names and descriptions, you may use the `all/` prefix to search for a task that contains all of your inputs
  * e.g. `find all/ n/do n/homework` will match a task with a name called "do math homework.
* For tags, if you do not specify the `all/` prefix, as long as one tag matches with one of the tags you are searching for, it will be considered matched.
However, adding `all/` means that a task which contains all your tag inputs will be displayed.
  * e.g. `find t/very urgent t/important` will match with tags `t/very very urgent t/math t/hard` since it has `very urgent`.
  * e.g. `find all/ t/very urgent t/important` will match with tags `t/very urgent t/important` since it has both tags.
* For deadlines, you can only use a valid date(without the time input) such as `2023-03-10` to search for deadlines on that day.
  * e.g. `find D/2023-03-10` will give you all the deadlines on 2023-01-01.
* For events, you may either use `F/` or `T/` prefix(without the time input as well) to search for event that starts or ends on a certain date.
  * e.g. `find F/2023-03-10` will give you all the events starting from 2023-03-10.
  * e.g. `find T/2023-03-10` will give you all the event ending on 2023-03-10.

Examples:
* `find n/book` returns `read book` and `return books`

### Deleting a task : `delete`

Deletes the specified task from the address book.

Format: `delete INDEX(S)`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​
* If multiple indices are entered, they must be written in ascending order.
* The command will reject all specified indices if one of them is invalid.

Examples:
* `list` followed by `delete 2` deletes the 2nd task in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st task in the results of the `find` command.

### Getting statistics : `stats`

Prints the top 10 tags (if applicable) and its corresponding number of occurrences in the tasks.

Format: `stats`

### Sorting tasks : `sort`

Sorts the list using the following format:

    1. SimpleTask
        1.1. Sort by tags size.
        1.2. If tags size are the same, sort by name.
    2. Deadline
        2.1. Sort by earliest deadline.
        2.2. If deadlines are the same, sort by tags size.
        2.3. If tags size are the same, sort by name.
    3. Event
        3.1. Sort by earliest "from" date.
        3.2. If "from" date is the same, sort by earliest "to" date.
        3.3. If "to" date is the same, sort by tags size.
        3.4. If tags size are the same, sort by name.

Format: `sort`

### Get alerts : `alert [ALERT WINDOW]`

Displays in another window the tasks that fall within the window specified. If not supplied, assumed to be 24 hours.
On opening of app, the alert window will open to show tasks which have deadlines within the latest window specified.
Have to specify `ALERT WINDOW` in hours and only integers.

Examples:
- `alert` followed by `48` will show the alert window with all tasks which end within 48 hours.
- `alert` alone will show the alert window with all tasks which end within 24 hours.

### Plan your month : `plan EFFORT`

Automatically plans your month depending on your ideal `EFFORT` level per day. The planner will make an effort to
keep as close to your effort level as possible, however, if it has to overload to complete tasks on time it will.
Overloading will also be spread out as evenly as possible.

In order of priority, the planner will prioritise `Events`, then `Deadlines`, then `SimpleTasks`

Examples:
- `plan 5` will plan your month according to an ideal effort level of 5 per day.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                                                                    |
|------------|---------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/TASKNAME d/DESCRIPTION [t/TAGS]…​ [E/EFFORT]` <br> e.g., `add n/read book d/Lord of the Flies t/leisure E/5` |
| **Clear**  | `clear`                                                                                                             |
| **Delete** | `delete INDEX(S)`<br> e.g., `delete 3`                                                                              |
| **Edit**   | `edit INDEX [n/TASKNAME] [d/DESCRIPTION] [E/EFFORT] [t/TAG]…​`<br> e.g.,`edit 2 n/study d/CS2103T`                  |
| **Find**   | `find n/NAME` or `find d/DESCRIPTION`<br> e.g., `find n/read book`                                                  |
| **List**   | `list`                                                                                                              |
| **Help**   | `help`                                                                                                              |
| **Stats**  | `stats`                                                                                                             |
| **sort**   | `sort`                                                                                                              |
| **alert**  | `alert ALERT_WINDOW`                                                                                                |
| **plan**   | `plan EFFORT`                                                                                                       |
