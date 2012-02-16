                                                   
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;
class Output
{
    public static void main(String args[])
    {
        BufferedWriter br= null;
        try
        {
            File file = new File("fileWriter.txt");
            FileWriter fw = new FileWriter(file);
            /* You can also give the path as C:\\Desktop\\fileWriter.txt */
            br = new BufferedWriter(fw);
            
            DukeBasketBallParser input = new DukeBasketBallParser();
            ArrayList<DukeBasketballEvent> eventList = (ArrayList<DukeBasketballEvent>) input.getListOfEvents();
            Processor process = new Processor();
            
            ArrayList<DukeBasketballEvent> keyList1 = (ArrayList<DukeBasketballEvent>) process.keywordFinder(eventList, "Duke");
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
            for(DukeBasketballEvent e: keyList1){
                br.write("<tr>");
                br.write("<td>"+e.getSubject()+"</td>");
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