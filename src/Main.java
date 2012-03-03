import input.Event;
import input.InputParser;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import output.GenerateCalendar;
import output.*;


public class Main
{

    public static void main (String[] args)
    {
        //get list of events
        InputParser input =      InputParser.ParserFactory.generate("DukeBasketBall.xml");
      //  InputParser inputC = InputParser.ParserFactory.generate("NFL.xml");
       // InputParser inputB = InputParser.ParserFactory.generate("GoogleCalSample.xml");
        InputParser inputD =
            InputParser.ParserFactory.generate("tv.xml");

        List<Event> eventList = input.getListOfEvents();
        //eventList.addAll(inputB.getListOfEvents());
        //eventList.addAll(inputC.getListOfEvents());
        
        List<Event> xmlEventList = inputD.getListOfEvents();
        eventList.addAll(xmlEventList);
        Collections.sort(eventList);
        Collections.sort(xmlEventList);


        // -- not sure exactly how to call this right now but once processor is done should be easy
        //Sorting sort = new Sorting(eventList);
        //sort.sorting(EventList); 

        //output
        int year = 2011;
        int month = 9;
        int date = 1;
        GregorianCalendar start = new GregorianCalendar(year, month, date);
        int eYear = 2012;
        int eMonth = 1;
        int eDate = 30;
        GregorianCalendar end = new GregorianCalendar(eYear, eMonth, eDate);

        GenerateCalendar o = new GenerateCalendar(eventList);
        DayWeekMonth dwm = new DayWeekMonth(eventList);
        SortedList s = new SortedList(eventList);
        ConflictList c = new ConflictList(eventList);
        dwm.generate(start, end);
        s.generate(start,end);
        c.generate(start,end);
        o.generate(start, end);

    }

}
