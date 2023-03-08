package seedu.task.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.task.commons.core.index.Index;
import seedu.task.commons.core.index.IndexList;
import seedu.task.commons.util.StringUtil;
import seedu.task.logic.parser.exceptions.ParseException;
import seedu.task.model.tag.Tag;
import seedu.task.model.task.Date;
import seedu.task.model.task.Description;
import seedu.task.model.task.Name;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndexList} into an {@code IndexList} and returns it. Leading and trailing whitespaces
     * will be trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static IndexList parseIndexList(String oneBasedIndexList) throws ParseException {
        String trimmedIndices = oneBasedIndexList.trim();
        String[] indices = trimmedIndices.split(" ");

        int num = indices.length;
        IndexList list = new IndexList();

        for (int i = 0; i < num; i++) {
            list.add(parseIndex(indices[i]));
        }

        return list;
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses {@code Collection<String> names} into a {@code Set<Name>}.
     */
    public static Set<Name> parseNames(Collection<String> names) throws ParseException {
        requireNonNull(names);
        final Set<Name> nameSet = new HashSet<>();
        for (String name : names) {
            nameSet.add(parseName(name));
        }
        return nameSet;
    }

    /**
     * Changes a set of names to a list of names
     * @param names The set of names
     * @return The corresponding list of names
     */
    public static List<String> parseNamesToList(Set<Name> names) {
        requireNonNull(names);
        List<String> nameList = new ArrayList<>();
        for (Name cur: names) {
            nameList.add(cur.toString());
        }
        return nameList;
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(description);
    }

    /**
     * Parses a {@code Collection string} into a {@code Set description}
     * @param descriptions The collection of descriptions
     * @return The descriptions as a set
     * @throws ParseException if the given{@code descriptions is invalid}
     */
    public static Set<Description> parseDescriptions(Collection<String> descriptions) throws ParseException {
        requireNonNull(descriptions);
        final Set<Description> descriptionSet = new HashSet<>();
        for (String cur: descriptions) {
            descriptionSet.add(parseDescription(cur));
        }
        return descriptionSet;
    }

    /**
     * Returns a list of strings from the set of descriptions
     * @param descriptions The set of descriptions
     * @return A list of string
     */
    public static List<String> parseDescriptionsToList(Set<Description> descriptions) {
        requireNonNull(descriptions);
        final List<String> descriptionList = new ArrayList<>();
        for (Description cur: descriptions) {
            descriptionList.add(cur.toString());
        }
        return descriptionList;
    }

    /**
     * Parses a {@code String Date} into a {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Date} is invalid.
     */
    public static Date parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!Date.isValidDate(trimmedDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(trimmedDate);
    }



    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code List<String>}.
     */
    public static List<String> parseTagsToList(Set<Tag> tags) {
        requireNonNull(tags);
        List<String> tagList = new ArrayList<>();
        for (Tag cur: tags) {
            tagList.add(cur.getTagName());
        }
        System.out.println(tagList);
        return tagList;
    }


}
