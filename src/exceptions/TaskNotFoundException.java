package exceptions;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException() {
        super("Задание не существует");
    }
}
