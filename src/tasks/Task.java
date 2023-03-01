package tasks;

import exceptions.IncorrectArgumentException;
import tasks.enums.Type;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int idGenerator = 0;
    private int id;
    private String title, description;
    private LocalDateTime dateTime;
    private Type type;

    public Task(String title, String description,Type type, LocalDateTime dateTime) {
        setTitle(title);
        setDescription(description);
        this.dateTime = dateTime;
        this.type = type;

        this.id = idGenerator;
        idGenerator++;
    }

    @Override
    public String toString() {
        return "Task{" +
                "ID=" + getId() +
                ", заголовок='" + getTitle() + '\'' +
                ", описание='" + getDescription() + '\'' +
                ", время=" + getDateTime() +
                ", тип=" + getType() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return getId() == task.getId() && getTitle().equals(task.getTitle()) && getDescription().equals(task.getDescription()) && getDateTime().equals(task.getDateTime()) && getType() == task.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getDateTime(), getType());
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Type getType() {
        return type;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()){
            throw new IncorrectArgumentException(title);
        }else{
            this.title = title;
        }
    }

    public void setDescription(String description) {
        if (description  == null || description .isBlank()){
            throw new IncorrectArgumentException(description );
        }else{
            this.description  = description ;
        }
    }

    public boolean appearsIn(LocalDate localDate){
        System.out.println("stub");
        return false;
    }
}
