package onTrack.models;

public class Review {


	    private String id;
	    private String comment;
	    private String taskId;
	    private String reviewerId;

	    public Review(String id, String comment, String taskId, String reviewerId) {
	        this.id = id;
	        this.comment = comment;
	        this.taskId = taskId;
	        this.reviewerId = reviewerId;
	    }

	    public String getId() {
	        return id;
	    }

	    public String getComment() {
	        return comment;
	    }

	    public String getTaskId() {
	        return taskId;
	    }

	    public String getReviewerId() {
	        return reviewerId;
	    }
	}

