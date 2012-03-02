package input;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class GoogleCalParser extends InputParser
{
    public GoogleCalParser ()
    {
        super("GoogleCalSample.xml", "entry");
    }


    public Event subParsing (Node node,
                             Event curEvent,
                             Calendar startCal,
                             Calendar endCal,
                             Map<String, String> googleCalNodeMap)
    {
        googleCalNodeMap.put("description", "Description");
        googleCalNodeMap.put("id", "Link");
        googleCalNodeMap.put("name", "Author");
        googleCalNodeMap.put("email", "Author's Email");

        //check if it is one of the categories you want, and populate the corresponding field in event
        String nodeName = node.getNodeName();
        String nodeText = node.getTextContent();

        if (nodeName.startsWith("title")) curEvent.mySubject = nodeText;
        else if (nodeName.startsWith("content"))
        {
            try
            {
                String[] summaryArray = nodeText.split(":");
                if (summaryArray[0].equals("When"))
                {
                    String[] singleEventArray = nodeText.split(" ");

                    //parse and put in the dates and times
                    int myMonth = parseMonth(singleEventArray[2]);
                    startCal.set(Calendar.MONTH, myMonth);
                    endCal.set(Calendar.MONTH, myMonth);

                    String dayString = singleEventArray[3];
                    int myDay =
                        Integer.parseInt(dayString.substring(0,
                                                             dayString.length() - 1));
                    startCal.set(Calendar.DAY_OF_MONTH, myDay);
                    endCal.set(Calendar.DAY_OF_MONTH, myDay);

                    String yearString = singleEventArray[4];
                    int myYear = Integer.parseInt(yearString.substring(0, 4));
                    startCal.set(Calendar.YEAR, myYear);
                    endCal.set(Calendar.YEAR, myYear);
                    Character ch = yearString.charAt(0);
                    Character.isDigit(ch);

                    // take care of regular, then do all day events
                    if (Character.isDigit(singleEventArray[5].charAt(0)))
                    {
                        int myHour;
                        //if no colon
                        if (!singleEventArray[5].contains(":"))
                        {

                            myHour =
                                Integer.parseInt(singleEventArray[5].substring(0,
                                                                               singleEventArray[5].length() - 2));
                            startCal.set(Calendar.MINUTE, 0);
                        }
                        else
                        // if colon
                        {
                            myHour =
                                Integer.parseInt(singleEventArray[5].substring(0,
                                                                               singleEventArray[5].length() - 5));
                            int myMin;
                            myMin =
                                Integer.parseInt(singleEventArray[5].substring(singleEventArray[5].length() - 4,
                                                                               singleEventArray[5].length() - 2));
                            startCal.set(Calendar.MINUTE, myMin);
                        }
                        //check if pm
                        if (singleEventArray[5].substring(singleEventArray[5].length() - 2)
                                               .startsWith("p"))
                        {
                            myHour += 12;
                        }
                        startCal.set(Calendar.HOUR_OF_DAY, myHour);

                        int myEndHour;
                        //if no colon
                        if (!singleEventArray[7].contains(":"))
                        {

                            myEndHour =
                                Integer.parseInt(singleEventArray[7].substring(0,
                                                                               singleEventArray[7].length() - 10));
                            endCal.set(Calendar.MINUTE, 0);
                        }
                        else
                        // if colon
                        {
                            myEndHour =
                                Integer.parseInt(singleEventArray[7].substring(0,
                                                                               singleEventArray[7].length() - 13));
                            int myMin;
                            myMin =
                                Integer.parseInt(singleEventArray[7].substring(singleEventArray[7].length() - 12,
                                                                               singleEventArray[7].length() - 10));
                            endCal.set(Calendar.MINUTE, myMin);
                        }
                        //check if pm
                        if (singleEventArray[7].substring(singleEventArray[7].length() - 2)
                                               .startsWith("p"))
                        {
                            myEndHour += 12;
                        }
                        endCal.set(Calendar.HOUR_OF_DAY, myEndHour);
                    }
                    else
                    {//all day event
                        startCal.set(Calendar.HOUR_OF_DAY, 0);
                        endCal.set(Calendar.HOUR_OF_DAY, 23);
                        startCal.set(Calendar.MINUTE, 0);
                        endCal.set(Calendar.MINUTE, 59);
                    }
                }
                else if (summaryArray[0].startsWith("Recurring Event"))
                {// treat as all day event
                 //Recurring Event<br> First start: 2011-08-29 14:50:00 EDT <br> Duration: 4500 <br>Where: LSRC 106 <br>Event Status: confirmed
                    String[] recurSplit = nodeText.split(" ");
                    String[] recurDateArray = recurSplit[4].split("-");
                    int recurYear = Integer.parseInt(recurDateArray[0]);
                    int recurMonth = Integer.parseInt(recurDateArray[1]);
                    int recurDay = Integer.parseInt(recurDateArray[2]);

                    startCal.set(Calendar.YEAR, recurYear);
                    endCal.set(Calendar.YEAR, recurYear);
                    startCal.set(Calendar.MONTH, recurMonth);
                    endCal.set(Calendar.MONTH, recurMonth);
                    startCal.set(Calendar.DAY_OF_MONTH, recurDay);
                    endCal.set(Calendar.DAY_OF_MONTH, recurDay);

                    // figure out what a duration is
                    startCal.set(Calendar.HOUR_OF_DAY, 0);
                    endCal.set(Calendar.HOUR_OF_DAY, 23);
                    startCal.set(Calendar.MINUTE, 0);
                    endCal.set(Calendar.MINUTE, 59);
                }
            }
            catch (Exception error)
            {
                error.printStackTrace();
            }
            if (nodeText.contains("Event Description"))
            {
                int startIndex = nodeText.indexOf("Event Description");
                String[] endOfNodeText =
                    nodeText.substring(startIndex).split(":");
                curEvent.detailMap.put("Event Description", endOfNodeText[1]);
            }
        }
        else if (googleCalNodeMap.containsKey(nodeName)) curEvent.detailMap.put(googleCalNodeMap.get(nodeName),
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


    private int parseMonth (String month)
    {
        if (month.equals("Jan")) return 1;
        else if (month.equals("Feb")) return 2;
        else if (month.equals("Mar")) return 3;
        else if (month.equals("Apr")) return 4;
        else if (month.equals("May")) return 5;
        else if (month.startsWith("Jun")) return 6;
        else if (month.startsWith("Jul")) return 7;
        else if (month.equals("Aug")) return 8;
        else if (month.equals("Sep")) return 9;
        else if (month.equals("Oct")) return 10;
        else if (month.equals("Nov")) return 11;
        return 12;
    }

}
