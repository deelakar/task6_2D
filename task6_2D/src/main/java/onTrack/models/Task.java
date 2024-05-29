package onTrack.models;

public class Task {
    private String id;
    private String content;
    private int priority;

    public Task(String id, String content) {
        this.id = id;
        this.content = content;
        this.priority = 0; 
    }

    public Task(String id, String content, int priority) {
        this.id = id;
        this.content = content;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
