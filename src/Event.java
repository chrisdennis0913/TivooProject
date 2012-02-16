import java.util.GregorianCalendar;


public abstract class Event
{
    public String mySubject;
    public String getSubject ()
    {
        return mySubject;
    }
    public GregorianCalendar myStart;
    public GregorianCalendar myEnd;
    public GregorianCalendar getStartDate ()
    {
        return myStart;
    }
    public GregorianCalendar getEndDate ()
    {
        return myEnd;
    }
}
