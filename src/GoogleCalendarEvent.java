import java.util.GregorianCalendar;


public class GoogleCalendarEvent extends Event
{
    // the subject for these is under title
    public boolean myIsAllDayEvent;
    // deal with all day events
    public String myDescription;
    public GregorianCalendar myPublished; // date and time published
    public GregorianCalendar myUpdated; // date and time updated
    public String mySensitivity; // a level like normal
    public String myShowTimeAs; // no clue, but normally 2

    public GoogleCalendarEvent(){
    
    }

//    public DukeBasketballEvent (String subject,
//                  int startYear,
//                  int startMonth,
//                  int startDay,
//                  int startHour,
//                  int startMinute,
//                  int endYear,
//                  int endMonth,
//                  int endDay,
//                  int endHour,
//                  int endMinute,
//                  String description,
//                  String location,
//                  String priority,
//                  String privacy,
//                  String reminderOnOff,
//                  int reminderYear,
//                  int reminderMonth,
//                  int reminderDay,
//                  int reminderHour,
//                  int reminderMinute,
//                  String sensitivity,
//                  String showTimeAs)
//    {
//        mySubject = subject;
//        myStart.set(startYear, startMonth, startDay, startHour, startMinute);
//        myEnd.set(endYear, endMonth, endDay, endHour, endMinute);
//
//        myReminder = null;
//        if (reminderOnOff.equals("On")) myReminder.set(reminderYear,
//                                                       reminderMonth,
//                                                       reminderDay,
//                                                       reminderHour,
//                                                       reminderMinute);
//        myDescription = description;
//        myLocation = location;
//        myPriority = priority;
//        myPrivacy = privacy;
//        mySensitivity = sensitivity;
//        myShowTimeAs = showTimeAs;
//    }



}
