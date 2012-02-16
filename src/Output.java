                                                   
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
class Output
{
    public static void createCalendar(String inputFilename)
    {
        BufferedWriter br= null;
        try
        {
            File file = new File("Calendar.html");
            FileWriter fw = new FileWriter(file);
            /* You can also give the path as C:\\Desktop\\fileWriter.txt */
            br = new BufferedWriter(fw);
            InputParser input = InputParser.ParserFactory.generate(inputFilename);

            List<Event> eventList = input.getListOfEvents();
            Collections.sort(eventList);
            Processor process = new Processor();
            
            List<Event> keyList1 = process.keywordFinder(eventList, "Duke");
            br.write("<html>");
            br.newLine();
            br.write("<body>");
            br.newLine();
            br.newLine();
            br.write("<h4>Table with subjects:</h4>");
            br.newLine();
            br.write("<table border='1'>");
            br.newLine();
            
            br.newLine();
            for(Event e: eventList){
                br.write("<tr>");
                br.write("<td>"+e.getSubject()+ "  <a href=\"" + e.generateDetailsHTML() + "\">Details</a></td>");
                br.write("</tr>");
                
            }
            
            br.write("</table>");
            br.write("</body>");
            br.write("</html>");
            br.close();
        }
        catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }
}