package staticcode.analysis.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import staticcode.analysis.PMDStaticCodeAnalyzer;
import staticcode.analysis.StaticCodeAnalyzer;

public class PMDStaticCodeAnalyzerTest {

	@Test
	public void testParseXMLToCSV() throws ParserConfigurationException, SAXException, IOException, InterruptedException {
		
		String pmdSourcePath = "./src";
		String pmdOutputPath = "../reports/pmd_report.xml";
		Map<String, String> optionsMap = new HashMap<String, String>();
		optionsMap.put("outputFormat", "xml");
		StaticCodeAnalyzer instance =  new PMDStaticCodeAnalyzer(pmdSourcePath, pmdOutputPath, optionsMap);
		
		ProcessBuilder pb = new ProcessBuilder();
		Map<String, String> envMap = pb.environment();
		String path = envMap.get("Path");
		path += "../static-code-analyzers/pmd/bin;";
		path += "../static-code-analyzers/findbugs/bin;";
			
		pb.command(instance.getCommand());
		Process process = pb.start();
		process.waitFor();
		
		instance.parseXMLToCSV();
		
//		boolean check = new File("../reports/", "_reports.csv").exists();
		
		File f = new File("../reports/pmd_reports.csv");
		assert(f.exists());
		
		
	}

}
