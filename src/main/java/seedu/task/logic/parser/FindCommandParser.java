package seedu.task.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.task.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.task.logic.parser.CliSyntax.PREFIX_ALLMATCH;
import static seedu.task.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.task.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.task.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.task.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.task.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.task.logic.parser.CliSyntax.PREFIX_TO;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.task.logic.commands.FindCommand;
import seedu.task.logic.parser.exceptions.ParseException;
import seedu.task.model.tag.Tag;
import seedu.task.model.task.Date;
import seedu.task.model.task.DeadlineDateContainsKeywordsPredicate;
import seedu.task.model.task.DescContainsAllKeywordsPredicate;
import seedu.task.model.task.DescContainsKeywordsPredicate;
import seedu.task.model.task.Description;
import seedu.task.model.task.EventFromContainsKeywordsPredicate;
import seedu.task.model.task.EventToContainsKeywordsPredicate;
import seedu.task.model.task.Name;
import seedu.task.model.task.NameContainsAllKeywordsPredicate;
import seedu.task.model.task.NameContainsKeywordsPredicate;
import seedu.task.model.task.TagsContainsAllKeywordsPredicate;
import seedu.task.model.task.TagsContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DESCRIPTION, PREFIX_TAG, PREFIX_ALLMATCH,
                    PREFIX_DEADLINE, PREFIX_FROM, PREFIX_TO);

        if (areTooManyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DESCRIPTION)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            return parseForFindCommand(PREFIX_NAME, argMultimap);
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            return parseForFindCommand(PREFIX_DESCRIPTION, argMultimap);
        }
        if (parseTagsForFind(argMultimap.getAllValues(PREFIX_TAG)).isPresent()) {
            return parseForFindCommand(PREFIX_TAG, argMultimap);
        }
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            return parseForFindCommand(PREFIX_DEADLINE, argMultimap);
        }
        if (argMultimap.getValue(PREFIX_FROM).isPresent()) {
            return parseForFindCommand(PREFIX_FROM, argMultimap);
        }
        if (argMultimap.getValue(PREFIX_TO).isPresent()) {
            return parseForFindCommand(PREFIX_TO, argMultimap);
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    /**
     * Returns true if more than 1 prefix is present in the given {@code ArgumentMultimap}.
     */
    private static boolean areTooManyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).filter(prefix -> argumentMultimap.getValue(prefix).isPresent()).count() > 1;
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForFind(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    private FindCommand parseForFindCommand(Prefix prefix, ArgumentMultimap argMultimap)
            throws ParseException {
        if (prefix.equals(PREFIX_NAME)) {
            Set<Name> names = ParserUtil.parseNames(argMultimap.getAllValues(PREFIX_NAME));
            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            if (argMultimap.getValue(PREFIX_ALLMATCH).isPresent()) {
                return new FindCommand(new NameContainsAllKeywordsPredicate(ParserUtil.parseNamesToList(names)));
            }
            return new FindCommand(new NameContainsKeywordsPredicate(name.toString()));
        } else if (prefix.equals(PREFIX_DESCRIPTION)) {
            Set<Description> descriptions = ParserUtil.parseDescriptions(argMultimap.getAllValues(PREFIX_DESCRIPTION));
            Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
            if (argMultimap.getValue(PREFIX_ALLMATCH).isPresent()) {
                return new FindCommand(new DescContainsAllKeywordsPredicate(ParserUtil
                    .parseDescriptionsToList(descriptions)));
            }
            return new FindCommand(new DescContainsKeywordsPredicate(description.toString()));
        } else if (prefix.equals(PREFIX_TAG)) {
            Set<Tag> tags = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
            if (argMultimap.getValue(PREFIX_ALLMATCH).isPresent()) {
                return new FindCommand(new TagsContainsAllKeywordsPredicate(ParserUtil.parseTagsToList(tags)));
            }
            return new FindCommand(new TagsContainsKeywordsPredicate(ParserUtil.parseTagsToList(tags)));
        } else if (prefix.equals(PREFIX_DEADLINE)) {
            String date = Date.parseFindDate(argMultimap.getValue(PREFIX_DEADLINE).get());
            return new FindCommand(new DeadlineDateContainsKeywordsPredicate(date));
        } else if (prefix.equals(PREFIX_FROM)) {
            String date = Date.parseFindDate(argMultimap.getValue(PREFIX_FROM).get());
            return new FindCommand(new EventFromContainsKeywordsPredicate(date));
        } else if (prefix.equals(PREFIX_TO)) {
            String date = Date.parseFindDate(argMultimap.getValue(PREFIX_TO).get());
            return new FindCommand(new EventToContainsKeywordsPredicate(date));
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

}
