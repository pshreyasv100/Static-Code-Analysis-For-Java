package staticcode.analysis.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import staticcode.analysis.FindbugsStaticCodeAnalyzer;
import staticcode.analysis.PMDStaticCodeAnalyzer;
import staticcode.analysis.StaticCodeAnalyzer;

public class PMDStaticCodeAnalyzerTest {

	String sourceCodePath;
	String resultsPath;
	Map<String, String> optionsMap;
	StaticCodeAnalyzer instance;

	@Before
	public void setUp() {
		sourceCodePath = "C:\\bootcamp\\java\\code\\MyTrainingProject";
		resultsPath = "../reports/";
		optionsMap = new HashMap<String, String>();
		optionsMap.put("outputFormat", "xml");
		optionsMap.put("ruleset", "rulesets/java/quickstart.xml");
		instance = new PMDStaticCodeAnalyzer(sourceCodePath, resultsPath, optionsMap);
	}

	@Test
	public void testGetCommand() {
		System.out.println("running pmd testGetCommand");

		String[] expectedCommand = { "cmd", "/c", "pmd", "-d", sourceCodePath, "-f", optionsMap.get("outputFormat"),
				"-R", optionsMap.get("ruleset"), ">",
				resultsPath + "pmd_report" + "." + optionsMap.get("outputFormat") };
		
		String[] actualCommand = instance.getCommand();
		assertArrayEquals(expectedCommand, actualCommand);
	}

	@Test
	public void testParseXMLToCSV()
			throws ParserConfigurationException, SAXException, IOException, InterruptedException {

		System.out.println("running pmd testParseXMLToCSV");
		List<StaticCodeAnalyzer> analyzers = new ArrayList<StaticCodeAnalyzer>();
		analyzers.add(instance);		
		StaticCodeAnalyzer.runAnalyzers(analyzers);
		
		boolean check = new File("../reports/", "pmd_reports.csv").exists();
		assert (check);
	}

}
