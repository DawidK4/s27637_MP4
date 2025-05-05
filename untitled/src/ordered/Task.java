package ordered;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Task {
    private String name;
    private Duration duration;

    private List<DailySchedule> isIncludedIn;

    public Task(String name, Duration duration) {
        setName(name);
        setDuration(duration);
        this.isIncludedIn = new ArrayList<>();
    }

    public void addDailySchedule(DailySchedule dailySchedule){
        if (dailySchedule == null) {
            throw new IllegalArgumentException("Daily schedule must not be null!");
        }

        if (isIncludedIn.contains(dailySchedule)) {
            throw new IllegalArgumentException("Task is already assigned to this schedule!");
        }

        isIncludedIn.add(dailySchedule);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Task name cannot be null or empty.");
        }
        this.name = name;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        if (duration == null || duration.isNegative()) {
            throw new IllegalArgumentException("Duration cannot be null or negative.");
        }
        this.duration = duration;
    }

    public static Duration fromHoursMinutesSeconds(long hours, long minutes, long seconds) {
        return Duration.ofHours(hours).plusMinutes(minutes).plusSeconds(seconds);
    }
}
