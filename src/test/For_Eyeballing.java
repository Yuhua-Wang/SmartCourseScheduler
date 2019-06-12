import Support.ClassTime;
import java.time.*;
import static java.time.DayOfWeek.*;


public class For_Eyeballing {
    public static void main(String[] args) {
        LocalTime st = LocalTime.of(13,0);
        LocalTime et = LocalTime.of(14,0);
        ClassTime c = new ClassTime();
        c.setDayOfWeek(MONDAY);
        c.setStartTime(st);
        c.setEndTime(et);
        System.out.println(c);
    }
}

