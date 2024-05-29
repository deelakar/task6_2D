package onTrack;

import onTrack.models.Review;
import onTrack.models.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PeerReviewSystem {

    private List<Review> reviews = new ArrayList<>();

    public void submitReview(Review review) {
        reviews.add(review);
    }

    public List<Review> getReviewsForTask(Task task) {
        return reviews.stream()
                .filter(review -> review.getTaskId().equals(task.getId()))
                .collect(Collectors.toList());
    }

    public List<Review> getReviewsByReviewer(String reviewerId) {
        return reviews.stream()
                .filter(review -> review.getReviewerId().equals(reviewerId))
                .collect(Collectors.toList());
    }
    
    public void removeReview(Review review) {
        reviews.remove(review);
    }
}
