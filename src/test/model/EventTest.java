package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Code from AlarmSystem
public class EventTest {
    // amount of time that two events are allowed to be off by in ms
    private static final long TOLERANCE = 15;
    private Event e;
    private Date d;

    @BeforeEach
    public void runBefore() {
        e = new Event("Pokemon added");
        d = Calendar.getInstance().getTime();
    }

    @Test
    public void testEvent() {
        assertEquals("Pokemon added", e.getDescription());
        assertTrue(d.getTime() - e.getDate().getTime() < TOLERANCE);
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "Pokemon added", e.toString());
    }
}