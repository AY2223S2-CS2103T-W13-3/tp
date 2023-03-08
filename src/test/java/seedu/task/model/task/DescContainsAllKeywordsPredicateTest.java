package seedu.task.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.task.testutil.SimpleTaskBuilder;

public class DescContainsAllKeywordsPredicateTest {

    @Test
    public void equals() {
        String[] firstPredicateKeyword = {"third", "fourth"};
        String[] secondPredicateKeyword = {"third"};

        DescContainsAllKeywordsPredicate firstPredicate =
            new DescContainsAllKeywordsPredicate(Arrays.asList(firstPredicateKeyword));
        DescContainsAllKeywordsPredicate secondPredicate =
            new DescContainsAllKeywordsPredicate(Arrays.asList(secondPredicateKeyword));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DescContainsAllKeywordsPredicate firstPredicateCopy =
            new DescContainsAllKeywordsPredicate(Arrays.asList(firstPredicateKeyword));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different task -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

    }

    @Test
    public void test_descContainsAllKeywords_returnsTrue() {
        String[] book = {"book"};
        String[] readBook = {"read", "book"};
        String[] bookMessy = {"rEaD BoOK"};

        // One keyword
        DescContainsAllKeywordsPredicate predicate = new DescContainsAllKeywordsPredicate(Arrays.asList(book));
        assertTrue(predicate.test(new SimpleTaskBuilder().withDescription("book").build()));

        // keyphrase
        predicate = new DescContainsAllKeywordsPredicate(Arrays.asList(readBook));
        assertTrue(predicate.test(new SimpleTaskBuilder().withDescription("read book in library").build()));

        // Mixed-case keywords
        predicate = new DescContainsAllKeywordsPredicate(Arrays.asList(bookMessy));
        assertTrue(predicate.test(new SimpleTaskBuilder().withDescription("read book").build()));

        String[] test = {"study", "party"};
        // Multiple descriptions with all keyword
        DescContainsAllKeywordsPredicate predicate2 = new DescContainsAllKeywordsPredicate(Arrays.asList(test));
        assertTrue(predicate2.test(new SimpleTaskBuilder().withDescription("study party").build()));
    }

    @Test
    public void test_descDoesNotContainKeywords_returnsFalse() {
        String[] test = {"legoes"};
        DescContainsAllKeywordsPredicate predicate = new DescContainsAllKeywordsPredicate(Arrays.asList(test));

        // Non-matching keyword
        assertFalse(predicate.test(new SimpleTaskBuilder().withDescription("read book").build()));

        String[] test2 = {"study", "party"};
        //Multiple descriptions with all keyword but the task only has one matching keyword
        DescContainsAllKeywordsPredicate predicate2 = new DescContainsAllKeywordsPredicate(Arrays.asList(test2));
        assertFalse(predicate2.test(new SimpleTaskBuilder().withDescription("study").build()));
    }
}
