package input;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import org.w3c.dom.Node;


public class NFLParser extends InputParser
{
    public NFLParser ()
    {
        super("NFL.xml", "row");
    }


    public Event subParsing (Node node,
                             Event event,
                             Calendar startCal,
                             Calendar endCal,
                             Map<String, String> xmlTVNodeMap)
    {

        xmlTVNodeMap.put("Col2", "Source");
        xmlTVNodeMap.put("Col3", "Season");
        xmlTVNodeMap.put("Col15", "Location");

        //check if it is one of the categories you want, and populate the corresponding field in event
        String nodeName = node.getNodeName();
        String nodeText = node.getTextContent();

        if (nodeName.equals("Col1"))
        { //subject
            event.mySubject = nodeText;
            event.detailMap.put("Description", nodeText);
        }

        else if (nodeName.equals("Col8"))
        { //start time and date
            startCal = parseDateAndTime(startCal, nodeText);
        }
        else if (nodeName.equals("Col9"))
        { //end time and date
            endCal = parseDateAndTime(endCal, nodeText);
        }
        else if (xmlTVNodeMap.containsKey(nodeName)) event.detailMap.put(xmlTVNodeMap.get(nodeName),
                                                                         nodeText);

        if (node.getNextSibling() != null) if (node.getNextSibling()
                                                   .getNextSibling() != null) stack.push(node.getNextSibling()
                                                                                             .getNextSibling());

        event.myStart = (GregorianCalendar) startCal;
        event.myEnd = (GregorianCalendar) endCal;

        return event;

    }


    private Calendar parseDateAndTime (Calendar myCal, String myText)
    {
        String[] dateTime = myText.split("\\s+");
        String[] date = dateTime[0].split("-");
        myCal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[2]));
        myCal.set(Calendar.MONTH, Integer.parseInt(date[1])-1);
        myCal.set(Calendar.YEAR, Integer.parseInt(date[0]));
        String[] hms = dateTime[1].split(":");
        myCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hms[0]));
        myCal.set(Calendar.MINUTE, Integer.parseInt(hms[1]));
        myCal.set(Calendar.SECOND, Integer.parseInt(hms[2]));
        return myCal;
    }

}
