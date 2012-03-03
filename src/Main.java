import input.Event;
import input.InputParser;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import output.GenerateCalendar;
import output.Output;
import processor.Processor;


public class Main
{

    public static void main (String[] args)
    {
        //get list of events
        InputParser input =      InputParser.ParserFactory.generate("DukeBasketBall.xml");
        InputParser inputC = InputParser.ParserFactory.generate("NFL.xml");
        InputParser inputB = InputParser.ParserFactory.generate("GoogleCalSample.xml");
        InputParser inputD = InputParser.ParserFactory.generate("tv.xml");
        InputParser inputE = InputParser.ParserFactory.generate("DukeClubsSample.xml");

        List<Event> eventList = input.getListOfEvents();
        eventList.addAll(inputB.getListOfEvents());
        eventList.addAll(inputC.getListOfEvents());
        
        List<Event> xmlEventList = inputD.getListOfEvents();
        eventList.addAll(xmlEventList);
        eventList.addAll(inputE.getListOfEvents());
        Collections.sort(eventList);
        Collections.sort(xmlEventList);

        //processor

        Processor process = new Processor(eventList);


        // -- not sure exactly how to call this right now but once processor is done should be easy
        //Sorting sort = new Sorting(eventList);
        //sort.sorting(EventList); 

        //output
        int year = 2;
        int month = 0;
        int date = 1;
        GregorianCalendar start = new GregorianCalendar(year, month, date);
        int eYear = 2012;
        int eMonth = 1;
        int eDate = 30;
        GregorianCalendar end = new GregorianCalendar(eYear, eMonth, eDate);

        GenerateCalendar o = new GenerateCalendar(eventList);
//        o.dayWeekMonth(start, end);
        //o.sortedList();
        //o.conflictList();
        o.generate(start,end);

    }

}
