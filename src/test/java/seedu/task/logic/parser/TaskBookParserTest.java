package seedu.task.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.task.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.task.logic.parser.CliSyntax.PREFIX_ALLMATCH;
import static seedu.task.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.task.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.task.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.task.testutil.Assert.assertThrows;
import static seedu.task.testutil.TypicalIndexLists.INDEXLIST_FIRST_TASK;
import static seedu.task.testutil.TypicalIndexLists.INDEXLIST_FIRST_TASK_INT;
import static seedu.task.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.task.logic.commands.AddCommand;
import seedu.task.logic.commands.ClearCommand;
import seedu.task.logic.commands.DeleteCommand;
import seedu.task.logic.commands.EditCommand;
import seedu.task.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.task.logic.commands.ExitCommand;
import seedu.task.logic.commands.FindCommand;
import seedu.task.logic.commands.HelpCommand;
import seedu.task.logic.commands.ListCommand;
import seedu.task.logic.parser.exceptions.ParseException;
import seedu.task.model.task.DescContainsAllKeywordsPredicate;
import seedu.task.model.task.DescContainsKeywordsPredicate;
import seedu.task.model.task.NameContainsAllKeywordsPredicate;
import seedu.task.model.task.NameContainsKeywordsPredicate;
import seedu.task.model.task.TagsContainsKeywordsPredicate;
import seedu.task.model.task.Task;
import seedu.task.testutil.EditTaskDescriptorBuilder;
import seedu.task.testutil.SimpleTaskBuilder;
import seedu.task.testutil.TaskUtil;

public class TaskBookParserTest {

    private final TaskBookParser parser = new TaskBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Task task = new SimpleTaskBuilder().build();
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task);
        AddCommand command = (AddCommand) parser.parseCommand(TaskUtil.getAddCommand(task));
        assertEquals(new AddCommand(tasks), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEXLIST_FIRST_TASK_INT);
        assertEquals(new DeleteCommand(INDEXLIST_FIRST_TASK), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Task task = new SimpleTaskBuilder().build();
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(task).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_TASK.getOneBased() + " " + TaskUtil.getEditTaskDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_TASK, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        String nameKeyphrase = "Alice";
        String nameKeyphrase1 = "Bob";
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_NAME + nameKeyphrase);
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(nameKeyphrase)), command);
        FindCommand command1 = (FindCommand) parser.parseCommand(
            FindCommand.COMMAND_WORD + " " + PREFIX_ALLMATCH + " " + PREFIX_NAME
                + nameKeyphrase + " " + PREFIX_NAME + nameKeyphrase1);
        List<String> nameList = new ArrayList<>();
        nameList.add("Bob");
        nameList.add("Alice");
        assertEquals(new FindCommand(new NameContainsAllKeywordsPredicate(nameList)), command1);

        String descriptionKeyphrase = "test";
        String descriptionKeyphrase1 = "now";
        FindCommand descriptionCommand = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_DESCRIPTION + descriptionKeyphrase);
        assertEquals(new FindCommand(new DescContainsKeywordsPredicate(descriptionKeyphrase)), descriptionCommand);
        FindCommand descriptionCommand1 = (FindCommand) parser.parseCommand(
            FindCommand.COMMAND_WORD + " " + PREFIX_ALLMATCH + " "
                + PREFIX_DESCRIPTION + descriptionKeyphrase
                + " " + PREFIX_DESCRIPTION + descriptionKeyphrase1);
        List<String> descriptionList = new ArrayList<>();
        descriptionList.add(descriptionKeyphrase);
        descriptionList.add(descriptionKeyphrase1);
        assertEquals(new FindCommand(new DescContainsAllKeywordsPredicate(descriptionList)), descriptionCommand1);

        String[] tagKeyphrases = {"friend", "family"};
        String tagKeyphrase = PREFIX_TAG + "friend " + PREFIX_TAG + "family";
        FindCommand tagCommand = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + tagKeyphrase);
        assertEquals(new FindCommand(new TagsContainsKeywordsPredicate(Arrays.asList(tagKeyphrases))), tagCommand);


    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
