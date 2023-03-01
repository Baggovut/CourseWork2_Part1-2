package tasks;

import tasks.enums.Type;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task{
    public WeeklyTask(String title, String description, Type type, LocalDateTime dateTime) {
        super(title, description, type, dateTime);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return getDateTime().getDayOfWeek() == localDate.getDayOfWeek();
    }
}
