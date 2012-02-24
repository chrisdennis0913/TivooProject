import input.Event;
import input.InputParser;
import java.util.Collections;
import java.util.List;
import output.Output;
import processor.Processor;


public class Main
{

    public static void main (String[] args)
    {
        //get list of events
//        InputParser input = InputParser.ParserFactory.generate("DukeBasketBall.xml");
        InputParser input = InputParser.ParserFactory.generate("DukeClubsSample.xml");
        List<Event> eventList = input.getListOfEvents();
        Collections.sort(eventList);
        //processor
        @SuppressWarnings("unused")
        Processor process = new Processor();
        //output
        System.out.println(eventList.get(0).myStart);
        Output o = new Output(eventList);
        o.generateCalendar();
    }
}
