import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class XMLtvEvent extends Event
{
    private static int fileCount = 0;
	
	public String myDescription;
    public String myLocation;
    public String mySource;
    public String mySeason;

    public XMLtvEvent(){
    
    }

    public String getLocation(){
        return myLocation;
    }
    public String getDescription()
    {
        return myDescription;
    }
    public String getSource ()
    {
        return mySource;
    }
    public String getSeason ()
    {
        return mySeason;
    }

    @Override
	public String generateDetailsHTML() {
		// TODO Add code to output element to HTML
		String html =  "<html> \n" +
		"<body> \n" +
		"<h4>"+mySubject+"</h4>" +
				"<p>Description: "+myDescription+"</p>" +
				"<p>Location: "+myLocation+"</p>" +
				"<p>Source: "+mySource+"</p>" +
				"<p>Season: "+mySeason+"</p>" +
				"<br /><a href=\"Calendar.html\">Back to Calendar</a>" +
		"</body>" +
		"</html>";
		String filename = "XMlTv"+fileCount+".html";
		FileWriter fstream;
		try {
			fstream = new FileWriter(filename);
			fileCount++;
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(html);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filename;
    }

	
}
