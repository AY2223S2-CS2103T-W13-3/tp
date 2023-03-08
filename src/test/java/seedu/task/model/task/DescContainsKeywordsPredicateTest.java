package seedu.task.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.task.testutil.SimpleTaskBuilder;

public class DescContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "first";
        String secondPredicateKeyword = "second";

        DescContainsKeywordsPredicate firstPredicate = new DescContainsKeywordsPredicate(firstPredicateKeyword);
        DescContainsKeywordsPredicate secondPredicate = new DescContainsKeywordsPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DescContainsKeywordsPredicate firstPredicateCopy = new DescContainsKeywordsPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different task -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_descContainsKeywords_returnsTrue() {
        // One keyword
        DescContainsKeywordsPredicate predicate = new DescContainsKeywordsPredicate("book");
        assertTrue(predicate.test(new SimpleTaskBuilder().withDescription("book").build()));

        // keyphrase
        predicate = new DescContainsKeywordsPredicate("read book");
        assertTrue(predicate.test(new SimpleTaskBuilder().withDescription("read book in library").build()));

        // Only one matching keyword
        predicate = new DescContainsKeywordsPredicate("book");
        assertTrue(predicate.test(new SimpleTaskBuilder().withDescription("read book").build()));

        // Mixed-case keywords
        predicate = new DescContainsKeywordsPredicate("rEaD BoOK");
        assertTrue(predicate.test(new SimpleTaskBuilder().withDescription("read book").build()));

    }

    @Test
    public void test_descDoesNotContainKeywords_returnsFalse() {
        DescContainsKeywordsPredicate predicate = new DescContainsKeywordsPredicate("legoes");

        // Non-matching keyword
        assertFalse(predicate.test(new SimpleTaskBuilder().withDescription("read book").build()));

        // Keywords match name, but does not match description
        predicate = new DescContainsKeywordsPredicate("Alice");
        assertFalse(predicate.test(new SimpleTaskBuilder()
                .withName("Alice").withDescription("likes to play").build()));

    }
}
