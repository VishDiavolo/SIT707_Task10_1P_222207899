package sit707;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.Date;
import java.util.Calendar;

public class TaskSubmissionTest {
    private TaskSubmission submission;
    private TaskSubmission.Student student;
    private TaskSubmission.Task task;
    private String[] files;
    private Date validDate;
    private Date invalidDate;

    @BeforeEach
    public void setUp() {
        submission = new TaskSubmission();
        student = submission.new Student("222207899");
        files = new String[] {"SIT707 PassTask 10.1.pdf", "report.docx"};

        // Setting up dates for the tests
        Calendar calendar = Calendar.getInstance();
        validDate = calendar.getTime(); // Current date

        calendar.add(Calendar.DATE, -1); // One day before the current date
        invalidDate = calendar.getTime();

        calendar.add(Calendar.DATE, 2); // One day after the current date
        Date deadline = calendar.getTime();
        task = submission.new Task("Math101", deadline);
    }

    @Test
    public void testSubmitTaskBeforeDeadline() {
        String result = submission.submitTask(student, task, files, validDate, "Submission before deadline.");
        assertEquals("Submission successful!", result, "Task should be submitted successfully before the deadline.");
    }

    @Test
    public void testSubmitTaskPastDeadline() {
        String result = submission.submitTask(student, task, files, invalidDate, "Submission past deadline.");
        assertEquals("Error: Submission past deadline.", result);
    }

    @Test
    public void testSubmitTaskWithNullFiles() {
        Exception exception = assertThrows(Exception.class, () -> {
            submission.submitTask(student, task, null, validDate, "No files provided.");
        });
        assertTrue(exception.getMessage().contains("files"), "Should throw an exception when files are null.");
    }
}

