import java.util.GregorianCalendar;


public abstract class Event
{
    public String mySubject;
    public GregorianCalendar myStart;
    public GregorianCalendar myEnd;
    
    
    public String getSubject ()
    {
        return mySubject;
    }
    public GregorianCalendar getStartDate ()
    {
        return myStart;
    }
    public GregorianCalendar getEndDate ()
    {
        return myEnd;
    }
    
    public abstract String getDetailsHTML();
    
    
}
