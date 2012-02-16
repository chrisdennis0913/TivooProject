import java.util.Calendar;
import java.util.GregorianCalendar;


public abstract class Event implements Comparable<Event>
{
    public String mySubject;
    public GregorianCalendar myStart;
    public GregorianCalendar myEnd;
    
    
	public int compareTo(Event other) {
		
		if(myStart.get(Calendar.YEAR) < other.myStart.get(Calendar.YEAR)) return -1;
		else if(myStart.get(Calendar.YEAR) > other.myStart.get(Calendar.YEAR)) return 1;
			
		if(myStart.get(Calendar.MONTH) < other.myStart.get(Calendar.MONTH)) return -1;
		else if(myStart.get(Calendar.MONTH) > other.myStart.get(Calendar.MONTH)) return 1;
		
		if(myStart.get(Calendar.DAY_OF_MONTH) < other.myStart.get(Calendar.DAY_OF_MONTH)) return -1;
		else if(myStart.get(Calendar.DAY_OF_MONTH) > other.myStart.get(Calendar.DAY_OF_MONTH)) return 1;

		// TODO Auto-generated method stub
		return 0;
	}
    
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
    
    public abstract String generateDetailsHTML();

    
    
}
