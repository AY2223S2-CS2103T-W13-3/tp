package seedu.task.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.task.commons.util.StringUtil;

/**
 * Tests that a {@code Task}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsAllKeywordsPredicate implements Predicate<Task> {
    private final List<String> keyphrases;

    public NameContainsAllKeywordsPredicate(List<String> keyphrases) {
        this.keyphrases = keyphrases;
    }

    @Override
    public boolean test(Task task) {
        return keyphrases.stream().allMatch(keyphrase ->
            StringUtil.containsIgnoreCase(task.getName().toString(), keyphrase));
    }

    public List<String> getKeyphrases() {
        return this.keyphrases;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof NameContainsAllKeywordsPredicate // instanceof handles nulls
            && keyphrases.equals(((NameContainsAllKeywordsPredicate) other).keyphrases)); // state check
    }

}
