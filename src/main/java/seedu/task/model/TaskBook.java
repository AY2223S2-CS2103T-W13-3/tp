package seedu.task.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.task.model.calendar.MonthlyPlan;
import seedu.task.model.task.DeadlineList;
import seedu.task.model.task.EventList;
import seedu.task.model.task.SimpleTaskList;
import seedu.task.model.task.Task;
import seedu.task.model.task.UniqueTaskList;

/**
 * Wraps all data at the task-book level
 * Duplicates are not allowed (by .isSameTask comparison)
 */
public class TaskBook implements ReadOnlyTaskBook {

    private final UniqueTaskList tasks;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        tasks = new UniqueTaskList();
    }

    public TaskBook() {}

    /**
     * Creates an TaskBook using the Tasks in the {@code toBeCopied}
     */
    public TaskBook(ReadOnlyTaskBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the task list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Resets the existing data of this {@code TaskBook} with {@code newData}.
     */
    public void resetData(ReadOnlyTaskBook newData) {
        requireNonNull(newData);

        setTasks(newData.getTaskList());
    }

    //// task-level operations

    /**
     * Returns true if a task with the same identity as {@code task} exists in the task book.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }


    /**
     * Adds a task to the task book.
     * The task must not already exist in the task book.
     */
    public void addTask(Task p) {
        tasks.add(p);
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the task book.
     * The task identity of {@code editedTask} must not be the same as another existing task in the task book.
     */
    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);

        tasks.setTask(target, editedTask);
    }

    /**
     * Removes {@code key} from this {@code TaskBook}.
     * {@code key} must exist in the task book.
     */
    public void removeTask(Task key) {
        tasks.remove(key);
    }

    public void sortTask() {
        tasks.sort();
    }

    /**
     * Main algorithm that supports the planning function
     * @param workload amount of effort user wants to put in per day in the workplan.
     */
    public void plan(int workload) {
        LocalDate currentDate = java.time.LocalDate.now();
        SimpleTaskList simpleTasks = tasks.filterSimpleTasks(currentDate);
        EventList events = tasks.filterEvents(currentDate);
        DeadlineList deadlines = tasks.filterDeadlines(currentDate);

        MonthlyPlan plan = new MonthlyPlan(workload, currentDate);
        plan.allocateEvents(events);
        plan.allocateDeadlines(deadlines);
        plan.allocateSimpleTasks(simpleTasks);
    }

    //// util methods

    @Override
    public String toString() {
        return tasks.asUnmodifiableObservableList().size() + " tasks";
        // TODO: refine later
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskBook // instanceof handles nulls
                && tasks.equals(((TaskBook) other).tasks));
    }

    @Override
    public int hashCode() {
        return tasks.hashCode();
    }
}
