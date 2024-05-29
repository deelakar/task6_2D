package onTrack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import onTrack.models.Task;

public class FeedbackGeneratorTest {
    @Test
    public void testGenerateFeedbackWithEmptyContent() {
        FeedbackGenerator generator = new FeedbackGenerator();
        Task emptyTask = new Task("1", "");
        String feedback = generator.generateFeedback(emptyTask);
        assertEquals("Your task content is empty.", feedback);
    }

    @Test
    public void testGenerateFeedbackWithNullContent() {
        FeedbackGenerator generator = new FeedbackGenerator();
        Task nullTask = new Task("2", null);
        String feedback = generator.generateFeedback(nullTask);
        assertEquals("Your task content is empty.", feedback);
    }

    @Test
    public void testGenerateFeedbackWithValidContent() {
        FeedbackGenerator generator = new FeedbackGenerator();
        Task correctTask = new Task("3", "This task is correct.");
        String feedback = generator.generateFeedback(correctTask);
        assertEquals("Your task is correct.", feedback);
    }

    @Test
    public void testGenerateFeedbackWithErrors() {
        FeedbackGenerator generator = new FeedbackGenerator();
        Task taskWithErrors = new Task("4", "This task has an error.");
        String feedback = generator.generateFeedback(taskWithErrors);
        assertEquals("Your task contains errors.", feedback);
    }

    @Test
    public void testGenerateFeedbackWithMissingComponents() {
        FeedbackGenerator generator = new FeedbackGenerator();
        Task taskWithMissingComponents = new Task("5", "This task is missing components.");
        String feedback = generator.generateFeedback(taskWithMissingComponents);
        assertEquals("Your task is missing some components.", feedback);
    }

    @Test
    public void testGenerateFeedbackWithIncorrectInfo() {
        FeedbackGenerator generator = new FeedbackGenerator();
        Task taskWithIncorrectInfo = new Task("6", "This task contains incorrect info.");
        String feedback = generator.generateFeedback(taskWithIncorrectInfo);
        assertEquals("Your task contains incorrect information.", feedback);
    }

    @Test
    public void testFeedbackConsistency() {
        FeedbackGenerator generator = new FeedbackGenerator();
        Task correctTask = new Task("7", "This task is correct.");
        for (int i = 0; i < 10; i++) {
            String feedback = generator.generateFeedback(correctTask);
            assertEquals("Your task is correct.", feedback);
        }
    }

    @Test
    public void testPerformanceUnderLoad() {
        FeedbackGenerator generator = new FeedbackGenerator();
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            tasks.add(new Task(String.valueOf(i), "Task content " + i));
        }
        long startTime = System.currentTimeMillis();
        for (Task task : tasks) {
            generator.generateFeedback(task);
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        assertTrue("Performance test failed. Duration: " + duration, duration < 1000);
    }

    @Test
    public void testInverseOperations() {
        FeedbackGenerator generator = new FeedbackGenerator();
        Task correctTask = new Task("8", "This task is correct.");
        String feedbackCorrect = generator.generateFeedback(correctTask);
        assertEquals("Your task is correct.", feedbackCorrect);

        Task incorrectTask = new Task("8", "This task contains errors.");
        String feedbackIncorrect = generator.generateFeedback(incorrectTask);
        assertEquals("Your task contains errors.", feedbackIncorrect);
    }
}
