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

		String pmdSourcePath = "./src";

		// Findbugs takes java project path as input for analysing
		String findbugsSourcePath = ".";

		String pmdOutputPath = "../reports/report1.xml";
		String FindbugsOutputPath = "../reports/report2.xml";

		Map<String, String> optionsMap = new HashMap<String, String>();
		optionsMap.put("outputFormat", "xml");

		List<StaticCodeAnalyzer> analyzers = new ArrayList<StaticCodeAnalyzer>();

		StaticCodeAnalyzer pmdAnalyzer = new PMDStaticCodeAnalyzer(pmdSourcePath, pmdOutputPath, optionsMap);
		StaticCodeAnalyzer findbugsAnalyzer = new FindbugsStaticCodeAnalyzer(findbugsSourcePath, FindbugsOutputPath, optionsMap);

		analyzers.add(pmdAnalyzer);
		analyzers.add(findbugsAnalyzer);
		
		ProcessBuilder pb = new ProcessBuilder();
		Map<String, String> envMap = pb.environment();
		String path = envMap.get("Path");
		path += "../static-code-analyzers/pmd/bin;";
		path += "../static-code-analyzers/findbugs/bin;";
			
		envMap.put("Path", path);
		
		for (StaticCodeAnalyzer analyzer : analyzers) {

			pb.command(analyzer.getCommand());
			Process process = pb.start();
			process.waitFor();
			
			analyzer.parseXML();
		}
	}

}
