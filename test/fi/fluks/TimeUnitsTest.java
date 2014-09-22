package fi.fluks;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TimeUnitsTest {
    private TimeUnits tu;
    
    @Before
    public void setUp() {
        tu = new TimeUnits(0, 0, 0, 0);
    }
    
    @Test
    public void newInstanceDefault() {
        assertNotNull(tu);
        assertEquals("Hours default to 0", 0, tu.getHours());
        assertEquals("Minutes default to 0", 0, tu.getMinutes());
        assertEquals("Seconds default to 0", 0, tu.getSeconds());
        assertEquals("Milliseconds default to 0", 0, tu.getMilliseconds());
    }
    
    @Test
    public void newInstance() {
        int hours = 100, minutes = 59, seconds = 59,
                milliseconds = 999;
        TimeUnits t = new TimeUnits(
                hours,
                minutes,
                seconds,
                milliseconds
        );
        assertNotNull(t);
        assertEquals(
            "Hours is " + Integer.toString(hours),
             hours,
             t.getHours()
        );
        assertEquals(
            "Minutes is " + Integer.toString(minutes),
             minutes,
             t.getMinutes()
        );
        assertEquals(
            "Seconds is " + Integer.toString(seconds),
             seconds,
             t.getSeconds()
        );
        assertEquals(
            "Milliseconds is " + Integer.toString(milliseconds),
             milliseconds,
             t.getMilliseconds()
        );

    }

    @Test
    public void newInstanceInvalidHours() {
        try {
            TimeUnits t = new TimeUnits(-1, 0, 0, 0);
            fail("New with hours less than HOURS_MIN didn't throw.");
        }
        catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void newInstanceInvalidMinutes() {
        try {
            TimeUnits t = new TimeUnits(0, TimeUnits.MINUTES_MAX + 1, 0, 0);
            fail("New with minutes greater than MINUTES_MAX didn't throw.");
        }
        catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void advance() {
        tu.advance();
        assertEquals("Advanced milliseconds to 1", 1, tu.getMilliseconds());
        
        tu.setMilliseconds(TimeUnits.MILLISECONDS_MAX - 1);
        tu.advance();
        assertEquals("Milliseconds turned around to zero",
                0, tu.getMilliseconds());
        assertEquals("Seconds now 1", 1, tu.getSeconds());
        
        tu.setSeconds(TimeUnits.SECONDS_MAX - 1);
        tu.setMilliseconds(TimeUnits.MILLISECONDS_MAX - 1);
        tu.advance();
        assertEquals("Seconds turned around to zero", 0, tu.getSeconds());
        assertEquals("Minutes now 1", 1, tu.getMinutes());
        assertEquals("Milliseconds turned around to zero",
                0, tu.getMilliseconds());
                
        tu.setMinutes(TimeUnits.MINUTES_MAX - 1);
        tu.setSeconds(TimeUnits.SECONDS_MAX - 1);
        tu.setMilliseconds(TimeUnits.MILLISECONDS_MAX - 1);
        tu.advance();
        assertEquals("Milliseconds turned around to zero",
                0, tu.getMilliseconds());
        assertEquals("Seconds turned around to zero", 0, tu.getSeconds());
        assertEquals("Minutes turned around to zero", 0, tu.getMinutes());
        assertEquals("Hours now 1", 1, tu.getHours());
    }

    @Test
    public void toStringTest() {
        assertEquals("Default time to string", "00:00:00:000", tu.toString());

        tu.setHours(1).setMinutes(1).setSeconds(1).
                setMilliseconds(1);
        assertEquals("One on every time unit to string", "01:01:01:001",
                tu.toString());
        
        tu.setHours(10).setMinutes(59).setSeconds(59).
                setMilliseconds(999);
        assertEquals("Maximum time units to string", "10:59:59:999",
                tu.toString());
    }

    @Test
    public void resetTest() {
        tu.setHours(1).setMinutes(1).setSeconds(1).setMilliseconds(1);
        tu.reset();

        assertEquals(0, tu.getHours());
        assertEquals(0, tu.getMinutes());
        assertEquals(0, tu.getSeconds());
        assertEquals(0, tu.getMilliseconds());
    }

    @Test
    public void timeUnitsAreEqualTest() {
        TimeUnits tu2 = new TimeUnits();

        assertTrue(TimeUnits.timeUnitsAreEqual(tu, tu2));

        tu.advance();
        assertFalse(TimeUnits.timeUnitsAreEqual(tu, tu2));

        tu.setHours(1).setMinutes(1).setSeconds(1).setMilliseconds(1);
        tu2.setHours(1).setMinutes(1).setSeconds(1).setMilliseconds(1);
    }

    @Test
    public void timeInMilliseconds() {
        assertEquals(0, tu.timeInMilliseconds());
        
        tu.setHours(1).setMinutes(1).setSeconds(1).setMilliseconds(1);
        long expected = 1 * 60 * 60 * 1000 +
                        1 * 60 * 1000 +
                        1 * 1000 + 
                        1;
        assertEquals(expected, tu.timeInMilliseconds());
    }
}