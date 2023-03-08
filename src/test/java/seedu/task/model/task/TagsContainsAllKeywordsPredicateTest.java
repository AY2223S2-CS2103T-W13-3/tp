package seedu.task.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.task.testutil.SimpleTaskBuilder;

public class TagsContainsAllKeywordsPredicateTest {

    @Test
    public void equals() {
        String[] firstPredicateKeyword = {"third", "fourth"};
        String[] secondPredicateKeyword = {"third"};

        TagsContainsAllKeywordsPredicate firstPredicate =
            new TagsContainsAllKeywordsPredicate(Arrays.asList(firstPredicateKeyword));
        TagsContainsAllKeywordsPredicate secondPredicate =
            new TagsContainsAllKeywordsPredicate(Arrays.asList(secondPredicateKeyword));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagsContainsAllKeywordsPredicate firstPredicateCopy =
            new TagsContainsAllKeywordsPredicate(Arrays.asList(firstPredicateKeyword));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different task -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagsContainsAllKeywords_returnsTrue() {
        String[] tag = {"urgent", "important", "uRgEnT"};

        // Mixed-case keywords
        TagsContainsAllKeywordsPredicate predicate = new TagsContainsAllKeywordsPredicate(Arrays.asList(tag[2]));
        assertTrue(predicate.test(new SimpleTaskBuilder().withTags("urgent").build()));

        String[] test = {"study", "party"};
        // Multiple tags with all keyword
        TagsContainsAllKeywordsPredicate predicate2 = new TagsContainsAllKeywordsPredicate(Arrays.asList(test));
        assertTrue(predicate2.test(new SimpleTaskBuilder().withTags("party", "study").build()));
    }

    @Test
    public void test_tagsDoesNotContainKeywords_returnsFalse() {
        DescContainsKeywordsPredicate predicate = new DescContainsKeywordsPredicate("legoes");

        // Non-matching keyword
        assertFalse(predicate.test(new SimpleTaskBuilder().withDescription("read book").build()));

        // Keywords match name, but does not match description
        predicate = new DescContainsKeywordsPredicate("Alice");
        assertFalse(predicate.test(new SimpleTaskBuilder()
            .withName("Alice").withDescription("likes to play").build()));
        String[] test = {"study", "party"};

        //Multiple tags with all keyword but the task only has one matching keyword
        TagsContainsAllKeywordsPredicate predicate3 = new TagsContainsAllKeywordsPredicate(Arrays.asList(test));
        assertFalse(predicate3.test(new SimpleTaskBuilder().withName("study").build()));
    }
}
