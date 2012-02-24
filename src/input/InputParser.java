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
        {
            try
            {
                doc = parserXML(new File(fileName));
                EventList = new ArrayList<Event>();
                //visit(doc, 0, EventList);
                NodeList nl = doc.getElementsByTagName(tagType);
                for (int i = 0; i < nl.getLength(); i++)
                {
                    if (tagType.equals("Calendar")) EventList.add(parseEvent(nl.item(i)));
                    else if (tagType.equals("row")) EventList.add(parseEvent(nl.item(i)
                                                                               .getFirstChild()
                                                                               .getNextSibling()));
                    else if (tagType.equals("event")) EventList.add(parseEvent(nl.item(i)));
                }
            }
            catch (Exception error)
            {
                error.printStackTrace();
            }
        }
    }

    public static class ParserFactory
    {
        public static InputParser generate (String filename)
        {
            if (filename.equals("DukeBasketBall.xml")) return new DukeBasketBallParser();
            else if (filename.equals("NFL.xml")) return new XMLtvParser();
            else if (filename.equals("DukeClubsSample.xml")) return new DukeClubParser();
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
        return event;
    }


    protected abstract Event subParsing (Node current,
                                         Event curEvent,
                                         Calendar startCal,
                                         Calendar endCal,
                                         Map<String, String> myNodeMap);

}
