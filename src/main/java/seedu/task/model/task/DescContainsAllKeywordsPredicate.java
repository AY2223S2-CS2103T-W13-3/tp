package seedu.task.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.task.commons.util.StringUtil;

/**
 * Tests that a {@code Task}'s {@code Description} matches any of the keywords given.
 */
public class DescContainsAllKeywordsPredicate implements Predicate<Task> {
    private final List<String> keyphrases;

    public DescContainsAllKeywordsPredicate(List<String> keyphrase) {
        this.keyphrases = keyphrase;
    }

    @Override
    public boolean test(Task task) {
        return keyphrases.stream().allMatch(keyphrase ->
            StringUtil.containsIgnoreCase(task.getDescription().value, keyphrase));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DescContainsAllKeywordsPredicate // instanceof handles nulls
            && keyphrases.equals(((DescContainsAllKeywordsPredicate) other).keyphrases)); // state check
    }

}
