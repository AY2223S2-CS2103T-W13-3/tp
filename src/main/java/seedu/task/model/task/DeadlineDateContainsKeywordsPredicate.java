package seedu.task.model.task;

import java.util.function.Predicate;


/**
 * Tests that a {@code Task}'s {@code Description} matches any of the keywords given.
 */
public class DeadlineDateContainsKeywordsPredicate implements Predicate<Task> {
    private final String keyphrase;

    public DeadlineDateContainsKeywordsPredicate(String keyphrase) {
        this.keyphrase = keyphrase;
    }

    @Override
    public boolean test(Task task) {
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            String date = deadline.getDeadline().getValue();
            return date.contains(keyphrase);
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeadlineDateContainsKeywordsPredicate // instanceof handles nulls
            && keyphrase.equals(((DeadlineDateContainsKeywordsPredicate) other).keyphrase)); // state check
    }

}
