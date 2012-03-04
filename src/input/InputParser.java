package input;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public abstract class InputParser
{
    protected List<Event> EventList;
    private Document doc = null;
    protected Stack<Node> stack;


    public List<Event> getListOfEvents ()
    {
        Collections.sort(EventList);
        return EventList;
    }


    protected InputParser (String fileName, String tagType)
    {

        try
        {
            doc = parserXML(new File(fileName));
            EventList = new ArrayList<Event>();
            //visit(doc, 0, EventList);
            NodeList nl = doc.getElementsByTagName(tagType);
            for (int i = 0; i < nl.getLength(); i++)
            {
                if (tagType.equals("row")) EventList.add(parseEvent(nl.item(i).getFirstChild().getNextSibling()));
                else if (tagType.equals("Calendar") || tagType.equals("event") || tagType.equals("entry") || tagType.equals("programme")) EventList.add(parseEvent(nl.item(i)));
            }
        }
        catch (Exception error)
        {
            error.printStackTrace();
        }

    }

    public static class ParserFactory
    {
        public static InputParser generate (String filename)
        {
            if (filename.equals("DukeBasketBall.xml")) return new DukeBasketBallParser();
            else if (filename.equals("NFL.xml")) return new NFLParser();
            else if (filename.equals("DukeClubsSample.xml")) return new DukeClubParser();
            else if (filename.equals("GoogleCalSample.xml")) return new GoogleCalParser();
            else if (filename.equals("tv.xml")) return new TvParser();
            else return null;
        }
    }


    public Document parserXML (File file)
        throws SAXException,
            IOException,
            ParserConfigurationException
    {
        return DocumentBuilderFactory.newInstance()
                                     .newDocumentBuilder()
                                     .parse(file);
    }


    protected Event parseEvent (Node node)
    {
        stack = new Stack<Node>();
        stack.push(node);
        Event event = new Event();
        Calendar startCal = new GregorianCalendar();
        Calendar endCal = new GregorianCalendar();
        Map<String, String> myNodeMap = new HashMap<String, String>();

        while (!stack.isEmpty())
        {
            Node current = stack.pop();
            event = subParsing(current, event, startCal, endCal, myNodeMap);
        }
        String startInfo =
            printMyDate(event.myStart.get(Calendar.YEAR),
                        event.myStart.get(Calendar.MONTH),
                        event.myStart.get(Calendar.DAY_OF_MONTH),
                        event.myStart.get(Calendar.HOUR_OF_DAY),
                        event.myStart.get(Calendar.MINUTE));
        String endInfo =
            printMyDate(event.myEnd.get(Calendar.YEAR),
                        event.myEnd.get(Calendar.MONTH),
                        event.myEnd.get(Calendar.DAY_OF_MONTH),
                        event.myEnd.get(Calendar.HOUR_OF_DAY),
                        event.myEnd.get(Calendar.MINUTE));
        ;
        event.detailMap.put("Start", startInfo);
        event.detailMap.put("Finish", endInfo);
        return event;
    }


    protected abstract Event subParsing (Node current,
                                         Event curEvent,
                                         Calendar startCal,
                                         Calendar endCal,
                                         Map<String, String> myNodeMap);


    protected String printMyDate (int year,
                                  int month,
                                  int date,
                                  int hour,
                                  int minutes)
    {
        String myMonth = convertMonth(month);
        String minute = "" + minutes;
//        String timeOfDay="am";
        if (minutes < 10) minute = "0" + minutes;
//        if (hour>12) {
//            timeOfDay="pm";
//            hour-=12;
//        }
        return hour + ":" + minute + " on " + myMonth + " " + date + ", " +
               year + ".";

    }


    private String convertMonth (int month)
    {
        if (month == 0) return "January";
        else if (month == 1) return "February";
        else if (month == 2) return "March";
        else if (month == 3) return "April";
        else if (month == 4) return "May";
        else if (month == 5) return "June";
        else if (month == 6) return "July";
        else if (month == 7) return "August";
        else if (month == 8) return "September";
        else if (month == 9) return "October";
        else if (month == 10) return "November";
        else return "December";
    }

}
