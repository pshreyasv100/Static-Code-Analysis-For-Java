package staticcodeanalysis;

import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public abstract class StaticCodeAnalyzer {
	
	String sourceCodePath;
	String resultsPath;
	Map<String, String> optionsMap;
	
	
	public StaticCodeAnalyzer(String sourceCodePath,String resultsPath, Map<String, String> optionsMap)
	{
		this.sourceCodePath = sourceCodePath;
		this.resultsPath = resultsPath;
		this.optionsMap = optionsMap;
	}
	
	
	public abstract String[] getCommand();
	
	public abstract void parseXML() throws ParserConfigurationException, SAXException, IOException;
	
}