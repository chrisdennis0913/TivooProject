package output;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;


public class Tag {
	
	String type;
	String end;
	String inner = "";
	int length =0;
	public Tag(String type)
	{
		this.type = type;
		this.end = type;
	}
	
	public Tag(String type, String att, int width)
	{
		this.type=type;
		this.end=type;
		addAttribute(att,width);
	}
	
	public String getHTML()
	{
		return "<"+type+"> \n" + this.inner +"\n"+"</"+end+"> \n";
	}
	
	public void addInnerHTML(Tag inner)
	{
		this.inner += inner.getHTML();
	}
	
	public void addInnerHTML(String inner)
	{
		this.inner+=inner;
	}
	
	
	public void addAttribute(String att, int value)
	{
		if (att.equals("width"))
			type = type +" style=\""+att+":"+value+"px\"";
		else if (att.equals("height"))
			type = type +" style=\""+att+":"+value+"px\"";
		else
			type = type +" "+att+"='"+value+"' ";
	}
	
	
}
