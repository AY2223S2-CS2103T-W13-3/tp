---
layout: page
title: User Guide
---

:alarm_clock: Tick-tock. Tick-tock. Time is ticking. :alarm_clock:

:scream: Are you stressed about the lab assignment you can't seem to make any progress with? Worried about the endless list of assignments to do? :scream:

:bellhop_bell: If a quick organisation tool is what you need to get your life together, you're at the right place! :bellhop_bell:

Introducing, Clock-Work!

Clock-Work is a **desktop application for managing tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Clock-Work can get your assignment management tasks done faster than traditional GUI apps.

* Table of Contents
  * [1. Quick start](#1-quick-start)
  * [2. Features and commands](#2-features-and-commands)
    * [2.1 help](#21-viewing-help--help)
    * [2.2 add](#22-adding-a-task--add)
    * [2.3 list](#23-listing-all-tasks--list)
    * [2.4 edit](#24-editing-a-task--edit)
    * [2.5 find](#25-locating-tasks-by-name--find)
    * [2.6 delete](#26-deleting-a-task--delete)
    * [2.7 stats](#27-getting-statistics--stats)
    * [2.8 sort](#28-sorting-tasks--sort)
    * [2.9 alert](#29-get-alerts--alert-alert-window)
    * [2.10 schedule](#210-schedule-of-the-day--schedule-dshortdate-eeffort)
    * [2.11 subsection](#211-adding-a-subsection-to-a-task--subsection)
    * [2.11 remove-subsection](#212-deleting-a-subsection-from-a-task--remove-subsection)
  * [3. Storage](#3-storage)
    * [3.1 saving the data](#31-saving-the-data)
    * [3.2 editing the data](#32-editing-the-data-file)
  * [4. FAQ](#4-faq)
  * [5. Command summary](#5-command-summary)

--------------------------------------------------------------------------------------------------------------------

## 1. Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `clockwork.jar` from [here](https://github.com/AY2223S2-CS2103T-W13-3/tp/releases). (Currently not available)

3. Copy the file to the folder you want to use as the _home folder_ for your Clock-Work.

4. Open a command terminal (e.g. Ubuntu)

5. Navigate to the directory with `clockwork.jar` file downloaded in (Downloads folder in most cases).

6. Use the command `java -jar clockwork.jar` to run the application or double-click on the jar file.

7. A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

8. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/Meeting d/CSXXXX project meeting` : Adds a task named Meeting to task book.

   * `delete 3` : Deletes the 3rd task shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

9. Refer to the [Features and commands](#2-features-and-commands) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## 2. Features and Commands

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>
* First word is assumed to be a command word (add/delete/list/find).

* A Task can be: 
  * A simpleTask, which does not have a Date attached to it. ([What is a Date?](#q2-what-is-a-valid-date))
  * A deadline, which has a Date known as `deadline`.
  * An event, which has 2 Dates known as a `from` and a `to`.

* A task can have the following additional **Optional** fields attached to it:
  * Tags
  * Description 
  * Effort level
  * Alert window
  * Subsection
  
* Words in `ALL CAPS` are the parameters to be supplied by the user.<br>

* Any fields in square brackets are optional.<br>

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[tag/TAG]…​` can be used as ` ` (i.e. 0 times), `tag/important`, `tag/important t/urgent` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `t/Task A d/A's sample description`, `d/A's sample description t/Task A` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `d/do it fast d/do it slow`, only `d/do it slow` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### 2.1 Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### 2.2 Adding a task: `add`

Adds a task to the address book. There are 3 types of tasks. `SimpleTask`, `Deadline` and `Event`.
For `Deadline` and `Event` `Date` field(s) are required.

`Date` should be in the format `YYYY-MM-DD HHMM`. ([What is a valid Date?](#q2-what-is-a-valid-date))

By default, effort level is 24. ([What is an effort level?](#q3-what-is-an-effort-level))

A Task must have a description.([What is a valid Description?](#q4-what-is-a-valid-description))

Format:

* SimpleTask: `add n/TASKNAME [d/DESCRIPTION] [t/TAGS]…​ [E/EFFORT]

* Deadline: `add n/TASKNAME D/DEADLINE [d/DESCRIPTION] [t/TAGS]…​ [E/EFFORT]

* Event: `add n/TASKNAME F/FROMDATE T/TODATE [d/DESCRIPTION] [t/TAGS]…​ [E/EFFORT]

Examples:
* `add n/Read Book d/Make sure to take notes t/Leisure`
* `add n/Return Book d/NUS library t/Urgent D/2023-01-01 1800`

You can add multiple tasks with the same parameters except for name with this command:`add n/TASKNAME1 n/TASKNAME2 d/DESCRIPTION [t/TAGS]…​`

:bulb: **PRO TIP**: You can add multiple Events and Deadlines as well! However, they have to share the same timings. Use this to add tasks that occur at the same time!

:bulb: **PRO TIP**: Parameters that comes after the command can be in any order!

:bulb: **PRO TIP**: A task can have any number of tags (including 0)

:warning: You are unable to add any tasks (simpleTask, Deadline, Event) of the same name.

:warning: Adding multiple tags of the same tag name will only result in one tag! 

e.g. `add n/SampleTask t/CS2102 t/CS2102` will only register t/CS2102 once!

:warning: There are reserved CLI syntax like `n/`, `d/`, `t/`, `D/`, `F/`, `T/`, `all/`, `E/`, `I/`, `D/`. Input fields will have unexpected behaviour when it contains these characters.

e.g. `add n/Sample d/Why can't I add n/?`


### 2.3 Listing all tasks : `list`

Shows a list of all tasks in the address book.

Format: `list`

:bulb: PRO TIP: Some commands (e.g. find) will trigger the UI to display a subset of tasks. Use `list` to return back to the original list.

### 2.4 Editing a task : `edit`

Edits an existing task in the address book.

Format: `edit INDEX [n/TASKNAME] [d/DESCRIPTION] [E/EFFORT]…​ [t/TAG]…​`

* Edits the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the task will be removed i.e adding of tags is not cumulative.
* You can remove all the task’s tags by typing `t/` without specifying any tags after it.
* You can remove a description with `d/` without specifying any tags after it.

Examples:
*  `edit 1 t/CS2102 t/URGENT` Edits the tags of the first task to now be `CS2102` and `URGENT`
*  `edit 2 n/CS2102 Finals t/` Edits the name of the 2nd task to be `CS2102 Finals` and clears all existing tags.

:warning: When editing the tags of a task, multiple tags of the same name is ignored! 

e.g. `edit 1 t/CS2102 t/CS2102` will only register t/CS2102 once!

### 2.5 Locating tasks by name: `find`

Find tasks whose attribute best match the user input string.

Format: `find n/NAME` OR `find d/DESCRIPTION` OR `find t/TAG...`

* The search is case-insensitive. e.g `book` will match `Book`
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

:warning: When searching for a description `find d/Description`, Tasks without user's input description will not show up!

e.g. `add n/Homework` and `add n/Project d/No Description` followed by `find d/No Description` will return only a list with the latter task.

### 2.6 Deleting a task : `delete`

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

### 2.7 Getting statistics : `stats`

Prints the top 10 tags (if applicable) and its corresponding number of occurrences in the tasks.

Format: `stats`

### 2.8 Sorting tasks : `sort`

Sorts the list using the following format:

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


Format: `sort`

### 2.9 Get alerts : `alert [ALERT WINDOW]`

Displays in another window the tasks that fall within the window specified. If not supplied, assumed to be 24 hours.
On opening of app, the alert window will open to show tasks which have deadlines within the latest window specified.
Have to specify `ALERT WINDOW` in hours and only integers.

Examples:
- `alert` followed by `48` will show the alert window with all tasks which end within 48 hours.
- `alert` alone will show the alert window with all tasks which end within 24 hours.

### 2.10 Schedule of the day : `schedule D/SHORTDATE [E/EFFORT]`

#### 2.10.1 How it Works
Entering `schedule D/SHORTDATE E/EFFORT` generates a new 30-day plan for users based on their intended `E/EFFORT`, and display a list of tasks to be done on `D/SHORTDATE`.
Entering `schedule D/SHORTDATE` displays a list of tasks to be done on `D/SHORTDATE` based on the previously generated plan.

#### 2.10.2 Generating a New Plan
When the schedule command is ran with an `E/EFFORT` flag, an internal planning algorithm is run, and all tasks will be allocated to a 30-day plan starting from the day the command is run.
As much as possible, tasks allocated to a day should not exceed the intended `E/EFFORT` level indicated by users.
However, if the need arises (as specified below), the algorithm allows the effort required for a particular day to exceed the user preferred `E/EFFORT` level.

The algorithm allocates tasks as such:
1. Allocate all events to the day(s) it is supposed to be happening. Events will be allocated, even if the effort required exceeds the user preferred effort.

2. Allocate all deadlines to the first free day before it is due (exclusive of due date), as we assume that it is better to complete a time-sensitive task as soon as possible. If it is not possible to find a free day, the algorithm will allocate task to a day before deadline with the least amount of work allocated (in terms of effort). If multiple of such days exist, the algorithm chooses the first of such days.

3. Allocate all SimpleTasks in descending order of effort required. As we assume that SimpleTasks are not time-sensitive, the algorithm allocates each task to the most busy free day (greedy approach). If such a day is not available, the algorithm will allocate the task to a day with the least amount of work allocated (in terms of effort). If multiple of such days exist, the algorithm chooses the first of such days.

#### 2.10.3 Displaying Daily Plan
Daily Plans can be viewed by entering `schedule D/SHORTDATE` or `schedule D/SHORTDATE E/EFFORT`, with the former showing an old plan, and the latter showing a newly generated plan.
As plans are only valid for 30 days from the last time it was generated, users are encouraged to regenerate a plan with an `E/EFFORT` flag if they do not remember when they last generated their plan.
If no tasks are shown, it means that there are no tasks planned for that day. 
An error message that prompts users to re-generate a plan will be shown if the entered date is out of range (either too far in the future, or a date before the start date of generated plan).

:construction: Valid `D/SHORTDATE` are 30-days from the day plan was generated. Re-scheduling plans multiple times would not make an invalid date become valid.

Examples:
- Assuming today is 2023-03-26, `schedule D/2023-04-01 E/5` will plan a 30-day schedule starting from today (March 26, 2023) according to a desired effort level of 5, and display tasks planned for April 1.
- `schedule D/2023-04-02` will show tasks which should be completed on April 2, 2023, based on a previously generated schedule (which should be generated between 3 Mar 2023, and 2 Apr 2023).

### 2.11 Adding a Subsection to a Task : `subsection`

Adds a subsection to a task in the task book. The subsection added will appear as in the corresponding task's task card.
You can do so by entering `subsection index n/name d/description`, and the description prefix `d/` is optional.

Examples:
- Assuming your current task list has a task with index 1, `subsection 1 n/homework d/Math` will add a subsection to the main task at index 1 with a subsection with the name "homework" and description "Math".

### 2.12 Deleting a Subsection From a Task : `remove-subsection`

Deletes a subsection from a task, assuming you have one in the task. You can do so by entering `remove-subsection index I/subsection index`, where the first index is the main task's index, and the latter is the subsection's.

Examples:
- Assuming your current task list has a task with index 1, which in turn contains a subsection with index 1. You can delete it with `remove-subsection 1 I/1`. The newly updated task will be shown.

### Show your daily plans : `schedule DATE`

Displays tasks to be done on 'DATE' entered. Valid dates include 30 days from the last time plan command was ran.

Examples:
- `schedule 2023-03-25` will display all tasks which should be done on that day, provided the plan command was run
-  within the past 30 days of 25 March 2023.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### 2.13 Exiting the program : `exit`

Exits the program.

Format: `exit`

## 3. Storage

### 3.1 Saving the data

TaskBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### 3.2 Editing the data file

TaskBook data are saved as a JSON file `[JAR file location]/data/taskbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, taskBook will discard all data and start with an empty data file at the next run.
</div>

### 3.3 Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## 4. FAQ

### Q1 How do I transfer my data to another Computer?
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

### Q2 What is a valid Date?
**A**: A valid Date must be in the format of `YYYY-MM-DD HHMM` such as `2023-07-13 1800` (13 July 2023, 6PM). A valid Date must have a valid time. A minimum of 4 characters must be supplied and only a maximum of 4 character will be parsed
  e.g. `2023-07-13 180` is invalid and `2023-07-13 18000000000` is understood as `2023-07-13 1800`

### Q3 What is an effort level?
**A**: [_More details to come_]

### Q4 What is a valid Description?
**A**: A valid Description is a text input that has at least one character.


--------------------------------------------------------------------------------------------------------------------

## 5. Command summary

| Action     | Format, Examples                                                                                                   |
|------------|--------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/TASKNAME d/DESCRIPTION [t/TAGS]…​ [E/EFFORT]` <br> e.g., `add n/read book d/Lord of the Flies t/leisure E/5` |
| **Clear**  | `clear`                                                                                                            |
| **Delete** | `delete INDEX(S)`<br> e.g., `delete 3`                                                                             |
| **Edit**   | `edit INDEX [n/TASKNAME] [d/DESCRIPTION] [E/EFFORT] [t/TAG]…​`<br> e.g.,`edit 2 n/study d/CS2103T`                 |
| **Find**   | `find n/NAME` or `find d/DESCRIPTION`<br> e.g., `find n/read book`                                                 |
| **List**   | `list`                                                                                                             |
| **Help**   | `help`                                                                                                             |
| **Stats**  | `stats`                                                                                                            |
| **sort**   | `sort`                                                                                                             |
| **alert**  | `alert ALERT_WINDOW`                                                                                               |
| **plan**   | `plan EFFORT`                                                                                                      |
=======
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

--------------------------------------------------------------------------------------------------------------------
## 6. Glossary
1. Overload: When the sum of effort for all tasks allocated to a particular day exceeds the user-preferred daily effort level.
2. Free day: Allocating a task to this day will not result in overloading.
3. Short date: YYYY-MM-DD
4. Date: YYYY-MM-DD HHMM
