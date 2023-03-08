package seedu.task.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.task.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.task.testutil.TypicalDeadlines.ASSIGNMENT;
import static seedu.task.testutil.TypicalDeadlines.PROJECT;
import static seedu.task.testutil.TypicalDeadlines.RETURN_BOOK;
import static seedu.task.testutil.TypicalDeadlines.getTypicalDeadlineBook;
import static seedu.task.testutil.TypicalEvents.MEETING;
import static seedu.task.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.task.testutil.TypicalTasks.BENSON;
import static seedu.task.testutil.TypicalTasks.DANIEL;
import static seedu.task.testutil.TypicalTasks.getTypicalTaskBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.task.model.Model;
import seedu.task.model.ModelManager;
import seedu.task.model.UserPrefs;
import seedu.task.model.task.DeadlineDateContainsKeywordsPredicate;
import seedu.task.model.task.EventFromContainsKeywordsPredicate;
import seedu.task.model.task.EventToContainsKeywordsPredicate;
import seedu.task.model.task.NameContainsAllKeywordsPredicate;
import seedu.task.model.task.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalTaskBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTaskBook(), new UserPrefs());
    private Model deadlineModel = new ModelManager(getTypicalDeadlineBook(), new UserPrefs());
    private Model expectedDeadlineModel = new ModelManager(getTypicalDeadlineBook(), new UserPrefs());
    private Model eventModel = new ModelManager(getTypicalEventBook(), new UserPrefs());
    private Model expectedEventModel = new ModelManager(getTypicalEventBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate("first");
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate("second");
        String[] inputs = {"study", "party"};
        NameContainsAllKeywordsPredicate thirdPredicate =
                new NameContainsAllKeywordsPredicate(Arrays.asList(inputs));


        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);
        FindCommand findThirdCommand = new FindCommand(thirdPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));

        //same values -> returns true
        assertTrue(findThirdCommand.equals(new FindCommand(thirdPredicate)));
    }

    @Test
    public void execute_keywords_noTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate("TESTING");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_multipleKeywords_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2);
        NameContainsKeywordsPredicate predicate = preparePredicate("Meier");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        model.getFilteredTaskList().stream().forEach(task -> System.out.println(task.getName()));
        assertEquals(Arrays.asList(BENSON, DANIEL), model.getFilteredTaskList());
    }

    @Test
    public void execute_multipleKeywords_oneTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 1);
        NameContainsAllKeywordsPredicate predicate = prepareAllPredicate("Benson", "Meier");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        model.getFilteredTaskList().stream().forEach(task -> System.out.println(task.getName()));
        assertEquals(Arrays.asList(BENSON), model.getFilteredTaskList());

    }

    @Test
    public void execute_findDeadline_oneTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 3);
        DeadlineDateContainsKeywordsPredicate predicate = prepareDeadlinePredicate("2023-01-01");
        FindCommand command = new FindCommand(predicate);
        expectedDeadlineModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, deadlineModel, expectedMessage, expectedDeadlineModel);
        model.getFilteredTaskList().stream().forEach(task -> System.out.println(task.getName()));
        assertEquals(Arrays.asList(RETURN_BOOK, ASSIGNMENT, PROJECT), deadlineModel.getFilteredTaskList());
    }

    @Test
    public void execute_findEventByFrom_oneTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 1);
        EventFromContainsKeywordsPredicate predicate = prepareEventFromPredicate("2023-01-01");
        FindCommand command = new FindCommand(predicate);
        expectedEventModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, eventModel, expectedMessage, expectedEventModel);
        expectedEventModel.getFilteredTaskList().stream().forEach(task -> System.out.println(task.getName()));
        assertEquals(Arrays.asList(MEETING), eventModel.getFilteredTaskList());
    }

    @Test
    public void execute_findEventByTo_oneTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 1);
        EventToContainsKeywordsPredicate predicate = prepareEventToPredicate("2023-01-02");
        FindCommand command = new FindCommand(predicate);
        expectedEventModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, eventModel, expectedMessage, expectedEventModel);
        expectedEventModel.getFilteredTaskList().stream().forEach(task -> System.out.println(task.getName()));
        assertEquals(Arrays.asList(MEETING), eventModel.getFilteredTaskList());
    }



    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(userInput);
    }

    private NameContainsAllKeywordsPredicate prepareAllPredicate(String... userInput) {
        return new NameContainsAllKeywordsPredicate(Arrays.asList(userInput));
    }

    private DeadlineDateContainsKeywordsPredicate prepareDeadlinePredicate(String deadline) {
        return new DeadlineDateContainsKeywordsPredicate(deadline);
    }

    private EventFromContainsKeywordsPredicate prepareEventFromPredicate(String from) {
        return new EventFromContainsKeywordsPredicate(from);
    }
    private EventToContainsKeywordsPredicate prepareEventToPredicate(String to) {
        return new EventToContainsKeywordsPredicate(to);
    }
}
