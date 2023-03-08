package seedu.task.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.task.testutil.SimpleTaskBuilder;

public class NameContainsAllKeywordsPredicateTest {

    @Test
    public void equals() {
        String[] firstPredicateKeyword = {"third", "fourth"};
        String[] secondPredicateKeyword = {"third"};


        NameContainsAllKeywordsPredicate firstPredicate =
            new NameContainsAllKeywordsPredicate(Arrays.asList(firstPredicateKeyword));
        NameContainsAllKeywordsPredicate secondPredicate =
            new NameContainsAllKeywordsPredicate(Arrays.asList(secondPredicateKeyword));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsAllKeywordsPredicate firstPredicateCopy =
            new NameContainsAllKeywordsPredicate(Arrays.asList(firstPredicateKeyword));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different task -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

    }

    @Test
    public void test_nameContainsAllKeywords_returnsTrue() {
        String[] alice = {"Alice"};
        String[] aliceBob = {"Alice", "Bob"};
        String[] aliceBobMixed = {"aLicE", "bOB"};
        // One keyword
        NameContainsAllKeywordsPredicate predicate = new NameContainsAllKeywordsPredicate(Arrays.asList(alice));
        assertTrue(predicate.test(new SimpleTaskBuilder().withName("Alice").build()));

        // keyphrase
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList(aliceBob));
        assertTrue(predicate.test(new SimpleTaskBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList(alice));
        assertTrue(predicate.test(new SimpleTaskBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList(aliceBobMixed));
        assertTrue(predicate.test(new SimpleTaskBuilder().withName("Alice Bob").build()));

        String[] test = {"study", "party"};
        // Multiple names with all keyword
        NameContainsAllKeywordsPredicate predicate2 = new NameContainsAllKeywordsPredicate(Arrays.asList(test));
        assertTrue(predicate2.test(new SimpleTaskBuilder().withName("study party").build()));


    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        NameContainsAllKeywordsPredicate predicate;
        String[] carol = {"Carol"};
        String[] likeToPlay = {"likeToPlay"};

        // Non-matching keyword
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList(carol));
        assertFalse(predicate.test(new SimpleTaskBuilder().withName("Alice Bob").build()));

        // Keywords match description, but does not match name
        predicate = new NameContainsAllKeywordsPredicate(Arrays.asList(likeToPlay));
        assertFalse(predicate.test(new SimpleTaskBuilder()
            .withName("Alice").withDescription("likes to play").build()));

        String[] test = {"study", "party"};
        //Multiple names with all keyword but the task only has one matching keyword
        NameContainsAllKeywordsPredicate predicate3 = new NameContainsAllKeywordsPredicate(Arrays.asList(test));
        assertFalse(predicate3.test(new SimpleTaskBuilder().withName("study").build()));
    }
}
