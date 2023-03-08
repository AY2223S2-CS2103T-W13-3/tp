package seedu.task.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.task.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.task.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.task.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import seedu.task.commons.core.Messages;
import seedu.task.model.Model;
import seedu.task.model.task.Task;

/**
 * Finds and lists all tasks in task book whose name/tag/description matches the argument.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose details contain "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: (Only use 1 prefix)\n"
            + "[" + PREFIX_NAME + "NAME] OR "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] OR "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "books";

    public final Predicate<Task> predicate;

    public FindCommand(Predicate<Task> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
