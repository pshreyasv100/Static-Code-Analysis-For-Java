package staticcodeanalysis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Client {

	public static void main(String[] args) throws IOException, InterruptedException, ParserConfigurationException, SAXException {

		String pmdSourcePath = "C:\\Temp\\a\\bootcamp-casestudy-1\\StaticCodeAnalysisSummarizer\\src";

		// Findbugs takes java project path as input for analysing
		String findbugsSourcePath = "C:\\Temp\\a\\bootcamp-casestudy-1\\StaticCodeAnalysisSummarizer";

		String pmdOutputPath = "C:\\Temp\\a\\bootcamp-casestudy-1\\StaticCodeAnalysisSummarizer\\reports\\report1.xml";
		String FindbugsOutputPath = "C:\\Temp\\a\\bootcamp-casestudy-1\\StaticCodeAnalysisSummarizer\\reports\\report2.xml";

		Map<String, String> optionsMap = new HashMap<String, String>();
		optionsMap.put("outputFormat", "xml");

		List<StaticCodeAnalyzer> analyzers = new ArrayList<StaticCodeAnalyzer>();

		StaticCodeAnalyzer pmdAnalyzer = new PMDStaticCodeAnalyzer(pmdSourcePath, pmdOutputPath, optionsMap);
		StaticCodeAnalyzer findbugsAnalyzer = new FindbugsStaticCodeAnalyzer(findbugsSourcePath, FindbugsOutputPath, optionsMap);

		analyzers.add(pmdAnalyzer);
		//analyzers.add(findbugsAnalyzer);
		
		for (StaticCodeAnalyzer analyzer : analyzers) {

			ProcessBuilder pb = new ProcessBuilder(analyzer.getCommand());
			Map<String, String> envMap = pb.environment();

			String path = envMap.get("Path");
			path += "C:\\Temp\\a\\bootcamp-casestudy-1\\static-code-analyzers\\findbugs-3.0.1\\bin;";
			path += "C:\\Temp\\a\\bootcamp-casestudy-1\\static-code-analyzers\\pmd-bin-6.16.0\\bin;";
				
			envMap.put("Path", path);
			
			Process process = pb.start();
			process.waitFor();
			
			analyzer.parseXML();
			
		}
	}

}
