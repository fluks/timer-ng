package fi.fluks;

import java.util.IllegalFormatException;

/** A class for time unit handling for a timer for example. Time units are;
 * hours, minutes, seconds and milliseconds.
 */
public class TimeUnits {
    public static final int HOURS_MIN        = 0;
    public static final int MINUTES_MAX      = 60;
    public static final int MINUTES_MIN      = 0;
    public static final int SECONDS_MAX      = 60;
    public static final int SECONDS_MIN      = 0;
    public static final int MILLISECONDS_MAX = 1000;
    public static final int MILLISECONDS_MIN = 0;

    private int hours;
    private int minutes;
    private int seconds;
    private int milliseconds;

    private char delimiter = ':';
    private String format = "%02d%c%02d%c%02d%c%03d";

    /** Initialize TimeUnits object having zero in all its time units.
     */
    public TimeUnits() {
        this(0, 0, 0, 0);
    }

    /** Initialize TimeUnits object.
     * @param hours
     * @param minutes
     * @param seconds
     * @param milliseconds
     * @throws IllegalArgumentException If {@code hours < HOURS_MIN, or
     * MINUTES_MIN < minutes <= MINUTES_MAX, or SECONDS_MIN < seconds <=
     * SECONDS_MAX or MILLISECONDS_MIN < milliseconds <= MILLISECONDS_MAX}.
     */
    public TimeUnits(int hours, int minutes, int seconds, int milliseconds)
            throws IllegalArgumentException {
        isHoursValid(hours);
        this.hours = hours;
        isMinutesValid(minutes);
        this.minutes = minutes;
        isSecondssValid(seconds);
        this.seconds = seconds;
        isMillisecondssValid(milliseconds);
        this.milliseconds = milliseconds;
    }

    /**
     * @param hours
     * @return
     * @throws IllegalArgumentException 
     */
    public TimeUnits setHours(int hours) throws IllegalArgumentException {
        isHoursValid(hours);
        this.hours = hours;
        return this;
    }

    /**
     * @param hours 
     */
    private void isHoursValid(int hours) {
        if (hours < HOURS_MIN)
            throw new IllegalArgumentException("Invalid hours");
    }
    
    /**
     * @return 
     */
    public int getHours() {
        return hours;
    }

    /**
     * @param minutes
     * @return
     * @throws IllegalArgumentException 
     */
    public TimeUnits setMinutes(int minutes) throws IllegalArgumentException {
        isMinutesValid(minutes);
        this.minutes = minutes;
        return this;
    }

    /**
     * @param minutes 
     */
    private void isMinutesValid(int minutes) {
        if (minutes < MINUTES_MIN || minutes >= MINUTES_MAX)
            throw new IllegalArgumentException("Invalid minutes");
    }
    
    /**
     * @return 
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * @param seconds
     * @return
     * @throws IllegalArgumentException 
     */
    public TimeUnits setSeconds(int seconds) throws IllegalArgumentException {
        isSecondssValid(seconds);
        this.seconds = seconds;
        return this;
    }

    /**
     * @param seconds 
     */
    private void isSecondssValid(int seconds) {
        if (seconds < SECONDS_MIN || seconds >= SECONDS_MAX)
            throw new IllegalArgumentException("Invalid seconds");
    }
    
    /**
     * @return 
     */
    public int getSeconds() {
        return seconds;
    }
   
    /**
     * @param milliseconds
     * @return
     * @throws IllegalArgumentException 
     */
    public TimeUnits setMilliseconds(int milliseconds)
            throws IllegalArgumentException {
        isMillisecondssValid(milliseconds);
        this.milliseconds = milliseconds;
        return this;
    }

    /**
     * @param milliseconds 
     */
    private void isMillisecondssValid(int milliseconds) {
        if (milliseconds < MILLISECONDS_MIN ||
            milliseconds >= MILLISECONDS_MAX)
            throw new IllegalArgumentException("Invalid milliseconds");
    }
    
    /**
     * @return 
     */
    public int getMilliseconds() {
        return milliseconds;
    }

    /** Advance one millisecond.
     * @return This object.
     */
    public TimeUnits advance() {
        stepOneMillisecond();
        return this;
    }

    /**
     * @return Format string to use in {@link #toString() toString}.
     */
    public String getFormat() {
        return format;
    }

    /**
     * @param format Format string to use in {@link #toString() toString}.
     * @return This object.
     */
    public TimeUnits setFormat(String format) {
        this.format = format;
        return this;
    }

    /**
     * @return 
     */
    public char getDelimiter() {
        return delimiter;
    }

    /**
     * @param delimiter
     * @return 
     */
    public TimeUnits setDelimiter(char delimiter) {
        this.delimiter = delimiter;
        return this;
    }

    /** TimeUnits object is stringified by using a format string. The parameters
     * for {@link java.lang.String#format(String, Object...)
     * String.format()} are; hours, delimiter, minutes, delimiter, seconds,
     * delimiter and milliseconds. Format string should have corresponding
     * format specifiers.
     * @return TimeUnits object's represented as a String.
     * @throws IllegalFormatException If format string is not valid.
     */
    @Override
    public String toString() {
        return String.format(
            format,
            hours,   delimiter,
            minutes, delimiter,
            seconds, delimiter,
            milliseconds
        );
    }

    /** Set time to zero.
     * @return This object.
     */
    public TimeUnits reset() {
        return setHours(HOURS_MIN).setMinutes(MINUTES_MIN).
            setSeconds(SECONDS_MIN).  setMilliseconds(MILLISECONDS_MIN);
    }

    /** Get time in milliseconds.
     * @return Number of milliseconds in time units.
     */
    public long timeInMilliseconds () {
        return hours * MINUTES_MAX * SECONDS_MAX * MILLISECONDS_MAX +
               minutes * SECONDS_MAX * MILLISECONDS_MAX +
               seconds * MILLISECONDS_MAX +
               milliseconds;
    }
   
    /** Step one minute ahead.
     */
    private void stepOneMinute() {
        if (minutes + 1 == MINUTES_MAX) {
            minutes = 0;
            setHours(hours + 1);
        }
        else
            minutes++;
    }
     
    /** Step one second ahead.
     */
    private void stepOneSecond() {
        if (seconds + 1 == SECONDS_MAX) {
            seconds = 0;
            stepOneMinute();
        }
        else
            seconds++;
    }

    /** Step one millisecond ahead.
     */
    private void stepOneMillisecond() {
        if (milliseconds + 1 == MILLISECONDS_MAX) {
            milliseconds = 0;
            stepOneSecond();
        }
        else
            milliseconds++;
    }

    /** Are time units of TimeUnits objects equal.
     * @param t1
     * @param t2
     * @return False if either is null, true if hours, minutes, seconds and
     * milliseconds fields are the same.
     */
    public static boolean timeUnitsAreEqual(TimeUnits t1, TimeUnits t2) {
        if (t1 == null || t2 == null)
            return false;

        return t1.getHours() == t2.getHours() &&
               t1.getMinutes() == t2.getMinutes() &&
               t1.getSeconds() == t2.getSeconds() &&
               t1.getMilliseconds() == t2.getMilliseconds();
    }
}
