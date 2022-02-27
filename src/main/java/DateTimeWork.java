import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public final class DateTimeWork {
    public static int getDayOfWeek(){
        ZoneId timezone = ZoneId.of("Asia/Yekaterinburg");
        ZonedDateTime localDate = ZonedDateTime.now(timezone);
        return localDate.getDayOfWeek().getValue() - 1;
    }
    public static String getTimeNow(){
        ZoneId timezone = ZoneId.of("Asia/Yekaterinburg");
        ZonedDateTime localDate = ZonedDateTime.now(timezone);
        return localDate.getHour() + ":" + localDate.getMinute();
    }
    public static String normalizeTime(String time){
        if (time.length() == 4)
            time = 0 + time;
        return time;
    }
    public static boolean timeCompare(String time){
        ZoneId timezone = ZoneId.of("Asia/Yekaterinburg");
        ZonedDateTime localDate = ZonedDateTime.now(timezone);
        String[] times = time.split(":");
        return (Integer.parseInt(times[0]) >= localDate.getHour() || Integer.parseInt(times[1]) >= localDate.getMinute());
    }
    public static int checkWeekNumber(){
        ZoneId timezone = ZoneId.of("Asia/Yekaterinburg");
        ZonedDateTime dateNow = ZonedDateTime.now(timezone);
        LocalDate localDate = LocalDate.of(2022,01,31);
        if(((dateNow.getDayOfYear() - localDate.getDayOfYear()) / 7) % 2 == 0)
            return 1;
        else
            return 2;
    }
}
