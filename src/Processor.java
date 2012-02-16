import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;


public class Processor {
    public List<Event> timeFrameFinder (List<Event> myEvents, GregorianCalendar start, GregorianCalendar end){
        List<Event> timeList = new ArrayList<Event> ();
        
        for (Event e: myEvents){
            if ( (e.getStartDate().getTimeInMillis() >= start.getTimeInMillis()) && 
                    (e.getStartDate().getTimeInMillis() <= end.getTimeInMillis()) )
                timeList.add(e);
        }
        return timeList;
    }
    
    public List<Event> keywordFinder (List<Event> myEvents, String keyword){
        List<Event> keyList = new ArrayList<Event> ();
        
        for (Event e: myEvents){
            if (e.getSubject().contains(keyword)){
                keyList.add(e);
            }
        }
        return keyList; 
    }
    
    public static void main (String[] args){
        DukeBasketBallParser input = new DukeBasketBallParser(); 
        
        List<Event> listofEvents = input.getListOfEvents();
        Processor process = new Processor ();
        
        List<Event> returnList = process.keywordFinder(listofEvents, "Duke");
        for (Event e: returnList){
            System.out.println (e.getSubject());
        }
    }
    
}
