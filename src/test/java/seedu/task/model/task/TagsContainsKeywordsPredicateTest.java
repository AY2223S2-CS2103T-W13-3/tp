package seedu.task.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.task.testutil.SimpleTaskBuilder;

public class TagsContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String[] firstPredicateKeyword = {"first"};
        String[] secondPredicateKeyword = {"second"};



        TagsContainsKeywordsPredicate firstPredicate =
                new TagsContainsKeywordsPredicate(Arrays.asList(firstPredicateKeyword));
        TagsContainsKeywordsPredicate secondPredicate =
                new TagsContainsKeywordsPredicate(Arrays.asList(secondPredicateKeyword));


        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));


        // same values -> returns true
        TagsContainsKeywordsPredicate firstPredicateCopy =
                new TagsContainsKeywordsPredicate(Arrays.asList(firstPredicateKeyword));
        assertTrue(firstPredicate.equals(firstPredicateCopy));


        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different task -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

    }

    @Test
    public void test_tagsContainsKeywords_returnsTrue() {
        String[] tag = {"urgent", "important", "uRgEnT"};
        // One keyword
        TagsContainsKeywordsPredicate predicate = new TagsContainsKeywordsPredicate(Arrays.asList(tag[0]));
        assertTrue(predicate.test(new SimpleTaskBuilder().withTags("urgent").build()));

        // match 1
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList(tag[1]));
        assertTrue(predicate.test(new SimpleTaskBuilder().withTags("urgent", "important").build()));

        // match at least 1
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList(tag));
        assertTrue(predicate.test(new SimpleTaskBuilder().withTags("sorta", "important").build()));

        // Mixed-case keywords
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList(tag[2]));
        assertTrue(predicate.test(new SimpleTaskBuilder().withTags("urgent").build()));


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
    }
}
