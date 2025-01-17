package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;


/**
 * A task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {

    /**
     * The date and time of the deadline.
     */
    protected String by;

    /**
     * The input formatter to parse Date and Time input.
     */
    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * The output formatter to format Date and Time output.
     */
    private DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");

    /**
     * Constructor for a Deadline task.
     *
     * @param description The description of the Deadline task.
     * @param date        The deadline of the Deadline task. Must follow the format yyyy-MM-dd HH:mm.
     * @throws DukeException If the deadline does not follow the format yyyy-MM-dd HH:mm.
     */
    public Deadline(String description, String date) throws DukeException {
        super(description);

        try {
            LocalDateTime d1 = LocalDateTime.parse(date, inputFormatter);
            by = d1.format(outputFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Deadline time must be in this format: yyyy-mm-dd HH:mm");
        }

    }

    /**
     * Updates the due date of the Deadline Task.
     *
     * @param newDate The new due date.
     * @throws DukeException If the deadline does not follow the format yyyy-MM-dd HH:mm.
     */
    public void updateDeadline(String newDate) throws DukeException {
        try {
            LocalDateTime d1 = LocalDateTime.parse(newDate, inputFormatter);
            by = d1.format(outputFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Deadline time must be in this format: yyyy-mm-dd HH:mm");
        }
    }

    /**
     * Returns the Formatted String to be saved into Storage.
     *
     * @return Formatted String representation of the Deadline task.
     */
    public String exportData() {
        return "D | " + this.getStatusIcon() + " | " + this.description + " | " + this.by;
    }

    /**
     * Returns the String representation of the Deadline task.
     *
     * @return String representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
