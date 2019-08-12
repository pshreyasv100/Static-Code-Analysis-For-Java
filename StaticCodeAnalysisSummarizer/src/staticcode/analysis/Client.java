package staticcode.analysis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Client {

	public static void main(String[] args) throws IOException, InterruptedException, ParserConfigurationException, SAXException {
		
		Scanner sc = new Scanner(System.in);
	    System.out.println("Enter source project directory");
	    String srcProjectDirectory = sc.nextLine(); 	
	    String resultsDirectory = "../reports/";
	    
	    System.out.println("Running static code analyzers ...");
	    System.out.println("Reports generated are stored in reports directory");	
	    
	    //Instantiating PMD
		Map<String, String> optionsMapPMD = new HashMap<String, String>();
		optionsMapPMD.put("outputFormat", "xml");
		optionsMapPMD.put("ruleset", "rulesets/java/quickstart.xml");
		StaticCodeAnalyzer pmdAnalyzer = new PMDStaticCodeAnalyzer(srcProjectDirectory, resultsDirectory, optionsMapPMD);	
		
		//Instantiating Findbugs
		Map<String, String> optionsMapFindbugs = new HashMap<String, String>();
		optionsMapFindbugs.put("outputFormat", "xml");
		StaticCodeAnalyzer findbugsAnalyzer = new FindbugsStaticCodeAnalyzer(srcProjectDirectory, resultsDirectory, optionsMapFindbugs);	
	  
		List<StaticCodeAnalyzer> analyzers = new ArrayList<StaticCodeAnalyzer>();
		analyzers.add(pmdAnalyzer);
		analyzers.add(findbugsAnalyzer);
		
		StaticCodeAnalyzer.runAnalyzers(analyzers);
		
	}

}
