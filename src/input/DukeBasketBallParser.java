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
        super("DukeBasketball.xml", "Calendar");
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
            //12/30/2011
            String[] myDateArray=nodeText.split("/");
            startCal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(myDateArray[1]));
            startCal.set(Calendar.MONTH, Integer.parseInt(myDateArray[0]));
            startCal.set(Calendar.YEAR, Integer.parseInt(myDateArray[2]));
        }
        else if (nodeName.equals("StartTime"))
        {
            String[] hms = nodeText.split(":");
            startCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hms[0]));
            startCal.set(Calendar.MINUTE, Integer.parseInt(hms[1]));
            String[] secAM_PM = hms[2].split(" ");
            startCal.set(Calendar.SECOND, Integer.parseInt(secAM_PM[0]));
        }
        else if (nodeName.equals("EndDate"))
        {
            String[] myDateArray=nodeText.split("/");
            endCal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(myDateArray[1]));
            endCal.set(Calendar.MONTH, Integer.parseInt(myDateArray[0]));
            endCal.set(Calendar.YEAR, Integer.parseInt(myDateArray[2]));
        }
        else if (nodeName.equals("EndTime"))
        {
            String[] hms = nodeText.split(":");
            endCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hms[0]));
            endCal.set(Calendar.MINUTE, Integer.parseInt(hms[1]));
            String[] secAM_PM = hms[2].split(" ");
            endCal.set(Calendar.SECOND, Integer.parseInt(secAM_PM[0]));
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
}
