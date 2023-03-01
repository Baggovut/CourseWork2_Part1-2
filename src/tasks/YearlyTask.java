package tasks;

import tasks.enums.Type;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class YearlyTask extends Task{
    public YearlyTask(String title, String description, Type type, LocalDateTime dateTime) {
        super(title, description, type, dateTime);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return getDateTime().getYear() <= localDate.getYear()
                && getDateTime().getMonth() == localDate.getMonth()
                && getDateTime().getDayOfMonth() == localDate.getDayOfMonth();
    }
}
