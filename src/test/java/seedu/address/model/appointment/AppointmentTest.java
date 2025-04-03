package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.commons.util.DateUtil.DATE_TIME_FORMATTER;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppointmentTest {

    private Appointment appointment;

    @BeforeEach
    public void setUp() {
        // Define DateTimeFormatter for the expected format
        DateTimeFormatter formatter = DATE_TIME_FORMATTER;

        // Create Appointment with LocalDateTime
        appointment = new Appointment("T1234567B",
                "Doctor Visit",
                LocalDateTime.parse("20-03-2025 12:00", formatter),
                LocalDateTime.parse("20-03-2025 12:30", formatter), "name 1");
    }

    @Test
    public void testAppointmentCreation() {
        assertNotNull(appointment);
        assertEquals("Doctor Visit", appointment.getDescription());

        DateTimeFormatter formatter = DATE_TIME_FORMATTER;

        assertEquals(LocalDateTime.parse("20-03-2025 12:00", formatter), appointment.getStartDate());
        assertEquals(LocalDateTime.parse("20-03-2025 12:30", formatter), appointment.getEndDate());
        assertEquals("T1234567B", appointment.getDoctorNric());
    }

    @Test
    public void testToString() {
        // Check the string representation of the Appointment
        String expectedString = "Doctor Visit FROM 20-03-2025 12:00 TO 20-03-2025 12:30";
        assertEquals(expectedString, appointment.toString());
    }
}
