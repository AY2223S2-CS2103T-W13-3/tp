package seedu.task.model.task;

import java.util.function.Predicate;


/**
 * Tests that a {@code Task}'s {@code Description} matches any of the keywords given.
 */
public class EventToContainsKeywordsPredicate implements Predicate<Task> {
    private final String keyphrase;

    public EventToContainsKeywordsPredicate(String keyphrase) {
        this.keyphrase = keyphrase;
    }

    @Override
    public boolean test(Task task) {
        if (task instanceof Event) {
            Event event = (Event) task;
            String date = event.getTo().getValue();
            return date.contains(keyphrase);
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof EventToContainsKeywordsPredicate // instanceof handles nulls
            && keyphrase.equals(((EventToContainsKeywordsPredicate) other).keyphrase)); // state check
    }

}
