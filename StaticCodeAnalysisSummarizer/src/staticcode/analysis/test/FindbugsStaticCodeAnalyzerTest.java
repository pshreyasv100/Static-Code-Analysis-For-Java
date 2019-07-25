package staticcode.analysis.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import staticcode.analysis.FindbugsStaticCodeAnalyzer;
import staticcode.analysis.PMDStaticCodeAnalyzer;
import staticcode.analysis.StaticCodeAnalyzer;

public class FindbugsStaticCodeAnalyzerTest {
	
	String sourceCodePath;
	String resultsPath;
	Map<String, String> optionsMap;
	StaticCodeAnalyzer instance;
	
	
	@Before
	public void setUp() {
		sourceCodePath = ".";
		resultsPath = "../reports/";
		optionsMap = new HashMap<String, String>();
		optionsMap.put("outputFormat", "xml");
		instance =  new FindbugsStaticCodeAnalyzer(sourceCodePath, resultsPath, optionsMap);
		
	}
	
	@Test
	public void testGetCommand() {
		System.out.println("running findbugs testGetCommand");
		String[] expectedCommand = {"cmd" , "/c" , "findbugs" , "-textui" , "-output" , "../reports/findbugs_report.xml" , "xml" , "."};
		String[] actualCommand = instance.getCommand();
		assertArrayEquals(expectedCommand, actualCommand);
	}

//	@Test
//	public void testParseXMLToCSV() throws ParserConfigurationException, SAXException, IOException, InterruptedException {
//		
//		System.out.println("running findbugs testParseXMLToCSV");
//		
//		ProcessBuilder pb = new ProcessBuilder();
//		Map<String, String> envMap = pb.environment();
//		String path = envMap.get("Path");
//		path += "../static-code-analyzers/pmd/bin;";
//		path += "../static-code-analyzers/findbugs/bin;";
//			
//		pb.command(instance.getCommand());
//		Process process = pb.start();
//		process.waitFor();
//		instance.parseXMLToCSV();	
//		boolean check = new File("../reports/", "findbugs_reports.csv").exists();
//		
//		//File f = new File("../reports/findbugs_reports.csv");
//		assert(check);
//		
//		
//	}

}
