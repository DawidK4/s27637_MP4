package ordered;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DailySchedule {
    private LocalDate date;

    private List<Task> includes;

    public DailySchedule(LocalDate date) {
        setDate(date);
        this.includes = new ArrayList<>();
    }

    public void addTask(Task newTask) {
        if (newTask == null) {
            throw new IllegalArgumentException("New task must not be null!");
        }

        if (includes.contains(newTask)) {
            throw new IllegalArgumentException("Daily schedule already contains this task!");
        }

        includes.add(newTask);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }
        this.date = date;
    }
}
