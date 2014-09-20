

/**
 * A class for time unit handling for a timer for example.
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

    public TimeUnits() {
        this(0, 0, 0, 0);
    }

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

    public TimeUnits setHours(int hours) throws IllegalArgumentException {
        isHoursValid(hours);
        this.hours = hours;
        return this;
    }

    private void isHoursValid(int hours) {
        if (hours < HOURS_MIN)
            throw new IllegalArgumentException("Invalid hours");
    }
    
    public int getHours() {
        return hours;
    }

    public TimeUnits setMinutes(int minutes) throws IllegalArgumentException {
        isMinutesValid(minutes);
        this.minutes = minutes;
        return this;
    }

    private void isMinutesValid(int minutes) {
        if (minutes < MINUTES_MIN || minutes >= MINUTES_MAX)
            throw new IllegalArgumentException("Invalid minutes");
    }
    
    public int getMinutes() {
        return minutes;
    }

    public TimeUnits setSeconds(int seconds) throws IllegalArgumentException {
        isSecondssValid(seconds);
        this.seconds = seconds;
        return this;
    }

    private void isSecondssValid(int seconds) {
        if (seconds < SECONDS_MIN || seconds >= SECONDS_MAX)
            throw new IllegalArgumentException("Invalid seconds");
    }
    
    public int getSeconds() {
        return seconds;
    }
   
    public TimeUnits setMilliseconds(int milliseconds)
            throws IllegalArgumentException {
        isMillisecondssValid(milliseconds);
        this.milliseconds = milliseconds;
        return this;
    }

    private void isMillisecondssValid(int milliseconds) {
        if (milliseconds < MILLISECONDS_MIN ||
            milliseconds >= MILLISECONDS_MAX)
            throw new IllegalArgumentException("Invalid milliseconds");
    }
    
    public int getMilliseconds() {
        return milliseconds;
    }

    /**
     * Advance one millisecond.
     * @return This object.
     */
    public TimeUnits advance() {
        stepOneMillisecond();
        return this;
    }

    public String getFormat() {
        return format;
    }

    public TimeUnits setFormat(String format) {
        this.format = format;
        return this;
    }

    public char getDelimiter() {
        return delimiter;
    }

    public TimeUnits setDelimiter(char delimiter) {
        this.delimiter = delimiter;
        return this;
    }

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
   
    private void stepOneMinute() {
        if (minutes + 1 == MINUTES_MAX) {
            minutes = 0;
            setHours(hours + 1);
        }
        else
            minutes++;
    }
     
    private void stepOneSecond() {
        if (seconds + 1 == SECONDS_MAX) {
            seconds = 0;
            stepOneMinute();
        }
        else
            seconds++;
    }

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
