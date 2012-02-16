import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;


public class Processor {
    public List<Event> timeFrameFinder (List<Event> myEvents, GregorianCalendar start, GregorianCalendar end){
        ArrayList<Event> timeList = new ArrayList<Event> ();
        
        for (Event e: myEvents){
            if ( (e.getStartDate().getTimeInMillis() >= start.getTimeInMillis()) && 
                    (e.getStartDate().getTimeInMillis() <= end.getTimeInMillis()) )
                timeList.add(e);
        }
        return timeList;
    }
    
    public List<Event> keywordFinder (List<Event> myEvents, String keyword){
        ArrayList<Event> keyList = new ArrayList<Event> ();
        
        for (Event e: myEvents){
            if (e.getSubject().startsWith(keyword)){
                keyList.add(e);
            }
        }
        return keyList; 
    }
    
    public static void main (String[] args){
        InputParser input = new InputParser(); 
        
        List<Event> listofEvents = input.getListOfEvents();
        Processor process = new Processor ();
        
        List<Event> returnList = process.keywordFinder(listofEvents, "Duke");
        for (Event e: returnList){
            System.out.println (e.getSubject());
        }
    }
    
}
