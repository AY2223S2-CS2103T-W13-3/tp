package seedu.task.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.task.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.task.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.task.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;

import seedu.task.logic.commands.exceptions.CommandException;
import seedu.task.model.Model;
import seedu.task.model.task.Task;

/**
 * Adds a task to the task book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the task book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_DESCRIPTION + "Example description "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task book";

    private final ArrayList<Task> toAdd;

    /**
     * Creates an AddCommand to add the specified {@code List<Task>}
     */
    public AddCommand(List<Task> taskList) {
        requireNonNull(taskList);
        toAdd = new ArrayList<Task>();
        for (Task cur: taskList) {
            toAdd.add(cur);
        }
    }

    /**
     * Creates an AddCommand to add the specified {@code Task}
     */
    public AddCommand(Task task) {
        requireNonNull(task);
        toAdd = new ArrayList<Task>();
        toAdd.add(task);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        for (Task cur: toAdd) {
            if (model.hasTask(cur)) {
                throw new CommandException(MESSAGE_DUPLICATE_TASK);
            }
        }
        for (Task cur: toAdd) {
            model.addTask(cur);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, formatAddedTasks(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }

    /**
     * Formats the added tasks to be printed out by UI
     * @param toAdd The arraylist of tasks to add
     * @return The formatted description of tasks
     */
    public String formatAddedTasks(ArrayList<Task> toAdd) {
        String description = "";
        int index = 1;
        for (Task task: toAdd) {
            description += index + ". " + task.toString() + "\n";
            index += 1;
        }
        return description;
    }
}
