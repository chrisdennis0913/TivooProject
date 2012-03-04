package input;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class DukeBasketBallParser extends InputParser
{
    public DukeBasketBallParser ()
    {
        super("DukeBasketBall.xml", "Calendar");
    }


    public Event subParsing (Node node,
                             Event curEvent,
                             Calendar startCal,
                             Calendar endCal,
                             Map<String, String> bBallNodeMap)
    {
        bBallNodeMap.put("Description", "Description");
        bBallNodeMap.put("Location", "Location");
        bBallNodeMap.put("Priority", "Priority");
        bBallNodeMap.put("Private", "Private");
        bBallNodeMap.put("Sensitivity", "Sensitivity");
        bBallNodeMap.put("Showtimeas", "Showtimeas");
        bBallNodeMap.put("Description", "Description");

        //check if it is one of the categories you want, and populate the corresponding field in event
        String nodeName = node.getNodeName();
        String nodeText = node.getTextContent();

        if (nodeName.equals("Subject")) curEvent.mySubject = nodeText;
        else if (nodeName.equals("StartDate"))
        {
            int[] times = parseDates(nodeText, "/");
            startCal.set(Calendar.DAY_OF_MONTH, times[1]);
            startCal.set(Calendar.MONTH, times[0]-1);
            startCal.set(Calendar.YEAR, times[2]);
        }
        else if (nodeName.equals("StartTime"))
        {
            int[] times = parseTimes(nodeText, ":");
            startCal.set(Calendar.HOUR_OF_DAY, times[0]);
            startCal.set(Calendar.MINUTE, times[1]);
            startCal.set(Calendar.SECOND, times[2]);
        }
        else if (nodeName.equals("EndDate"))
        {
            int[] times = parseDates(nodeText, "/");
            endCal.set(Calendar.DAY_OF_MONTH, times[1]);
            endCal.set(Calendar.MONTH, times[0]-1);
            endCal.set(Calendar.YEAR, times[2]);
        }
        else if (nodeName.equals("EndTime"))
        {
            int[] times = parseTimes(nodeText, ":");
            endCal.set(Calendar.HOUR_OF_DAY, times[0]);
            endCal.set(Calendar.MINUTE, times[1]);
            endCal.set(Calendar.SECOND, times[2]);
        }
        else if (bBallNodeMap.containsKey(nodeName)) curEvent.detailMap.put(bBallNodeMap.get(nodeName),
                                                                            nodeText);

        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++)
        {
            stack.push(list.item(i));
        }
        curEvent.myStart = (GregorianCalendar) startCal;
        curEvent.myEnd = (GregorianCalendar) endCal;

        return curEvent;

    }


    private int[] parseDates (String myText, String myRegex)
    {
        String[] myStrArray = myText.split(myRegex);
        int[] myIntArray = new int[myStrArray.length];
        for (int n = 0; n < myStrArray.length; n++)
        {
            myIntArray[n] = Integer.parseInt(myStrArray[n]);
        }
        return myIntArray;
    }


    private int[] parseTimes (String myText, String myRegex)
    {
        String[] myStrArray = myText.split(myRegex);
        int[] myIntArray = new int[myStrArray.length];
        for (int n = 0; n < 2; n++)
        {
            myIntArray[n] = Integer.parseInt(myStrArray[n]);
        }
        myIntArray[2] = Integer.parseInt(myStrArray[2].substring(0, 2));
        return myIntArray;
    }
}
