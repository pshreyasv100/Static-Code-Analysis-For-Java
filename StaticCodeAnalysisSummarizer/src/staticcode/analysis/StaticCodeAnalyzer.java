package staticcode.analysis;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public abstract class StaticCodeAnalyzer {
	
	public abstract String[] getCommand();
	
	public abstract void parseXMLToCSV() throws ParserConfigurationException, SAXException, IOException;
		
}