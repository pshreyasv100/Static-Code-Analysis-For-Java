package staticcodeanalysis;

import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class FindbugsStaticCodeAnalyzer extends StaticCodeAnalyzer{

	
	/**
	 * 
	 * @param sourceCodePath - findbugs expects project path
	 * @param resultsPath
	 * @param optionsMap
	 */
	public FindbugsStaticCodeAnalyzer(String sourceCodePath, String resultsPath, Map<String, String> optionsMap) {
		super(sourceCodePath, resultsPath, optionsMap);
	}

	
	@Override
	public String[] getCommand() {
		
		String[] command = {"cmd", "/c", "findbugs", "-textui" , "-output", resultsPath, optionsMap.get("outputFormat"), sourceCodePath};
		return command;
	}


	@Override
	public void parseXML() throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	

}