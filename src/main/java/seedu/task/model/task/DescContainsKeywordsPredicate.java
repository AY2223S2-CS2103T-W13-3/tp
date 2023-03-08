package seedu.task.model.task;

import java.util.function.Predicate;

import seedu.task.commons.util.StringUtil;

/**
 * Tests that a {@code Task}'s {@code Description} matches any of the keywords given.
 */
public class DescContainsKeywordsPredicate implements Predicate<Task> {
    private final String keyphrase;

    public DescContainsKeywordsPredicate(String keyphrase) {
        this.keyphrase = keyphrase;
    }

    @Override
    public boolean test(Task task) {
        return StringUtil.containsIgnoreCase(task.getDescription().value, keyphrase);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DescContainsKeywordsPredicate // instanceof handles nulls
                && keyphrase.equals(((DescContainsKeywordsPredicate) other).keyphrase)); // state check
    }

}

