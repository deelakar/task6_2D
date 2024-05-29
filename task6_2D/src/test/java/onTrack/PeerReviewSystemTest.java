package onTrack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import onTrack.models.Review;
import onTrack.models.Task;
import org.junit.Test;

import java.util.List;

public class PeerReviewSystemTest {
    @Test
    public void testSubmitReview() {
        PeerReviewSystem system = new PeerReviewSystem();
        Review review = new Review("1", "Good job", "task1", "reviewer1");
        system.submitReview(review);
        List<Review> reviews = system.getReviewsForTask(new Task("task1", "This is a task"));
        assertEquals(1, reviews.size());
        assertEquals("Good job", reviews.get(0).getComment());
    }

    @Test
    public void testGetReviewsForTask() {
        PeerReviewSystem system = new PeerReviewSystem();
        Task task = new Task("task1", "This is a task");
        Review review1 = new Review("1", "Good job", task.getId(), "reviewer1");
        Review review2 = new Review("2", "Needs improvement", task.getId(), "reviewer2");

        system.submitReview(review1);
        system.submitReview(review2);

        List<Review> reviews = system.getReviewsForTask(task);

        assertEquals(2, reviews.size());
        assertEquals("Good job", reviews.get(0).getComment());
        assertEquals("Needs improvement", reviews.get(1).getComment());
    }

    @Test
    public void testGetReviewsByReviewer() {
        PeerReviewSystem system = new PeerReviewSystem();
        Task task1 = new Task("task1", "This is a task 1");
        Task task2 = new Task("task2", "This is a task 2");
        Review review1 = new Review("1", "Good job", task1.getId(), "reviewer1");
        Review review2 = new Review("2", "Needs improvement", task2.getId(), "reviewer1");
        Review review3 = new Review("3", "Well done", task1.getId(), "reviewer2");

        system.submitReview(review1);
        system.submitReview(review2);
        system.submitReview(review3);

        List<Review> reviews = system.getReviewsByReviewer("reviewer1");

        assertEquals(2, reviews.size());
        assertEquals("Good job", reviews.get(0).getComment());
        assertEquals("Needs improvement", reviews.get(1).getComment());
    }

    @Test
    public void testNoReviewsForTask() {
        PeerReviewSystem system = new PeerReviewSystem();
        Task task = new Task("task3", "This is another task");

        List<Review> reviews = system.getReviewsForTask(task);

        assertEquals(0, reviews.size());
    }

    @Test
    public void testMultipleReviewsForTask() {
        PeerReviewSystem system = new PeerReviewSystem();
        Task task = new Task("task1", "This is a task");
        Review review1 = new Review("1", "Good job", task.getId(), "reviewer1");
        Review review2 = new Review("2", "Needs improvement", task.getId(), "reviewer2");
        Review review3 = new Review("3", "Well done", task.getId(), "reviewer3");

        system.submitReview(review1);
        system.submitReview(review2);
        system.submitReview(review3);

        List<Review> reviews = system.getReviewsForTask(task);

        assertEquals(3, reviews.size());
        assertEquals("Good job", reviews.get(0).getComment());
        assertEquals("Needs improvement", reviews.get(1).getComment());
        assertEquals("Well done", reviews.get(2).getComment());
    }

    @Test
    public void testPerformanceUnderLoad() {
        PeerReviewSystem system = new PeerReviewSystem();
        Task task = new Task("task1", "This is a task");
        for (int i = 0; i < 1000; i++) {
            system.submitReview(new Review(String.valueOf(i), "Review " + i, task.getId(), "reviewer" + i));
        }
        long startTime = System.currentTimeMillis();
        List<Review> reviews = system.getReviewsForTask(task);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        assertTrue("Performance test failed. Duration: " + duration, duration < 1000);
    }

    @Test
    public void testInverseOperations() {
        PeerReviewSystem system = new PeerReviewSystem();
        Task task = new Task("task1", "This is a task");
        Review review = new Review("1", "Good job", task.getId(), "reviewer1");
        system.submitReview(review);
        system.removeReview(review);
        List<Review> reviews = system.getReviewsForTask(task);
        assertEquals(0, reviews.size());
    }
}
