package tasks;

import tasks.enums.Type;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonthlyTask extends Task{
    public MonthlyTask(String title, String description, Type type, LocalDateTime dateTime) {
        super(title, description, type, dateTime);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return getDateTime().getDayOfMonth() == localDate.getDayOfMonth();
    }
}
