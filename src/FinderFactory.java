import java.util.GregorianCalendar;


public class FinderFactory {
    public KeyWordFinder CreateKeyWordFinder (String keyword){
        return new KeyWordFinder(keyword); 
    }
    
    public TimeFrameFinder CreateTimeFrameFinder (GregorianCalendar start, GregorianCalendar end){
        return new TimeFrameFinder(start, end); 
    }

}
