import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public abstract class InputParser
{
    protected List<Event> EventList;

    public List<Event> getListOfEvents ()
    {
        Collections.sort(EventList);
        return EventList;
    }

    public static class ParserFactory
    {
        public static InputParser generate (String filename)
        {
            if (filename.equals("DukeBasketBall.xml")) return new DukeBasketBallParser();

            else if (filename.equals("NFL.xml")) return new XMLtvParser();
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

}
