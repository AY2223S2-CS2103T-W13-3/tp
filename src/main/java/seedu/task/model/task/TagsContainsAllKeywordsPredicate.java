package seedu.task.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.task.commons.util.StringUtil;

/**
 * Tests that any of a {@code Task}'s {@code Tags} matches any of the keywords given.
 */
public class TagsContainsAllKeywordsPredicate implements Predicate<Task> {
    private final List<String> keyphrases;

    public TagsContainsAllKeywordsPredicate(List<String> keyphrases) {
        this.keyphrases = keyphrases;
    }

    @Override
    public boolean test(Task task) {
        return keyphrases.stream().allMatch(keyphrase ->
           StringUtil.containsIgnoreCase(task.getTags().toString(), keyphrase));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof TagsContainsAllKeywordsPredicate // instanceof handles nulls
            && keyphrases.equals(((TagsContainsAllKeywordsPredicate) other).keyphrases)); // state check
    }

}
