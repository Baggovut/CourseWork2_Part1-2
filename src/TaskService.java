import exceptions.TaskNotFoundException;
import tasks.Task;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskService implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<Integer, Task> taskMap;
    private Collection<Task> removedTasks;

    public TaskService() {
        this.taskMap = new HashMap<>();
        this.removedTasks = new ArrayList<>();
    }

    public void add(Task task){
        if (task == null){
            throw new TaskNotFoundException();
        }else {
            taskMap.put(task.getId(), task);
        }
    }

    public Task remove(int id){
        Task task1 = taskMap.get(id);
        if(task1 == null){
            throw new TaskNotFoundException();
        }else {
            removedTasks.add(task1);
            taskMap.remove(id);
        }
        return task1;
    }

    public Collection<Task> getAllByDate(LocalDate localDate){
        Collection<Task> tasks1 = new ArrayList<>();
        taskMap.entrySet().stream()
                .filter(
                        x -> x.getValue().appearsIn(localDate)
                )
                .forEach(x -> tasks1.add(x.getValue()));
        return tasks1;
    }

    public Task updateDescription(int id, String description){
        Task task1 = taskMap.get(id);
        if (task1 == null){
            throw new TaskNotFoundException();
        }else {
            taskMap.get(id).setDescription(description);
        }
        return task1;
    }

    public Task updateTitle(int id, String title){
        Task task1 = taskMap.get(id);
        if (task1 == null){
            throw new TaskNotFoundException();
        }else {
            taskMap.get(id).setTitle(title);
        }
        return task1;
    }

    public Collection<Task> getRemovedTasks(){
        return removedTasks;
    }

    public Map<LocalDate,Collection<Task>> getAllGroupedByDate(){
        return taskMap.entrySet().stream()
                .sorted(Comparator.comparing(entry -> entry.getValue().getDateTime()))
                .collect(
                        Collectors.groupingBy(
                                entry -> LocalDate.of(entry.getValue().getDateTime().getYear(),
                                        entry.getValue().getDateTime().getMonth(),
                                        entry.getValue().getDateTime().getDayOfMonth()),
                                Collectors.flatMapping(
                                        entry -> Stream.of(entry.getValue()),
                                        Collectors.toCollection(ArrayList::new)
                                )
                        )
                );
    }
}
