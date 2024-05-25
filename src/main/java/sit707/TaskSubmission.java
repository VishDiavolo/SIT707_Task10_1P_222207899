package sit707;
import java.util.Date;

public class TaskSubmission {
    // Assuming the presence of a Task and Student class in the project
    public class Task {
        private String taskId;
        private Date deadline;

        public Task(String taskId, Date deadline) {
            this.taskId = taskId;
            this.deadline = deadline;
        }

        public Date getDeadline() {
            return deadline;
        }

        public String getTaskId() {
            return taskId;
        }
    }

    public class Student {
        private String studentId;

        public Student(String studentId) {
            this.studentId = studentId;
        }

        public String getStudentId() {
            return studentId;
        }
    }

    public String submitTask(Student student, Task task, String[] files, Date submissionDate, String comments) {
        if (!isBeforeDeadline(task, submissionDate)) {
            return "Error: Submission past deadline.";
        }

        try {
            storeInDatabase(student, task, files, submissionDate, comments);
            sendConfirmation(student, task);
            notifyTutor(task);
            return "Submission successful!";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private boolean isBeforeDeadline(Task task, Date submissionDate) {
        return submissionDate.before(task.getDeadline());
    }

    private void storeInDatabase(Student student, Task task, String[] files, Date submissionDate, String comments) {
        // Logic to store the submission details in a database
        System.out.println("Storing submission for student " + student.getStudentId() + " for task " + task.getTaskId());
    }

    private void sendConfirmation(Student student, Task task) {
        // Logic to send a confirmation message to the student
        System.out.println("Confirmation sent to student " + student.getStudentId() + " for task " + task.getTaskId());
    }

    private void notifyTutor(Task task) {
        // Logic to notify the tutor about the new submission
        System.out.println("Tutor notified about new submission for task " + task.getTaskId());
    }

    // Main method for demonstration purposes
    public static void main(String[] args) {
        TaskSubmission submission = new TaskSubmission();
        Task task = submission.new Task("PassTask 10.1", new Date()); // Example task with a deadline
        Student student = submission.new Student("222207899"); // Example student
        String[] files = {"SIT707 PassTask 10.1.pdf", "report.docx"};
        Date now = new Date();
        String result = submission.submitTask(student, task, files, now, "Here are my assignment files.");
        System.out.println(result);
    }
}
