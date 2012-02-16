
public class XMLtvEvent extends Event
{
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
}
