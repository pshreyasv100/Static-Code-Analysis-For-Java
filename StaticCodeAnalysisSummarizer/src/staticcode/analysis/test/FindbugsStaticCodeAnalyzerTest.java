package staticcode.analysis.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import staticcode.analysis.FindbugsStaticCodeAnalyzer;
import staticcode.analysis.StaticCodeAnalyzer;

class FindbugsStaticCodeAnalyzerTest {

	
	StaticCodeAnalyzer instance;
	
	@BeforeEach
	void setUp() throws Exception {
		
		String findbugsSourcePath = ".";
		String findbugsOutputPath = "../reports/report2.xml";
		Map<String, String> optionsMap = new HashMap<String, String>();
		optionsMap.put("outputFormat", "xml");
		StaticCodeAnalyzer instance =  new FindbugsStaticCodeAnalyzer(findbugsSourcePath, findbugsOutputPath, optionsMap);
	}

	@AfterEach
	void tearDown() throws Exception {
	//	instance = null;
	}

	@Test
	void testGetCommand() {
		String expectedCommand = "cmd" + "/c" + "findbugs" + "-textui" + "-output" + "../reports/report2.xml" + "xml" + ".";
		assertEquals(expectedCommand, instance.getCommand());
	}

	@Test
	void testParseXMLToCSV() {
		fail("Not yet implemented");
	}

}
