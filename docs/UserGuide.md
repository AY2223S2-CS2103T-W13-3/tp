---
layout: page
title: User Guide
---

:alarm_clock: Tick-tock. Tick-tock. Time is ticking. :alarm_clock:

:scream: Are you stressed about the programming lab assignment you can't seem to make any progress with? Worried about the endless list of assignments to do? :scream:

:bellhop_bell: If a quick organisation tool is what you need to get your life together, you're at the right place! :bellhop_bell:

Introducing, Clock-Work!

Clock-Work is a **desktop application for managing tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Clock-Work can get your assignment management tasks done faster than traditional GUI apps.

With basic commands like `add`, `delete`, `edit`, and special commands like `alert`, `find`, `schedule`, Clock-Work is your one-stop application to organise your busy Computing life :computer:.

**Get started with Clock-Work with this guide!**

This guide is meant for the average Computer Science student, who is interested in using Clock-Work.

Table of Contents
* [1. Quick start](#1-quick-start)
* [2. Introducing a Task](#2-a-task-in-clock-work)
* [3. Features and commands](#3-features-and-commands)
  * [3.1 add](#31-adding-a-task--add)
  * [3.2 edit](#32-editing-a-task--edit)
  * [3.3 delete](#33-deleting-a-task--delete)
  * [3.4 list](#34-listing-all-tasks--list)
  * [3.5 sort](#35-sorting-tasks--sort)
  * [3.6 find](#36-locating-tasks-by-name--find)
  * [3.7 stats](#37-getting-statistics--stats)
  * [3.8 alert](#38-get-alerts--alert-alert-window)
  * [3.9 schedule](#39-schedule-of-the-day--schedule-dshortdate-eeffort)
  * [3.10 subsection](#310-subsections)
    * [3.10.1 subsection](#3101-adding-a-subsection-to-a-task--subsection)
    * [3.10.2 remove-subsection](#3102-deleting-a-subsection-from-a-task--remove-subsection)
  * [3.11 help](#311-viewing-help--help)
  * [3.12 clear](#312-clearing-all-entries--clear)
  * [3.13 exit](#313-exiting-the-program--exit)
* [4. Storage](#4-storage)
  * [4.1 saving the data](#41-saving-the-data)
  * [4.2 editing the data](#42-editing-the-data-file)
* [5. FAQ](#5-faq)
* [6. Command summary](#6-command-summary)
* [7. Glossary](#7-glossary)

If you like our product and want to contribute to it, consider looking at the [Developer Guide](DeveloperGuide.md) instead.

--------------------------------------------------------------------------------------------------------------------
## 1. Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `clockwork.jar` from [here](https://github.com/AY2223S2-CS2103T-W13-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your Clock-Work.

4. Open a command terminal (e.g. Ubuntu)

5. Navigate to the directory with `clockwork.jar` file downloaded in (Downloads folder in most cases).

6. Enter the command `java -jar clockwork.jar` to run the application or double-click on the jar file.

7. A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

8. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/Meeting d/CSXXXX project meeting` : Adds a task named Meeting to task book.

   * `delete 3` : Deletes the 3rd task shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

9. Refer to the [Features and Commands](#2-features-and-commands) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
## 2. A Task in Clock-Work

The life of student is busy, but also vibrant and filled with a variety of activities. Clock-Work helps students achieve their maximum potential by supporting 3 types of tasks to cater to their varying needs.
They are: Simple Tasks, Deadlines, and Events. As their name suggests, a simple task is a time-insensitive task that can be done at any time, a deadline is a time-sensitive task which must be done before the specified deadline, and an event is a time-sensitive task that must be done during the specified period of time.

### Basic Information about Tasks
* A Task can be:
    * A <span style="color:#4285F4">simpleTask</span>, which does not have a _long date_ attached to it. ([What is a Long Date?](#q4-what-is-a-valid-long-date))
    * A <span style="color:#34A853">deadline</span>, which has a _long date_ known as `deadline`.
    * An <span style="color:#FBBC05">event</span>, which has 2 _long dates_ known as a `from` and a `to`.

* A task can have the following additional **Optional** fields attached to it:
    * Tags
    * Description
    * Effort level
    * Alert window
    * Subsection

This is what a task look like in Clock-Work.

![Sample Task](images/SampleTask.png)

This is what each component mean:

| Component | Meaning | How to use it | Usage Specifications                                                                       |
|--| --------- | ------------- |--------------------------------------------------------------------------------------------|
| [Name](#q2-what-is-a-valid-name) | Name of task | Use it to identify task | Must be unique, Case-insensitive                                                           |
| [Tag](#q3-what-is-a-valid-tag) | Labels / Folders | Use it to group related tasks | Must be a single word, Colour randomly generated, limit to 45 characters                   |
| [Time](#q4-what-is-a-valid-long-date) | Type of task | Keep track of time-related information here | A simple task has no specified time, a deadline has 1 long date, an event has 2 long dates |
| [Effort](#q7-what-is-an-effort-level) | Estimated amount of work to complete task | Set realistic effort estimates to help with scheduling | Default value of 24                                                                        |
| [Description](#q8-what-is-a-valid-description) | Good-to-know | Useful things to remember | Text with at least 1 character                                                             |
| [Subsection](#q9-what-is-a-valid-subsection) | Smaller tasks to complete | Split a larger task into more manageable pieces | Has its own name and description                                                           |


--------------------------------------------------------------------------------------------------------------------

## 3. Features and Commands

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

### Conventions used in this document

For easy reference and understanding, this guide utilizes some special formatting of text. The conventions used are as follows:


| Convention | Meaning | Sample Usage |
| --- |-----------------------------------------------------------------------------|--------------|
| `lowercaseword` | Valid Clock-Work command words. They may or may not be used with additional inputs. | `plan`       |
| `UPPERCASEWORD` | Compulsory inputs which must be supplied by user in order for the command to work. | `n/NAME`     |
| `[UPPERCASEWORD]` | Optional inputs which user can supply for a command.                        |  `[d/DESCRIPTION]` |
| `…`​ | Inputs can be used multiple times, including zero times | `[t/TAG]…​` |


| Emoji              | Meaning |
|--------------------| --- |
| :man_technologist: | Short messages from us to you |
| :bulb:             | Tips on how to enhance your user experience |
| :warning:          | Important things to note to avoid unintended behaviour |

### Inputs into Clock-Work

Clock-Work provides some flexibility regarding the types of inputs it accepts. These are some information about inputting commands you may find useful.

1. First word is assumed to be a command word (add/delete/list/find).

2. Parameters can be in any order.<br>
  e.g. if the command specifies `n/Task A d/A's sample description`, `d/A's sample description n/Task A` is also acceptable.

3. If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `d/do it fast d/do it slow`, only `d/do it slow` will be taken.

4. Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### 3.1 Adding a task : `add`

:man_technologist: **Congratulations on reaching this part of the user guide. You should have Clock-Work started and running at this point. You are now ready to add your first task!** :tada:

You can create different types of tasks depending on the input parameters you enter.

Format:

* <span style="color:#4285F4">SimpleTask</span>: `add n/TASKNAME [d/DESCRIPTION] [t/TAGS]…​ [E/EFFORT]`

* <span style="color:#34A853">Deadline</span>: `add n/TASKNAME D/DEADLINE [d/DESCRIPTION] [t/TAGS]…​ [E/EFFORT]`

* <span style="color:#FBBC05">Event</span>: `add n/TASKNAME F/FROMDATE T/TODATE [d/DESCRIPTION] [t/TAGS]…​ [E/EFFORT]`

Examples:
* `add n/Read Book d/Make sure to take notes t/Leisure`
* `add n/Return Book d/NUS library t/Urgent D/2023-01-01 1800`


:warning: You are unable to add any tasks (<span style="color:#4285F4">simpleTask</span>, <span style="color:#34A853">Deadline</span>, <span style="color:#FBBC05">Event</span>) of the same name.

:warning: Adding multiple **Tags** of the same tag name will only result in one tag!

e.g. `add n/SampleTask t/CS2102 t/CS2102` will only register t/CS2102 once!

:warning: Adding more than 1 **Description** or **Effort** is accepted but only the last instance will be processed.

:warning: There are reserved CLI syntax like `n/`, `d/`, `t/`, `D/`, `F/`, `T/`, `all/`, `E/`, `I/`. Input fields will have unexpected behaviour when it contains these characters.

e.g. `add n/Sample d/Why can't I add n/?`

<br>

:man_technologist: **If you are a pro user, you may consider adding multiple tasks at once with this hack!**


Add multiple tasks with the same parameters except for name with this command:`add n/TASKNAME1 n/TASKNAME2 d/DESCRIPTION [t/TAGS]…​`


:bulb: **PRO TIP**: You can add multiple <span style="color:#FBBC05">Events</span> and <span style="color:#34A853">Deadlines</span> as well! However, they have to share the same timings. Use this to add tasks that occur at the same time!

:bulb: **PRO TIP**: Parameters that comes after the command can be in any order!

:bulb: **PRO TIP**: A task can have any number of tags (including 0). Each tag will be automatically assigned a color. [Why are my different tags same color?](#q5-why-does-two-different-tags-have-the-same-tag-color)


### 3.2 Editing a task : `edit`

:man_technologist: **Hmm, have a task that you want to modify but find that it takes too much effort to create and delete a task? Try this command.**

Edits an existing task in the task book.

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


### 3.3 Deleting a task : `delete`

:man_technologist: **Congratulations! You have completed a task. Now, you can remove it by using this command.**

Deletes the specified task from the task  book.

Format: `delete INDEX [INDEX]…​`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​
* If multiple indices are entered, they must be written in ascending order.
* The command will reject all specified indices if one of them is invalid.

Examples:
* `list` followed by `delete 2` deletes the 2nd task in the task book.
* `find Betsy` followed by `delete 1` deletes the 1st task in the results of the `find` command.


### 3.4 Listing all tasks : `list`

:man_technologist: **Want to have an overview of all your tasks? Try this!**

Shows a list of all tasks in the task book.

Format: `list`

:bulb: PRO TIP: Some commands (e.g. find) will trigger the UI to display a subset of tasks. Use `list` to return back to the original list.


### 3.5 Sorting tasks : `sort`

:man_technologist: **Is the displayed list too messy for your liking? Sort is here to help.**

Sorts the list using the following format:

* <span style="color:#4285F4">SimpleTask</span> is listed above Deadline and Event.
* <span style="color:#34A853">Deadline</span> is  listed below SimpleTask and above Event.
* <span style="color:#FBBC05">Event</span> is  listed below SimpleTask and Event.
* When comparing 2 tasks of the same class:
    * <span style="color:#4285F4">SimpleTask</span>
        * The task with lesser tags is listed above the task with more tags.
        * Else if both tasks have the same number of tags, the task with a smaller lexicographical name is listed above the other.
    * <span style="color:#34A853">Deadline</span>
        * The task with the earlier deadline is listed above the task with later deadline.
        * Else if both tasks have the same deadline, the task with lesser tags is listed above the task with more tags.
        * Else if both tasks have the same number of tags, the task with a smaller lexicographical name is listed above the other.
    * <span style="color:#FBBC05">Event</span>
        * The task with the earlier `from` attribute is listed above the task with a later `from` attribute.
        * Else if both task have the same `from` attribute, the task with the earlier `to` attribute is listed above the task with later `to` attribute.
        * Else if both task have the same `to` attribute, the task with lesser tags is listed above the task with more tags.
        * Else if both tasks have the same number of tags, the task with a smaller lexicgraphical name is listed above the other.


Format: `sort`


### 3.6 Locating tasks by name : `find`

:man_technologist: **Oops! Are you unable to locate a task? Try find.**

Find **Tasks** whose attribute best match the user input string.

Format: `find n/NAME` OR `find d/DESCRIPTION` OR `find t/TAG...`

* The search is **case-insensitive**. e.g `book` will match `Book`
* **Substrings** will be matched e.g. `book` will match `Books`
* For <span style="color:#34A853">Deadline</span>, you can only use a valid date(without the time input) such as `2023-03-10` to search for deadlines on that day.
    * e.g. `find D/2023-03-10` will give you all the deadlines on 2023-01-01.
* For <span style="color:#FBBC05">Event</span>, you may either use `F/` or `T/` prefix(without the time input as well) to search for event that starts or ends on a certain date.
    * e.g. `find F/2023-03-10` will give you all the events starting from 2023-03-10.
    * e.g. `find T/2023-03-10` will give you all the event ending on 2023-03-10.

Examples:
* `find n/book` finds **Tasks** with name `read book` and `return books`

:bulb: Here are some **PRO TIPS**:
* For names and descriptions, you may use the `all/` prefix to search for a task that contains all of your inputs. Repeated inputs are only considered once.
    * e.g. `find all/ n/do n/homework` will match a task with a name called "do math homework.
    * e.g. `find all/ n/try n/try n/lab` is interpreted as `find all/ n/try n/lab`
* For tags, if you do not specify the `all/` prefix, as long as one tag matches with one of the tags you are searching for, it will be considered matched.
  However, adding `all/` means that a task which contains all your tag inputs will be displayed.
    * e.g. `find t/very urgent t/important` will match with tags `t/very very urgent t/math t/hard` since it has `very urgent`.
    * e.g. `find all/ t/very urgent t/important` will match with tags `t/very urgent t/important` since it has both tags.

:warning: When searching for a description `find d/DESCRIPTION`, Tasks without user's input description will not show up!

e.g. `add n/Homework` and `add n/Project d/No Description` followed by `find d/No Description` will return a list with only the latter task.


### 3.7 Getting statistics : `stats`

:man_technologist: **Some statistics will do no harm. Stats is here to help you visualise your tasks in numbers!**

Prints the top 10 tags (if applicable) and its corresponding number of occurrences in the tasks.

Format: `stats`


### 3.8 Get alerts : `alert [ALERT WINDOW]`

:man_technologist: **Beep! Beep! Well, not really, but alert is here to warn you about tasks which are due soon!**

Displays in the tasks that fall within the window specified on the right panel. If not supplied, assumed to be 24 hours.
On opening of app, the **alert panel** will open to show **Tasks** which have **start or have deadlines** within the **latest** window specified.
Have to specify `ALERT WINDOW` in hours and only integers.

Examples:
- `alert` followed by `48` will show the alert window with all tasks which **start or end** within 48 hours.
- `alert` alone will show the alert window with all tasks which **start or end** within 24 hours.


### 3.9 Schedule of the day : `schedule D/SHORTDATE [E/EFFORT]`

:man_technologist: **Wouldn't it be great if you can have your life planned out for you? Schedule can help!**

`schedule` displays a planned daily schedule according to the tasks currently stored.

#### 3.9.1 How it Works
Entering `schedule D/SHORTDATE E/EFFORT` generates a new 30-day plan for users based on their intended `E/EFFORT`, and display a list of tasks to be done on `D/SHORTDATE`.
Entering `schedule D/SHORTDATE` displays a list of tasks to be done on `D/SHORTDATE` based on the previously generated plan. [What is a short date?](#q6-what-is-the-difference-between-a-date-and-a-shortdate-)

#### 3.9.2 Generating a New Plan
When the schedule command is ran with an `E/EFFORT` flag, an internal planning algorithm is run, and all tasks will be allocated to a 30-day plan starting from the day the command is run.
As much as possible, tasks allocated to a day should not exceed the intended `E/EFFORT` level indicated by users.
However, if the need arises (as specified below), the algorithm allows the effort required for a particular day to exceed the user preferred `E/EFFORT` level.

The algorithm allocates tasks as such:
1. Allocate all events to the day(s) it is supposed to be happening.

2. Allocate all deadlines to the first free day before it is due (exclusive of due date). Otherwise, allocate task to any day before deadline with the least amount of work allocated (in terms of effort).

3. Allocates each SimpleTask to the most busy day without exceeding desired workload. If such a day is not available, allocate the task to a day with the least amount of work allocated (in terms of effort).

<details>

<summary>Need more help?</summary>

Displaying Daily Plans

<br>

Daily Plans can be viewed by entering `schedule D/SHORTDATE` or `schedule D/SHORTDATE E/EFFORT`, with the former showing an old plan, and the latter showing a newly generated plan.
As plans are only valid for 30 days from the last time it was generated, users are encouraged to regenerate a plan with an `E/EFFORT` flag if they do not remember when they last generated their plan.
If no tasks are shown, it means that there are no tasks planned for that day.
An error message that prompts users to re-generate a plan will be shown if the entered date is out of range (either too far in the future, or a date before the start date of generated plan).

</details>
<br>

:warning: Valid `D/SHORTDATE` are 30-days from the day plan was generated. Re-scheduling plans multiple times would not make an invalid date become valid.
:warning: Select an effort estimate that you can realistically complete in a day. Having abnormally high desired effort levels in comparison to effort per task (eg. desired workload of 10000 and each task has effort 1) may result in undesired behaviour where all tasks are allocated to the same day.


Examples:
- Assuming today is 2023-03-26, `schedule D/2023-04-01 E/5` will plan a 30-day schedule starting from today (March 26, 2023) according to a desired effort level of 5, and display tasks planned for April 1.
- `schedule D/2023-04-02` will show tasks which should be completed on April 2, 2023, based on a previously generated schedule (which should be generated between 3 Mar 2023, and 2 Apr 2023).

## 3.10 Subsections

:man_technologist: **Have a task that is really long? Want to organise it in smaller sections? Why not use subsection?**

### 3.10.1 Adding a Subsection to a Task : `subsection`

Adds a subsection to a task in the task book. The subsection added will appear as in the corresponding task's task card.
You can do so by entering `subsection index n/name d/description`, and the description prefix `d/` is optional.

Examples:
- Assuming your current task list has a task with index 1, `subsection 1 n/homework d/Math` will add a subsection to the main task at index 1 with a subsection with the name "homework" and description "Math".

### 3.10.2 Deleting a Subsection From a Task : `remove-subsection`

Deletes a subsection from a task, assuming you have one in the task. You can do so by entering `remove-subsection index I/subsection index`, where the first index is the main task's index, and the latter is the subsection's.

Examples:
- Assuming your current task list has a task with index 1, which in turn contains a subsection with index 1. You can delete it with `remove-subsection 1 I/1`. The newly updated task will be shown.


### 3.11 Viewing help : `help`

:man_technologist: **For the beginner user who is still unfamiliar with the commands. Welcome!**

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### 3.12 Clearing all entries : `clear`

:man_technologist: **Think twice before using this. This action is not reversible.**

Clears all entries from the task book.

Format: `clear`

### 3.13 Exiting the program : `exit`

:man_technologist: **We hope to see you again!**

Exits the program.

Format: `exit`

## 4. Storage

### 4.1 Saving the data

TaskBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### 4.2 Editing the data file

TaskBook data are saved as a JSON file `[JAR file location]/data/taskbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, taskBook will discard all data and start with an empty data file at the next run.
</div>

### 4.3 Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## 5. FAQ

### Q1 How do I transfer my data to another Computer?
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TaskBook home folder.

### Q2 What is a valid Name?
**A**: A valid Name has a minimum of 1 character, and does not have **/** in it. It can have multiple words, and there is no limit to the number of characters it accept. However, for a better user experience, we recommend limiting the number of character in name to 35 characters.

### Q3 What is a valid Tag?
**A**: A valid tag consists of only alphanumeric characters, and has minimum length of 1 character. Tags are case-sensitive, which means that **STUDY** and **study** are treated as different tags.

### Q4 What is a valid Long Date?
**A**: A valid LongDate must be in the format of `YYYY-MM-DD HHMM` such as `2023-07-13 1800` (13 July 2023, 6PM). A valid LongDate must have a valid time. A minimum of 4 characters must be supplied and only a maximum of 4 character will be parsed
  e.g. `2023-07-13 180` is invalid and `2023-07-13 18000000000` is understood as `2023-07-13 1800`

### Q5 What is a valid Short Date?
**A**: A valid ShortDate must be in the format of `YYYY-MM-DD` such as `2023-07-15` (15 July 2023).

### Q6 What is the difference between a LongDate and a ShortDate?
**A**: A `ShortDate` omits the `HHMM` component of a `LongDate`.

### Q7 What is an effort level?
**A**: The effort level is a user estimate of the amount of work required to complete a task. Users are free to select any value between 0 and 2147483647 for their estimate.

### Q8 What is a valid Description?
**A**: A valid Description is a text input that has at least one character.

### Q9 What is a valid Subsection?
**A**: A valid Subsection has a name and an optional description. Within a task, all subsection names must be unique, and it is case-insensitive. This means that **study** and **STUDY** are treated as the same name.

### Q10 Why does two different tags have the same tag color?
**A**: Clock-Work randomly assigns 1 of 20 available colors to tags. As the number of tags users use increases, the chances of multiple tags being allocated the same colour increases. More colors will be added in future iterations.

--------------------------------------------------------------------------------------------------------------------

## 6. Command summary

| Action       | Format, Examples                                                                                                      |
|--------------|-----------------------------------------------------------------------------------------------------------------------|
| **Add**      | `add n/TASKNAME [d/DESCRIPTION] [t/TAGS]…​ [E/EFFORT]` <br> e.g., `add n/read book d/Lord of the Flies t/leisure E/5` |
| **Clear**    | `clear`                                                                                                               |
| **Delete**   | `delete INDEX [INDEX]…​ `<br> e.g., `delete 3`                                                                        |
| **Edit**     | `edit INDEX [n/TASKNAME] [d/DESCRIPTION] [E/EFFORT] [t/TAG]…​`<br> e.g.,`edit 2 n/study d/CS2103T`                    |
| **Find**     | `find n/NAME` or `find d/DESCRIPTION`<br> e.g., `find n/read book`                                                    |
| **List**     | `list`                                                                                                                |
| **Help**     | `help`                                                                                                                |
| **Stats**    | `stats`                                                                                                               |
| **sort**     | `sort`                                                                                                                |
| **alert**    | `alert [ALERT_WINDOW]`                                                                                                |
| **schedule** | `schedule D/SHORTDATE [E/EFFORT]`                                                                                     |

--------------------------------------------------------------------------------------------------------------------
## 7. Glossary
2. Free day: Allocating a task to this day will not result in overloading.
3. Long date: Date in the format YYYY-MM-DD HHMM
4. Short date: Date in the format YYYY-MM-DD
3. Overload: When the sum of effort for all tasks allocated to a particular day exceeds the user-preferred daily effort level.
