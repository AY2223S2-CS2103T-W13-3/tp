package seedu.task.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.task.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import seedu.task.logic.parser.exceptions.ParseException;

/**
 * Represents a Task's date in the task book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date implements Comparable<Date> {

    public static final String MESSAGE_INVALID_DATE = "Date must be valid and does not contain the time";
    public static final String MESSAGE_CONSTRAINTS = "Date must be valid";
    public final LocalDateTime value;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = LocalDateTime.parse(formatDate(date));
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        try {
            String toParse = formatDate(test);
            LocalDateTime.parse(toParse);
        } catch (DateTimeException | IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    /**
     * Formats the String to be a parseable String for LocalDateTime.
     * Only the first 4 numbers of the input String is parsed for time.
     *
     * @param input input String by user.
     * @return String parseable by LocalDateTime.
     * @Throw IndexOutOfBoundsException if input is not the right format.
     */
    public static String formatDate(String input) throws IndexOutOfBoundsException {
        String[] dateTime = input.trim().split(" ");
        String date = dateTime[0];
        String time = dateTime[1];
        String hour = time.substring(0, 2);
        String minute = time.substring(2, 4);
        return date + "T" + hour + ":" + minute + ":00";
    }

    /**
     * The seconds field is ignored.
     *
     * @return String to be printed out.
     */
    @Override
    public String toString() {
        String month = value.getMonth().toString().substring(0, 3);
        int day = value.getDayOfMonth();
        int year = value.getYear();
        String hour = String.format("%02d", value.getHour());
        String minute = String.format("%02d", value.getMinute());
        return month + " " + day + " " + year + " " + hour + minute;
    }

    /**
     * Checks whether a date is a valid date
     * @param test The string input representing a date
     * @return True or False
     */
    public static String parseFindDate(String test) throws ParseException {
        try {
            String toParse = formatDateOnly(test);
            LocalDate.parse(toParse);
            return toParse;
        } catch (DateTimeException | IndexOutOfBoundsException e) {
            throw new ParseException(MESSAGE_INVALID_DATE);
        }
    }

    /**
     * Trims the date for checking
     * @param input The date in form of a string
     * @return A trimmed string
     * @throws IndexOutOfBoundsException
     */
    public static String formatDateOnly(String input) throws IndexOutOfBoundsException {
        String dateTime = input.trim();
        return dateTime;
    }



    /**
     * Returns a full information String which can be parsed back into a DateTime.
     */
    public String getValue() {
        String month = String.format("%02d", value.getMonthValue());
        String day = String.format("%02d", value.getDayOfMonth());
        int year = value.getYear();
        String hour = String.format("%02d", value.getHour());
        String minute = String.format("%02d", value.getMinute());
        return year + "-" + month + "-" + day + " " + hour + minute;
    }

    /**
     * Compares the date using the LocalDateTime compareTo.
     *
     * @param date the object to be compared.
     * @return int priority.
     */
    @Override
    public int compareTo(Date date) {
        return this.value.compareTo(date.value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && value.equals(((Date) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
