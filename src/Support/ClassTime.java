package Support;
import java.time.DayOfWeek;
import java.time.LocalTime;

//represents when a section of a course occurs (e.g. Mon 9:00--10:00)
public class ClassTime {
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    //TODO: a constructor converting Strings into types needed

    public ClassTime(){

    }

    public ClassTime(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime){
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    //check if there is a time conflict between 2 ClassTime
    // 3 possible situations for *no* conflict :
    //              - on different day of the week
    //              - this starts equals/after the other ends
    //              - this ends equals/before the other starts
    public boolean hasTimeConflict(ClassTime theOther){
        return  !(dayOfWeek != theOther.dayOfWeek || !startTime.isBefore(theOther.endTime)
                                                               ||!endTime.isAfter(theOther.startTime));
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return  dayOfWeek + " " + startTime + " "+ endTime;
    }
}
