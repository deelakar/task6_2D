package onTrack;

import onTrack.models.Task;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskPrioritization {
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void prioritizeTask(Task task, int priority) {
        task.setPriority(priority);
        if (!tasks.contains(task)) {
            tasks.add(task);
        }
    }

    public List<Task> getTasksSortedByPriority() {
        List<Task> sortedTasks = new ArrayList<>(tasks);
        sortedTasks.sort(Comparator.comparingInt(Task::getPriority));
        return sortedTasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void sortTasksByPriority() {
        tasks.sort(Comparator.comparingInt(Task::getPriority));
    }
}