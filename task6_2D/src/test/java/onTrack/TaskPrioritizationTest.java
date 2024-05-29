package onTrack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import onTrack.models.Task;
import org.junit.Test;

import java.util.List;

public class TaskPrioritizationTest {
	@Test
    public void testAddTask() {
        TaskPrioritization prioritization = new TaskPrioritization();
        Task task = new Task("1", "Task 1", 0);

        prioritization.addTask(task);

        List<Task> tasks = prioritization.getTasks();

        assertEquals(1, tasks.size());
        assertEquals("Task 1", tasks.get(0).getContent());
    }

	@Test
    public void testPrioritizeTaskToTop() {
        TaskPrioritization prioritization = new TaskPrioritization();
        Task task1 = new Task("1", "Task 1", 1);
        Task task2 = new Task("2", "Task 2", 2);

        prioritization.addTask(task1);
        prioritization.addTask(task2);

        prioritization.prioritizeTask(task1, 0);
        prioritization.sortTasksByPriority();

        List<Task> tasks = prioritization.getTasks();

        assertEquals("Task 1", tasks.get(0).getContent());
    }

	@Test
    public void testPrioritizeTaskToMiddle() {
        TaskPrioritization prioritization = new TaskPrioritization();
        Task task1 = new Task("1", "Task 1", 0);
        Task task2 = new Task("2", "Task 2", 2);
        Task task3 = new Task("3", "Task 3", 1);

        prioritization.addTask(task1);
        prioritization.addTask(task2);
        prioritization.addTask(task3);

        prioritization.prioritizeTask(task3, 1);
        prioritization.sortTasksByPriority();

        List<Task> tasks = prioritization.getTasks();

        assertEquals("Task 1", tasks.get(0).getContent());
        assertEquals("Task 3", tasks.get(1).getContent());
        assertEquals("Task 2", tasks.get(2).getContent());
    }

	@Test
    public void testRetrieveTasksSortedByPriority() {
        TaskPrioritization prioritization = new TaskPrioritization();
        Task task1 = new Task("1", "Task 1", 2);
        Task task2 = new Task("2", "Task 2", 0);
        Task task3 = new Task("3", "Task 3", 1);

        prioritization.addTask(task1);
        prioritization.addTask(task2);
        prioritization.addTask(task3);

        prioritization.sortTasksByPriority();
        List<Task> tasks = prioritization.getTasks();

        assertEquals("Task 2", tasks.get(0).getContent());
        assertEquals("Task 3", tasks.get(1).getContent());
        assertEquals("Task 1", tasks.get(2).getContent());
    }

	@Test
    public void testSortTasksByPriority() {
        TaskPrioritization prioritization = new TaskPrioritization();
        Task task1 = new Task("1", "Task 1", 2);
        Task task2 = new Task("2", "Task 2", 0);
        Task task3 = new Task("3", "Task 3", 1);

        prioritization.addTask(task1);
        prioritization.addTask(task2);
        prioritization.addTask(task3);

        prioritization.sortTasksByPriority();
        List<Task> tasks = prioritization.getTasks();

        assertEquals("Task 2", tasks.get(0).getContent());
        assertEquals("Task 3", tasks.get(1).getContent());
        assertEquals("Task 1", tasks.get(2).getContent());
    }

    @Test
    public void testPerformanceUnderLoad() {
        TaskPrioritization prioritization = new TaskPrioritization();
        for (int i = 0; i < 1000; i++) {
            prioritization.addTask(new Task(String.valueOf(i), "Task " + i));
        }
        long startTime = System.currentTimeMillis();
        prioritization.getTasksSortedByPriority();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        assertTrue("Performance test failed. Duration: " + duration, duration < 1000);
    }

    @Test
    public void testInverseOperations() {
        TaskPrioritization prioritization = new TaskPrioritization();
        Task task1 = new Task("1", "Task 1");
        Task task2 = new Task("2", "Task 2");

        prioritization.addTask(task1);
        prioritization.addTask(task2);
        prioritization.prioritizeTask(task1, 1);
        prioritization.prioritizeTask(task2, 0);

        List<Task> tasks = prioritization.getTasksSortedByPriority();
        assertEquals("Task 2", tasks.get(0).getContent());
        assertEquals("Task 1", tasks.get(1).getContent());

        prioritization.prioritizeTask(task1, 0);
        tasks = prioritization.getTasksSortedByPriority();
        assertEquals("Task 1", tasks.get(0).getContent());
        assertEquals("Task 2", tasks.get(1).getContent());
    }
    

}
