package staticcodeanalysis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client {

	public static void main(String[] args) throws IOException, InterruptedException {

		String pmdSourcePath = "C:\\bootcamp\\bootcamp-casestudy-1\\StaticCodeAnalysisSummarizer\\StaticCodeAnalysisSummarizer\\src";

		// Findbugs takes java project path as input for analysing
		String findbugsSourcePath = "C:\\bootcamp\\bootcamp-casestudy-1\\StaticCodeAnalysisSummarizer\\StaticCodeAnalysisSummarizer";

		String pmdOutputPath = "C:\\bootcamp\\bootcamp-casestudy-1\\StaticCodeAnalysisSummarizer\\reports\\report1.xml";
		String FindbugsOutputPath = "C:\\bootcamp\\bootcamp-casestudy-1\\StaticCodeAnalysisSummarizer\\reports\\report2.xml";

		Map<String, String> optionsMap = new HashMap<String, String>();
		optionsMap.put("outputFormat", "xml");

		List<StaticCodeAnalyzer> analyzers = new ArrayList<StaticCodeAnalyzer>();

		StaticCodeAnalyzer pmdAnalyzer = new PMDStaticCodeAnalyzer(pmdSourcePath, pmdOutputPath, optionsMap);
		StaticCodeAnalyzer findbugsAnalyzer = new FindbugsStaticCodeAnalyzer(findbugsSourcePath, FindbugsOutputPath, optionsMap);

		analyzers.add(pmdAnalyzer);
		analyzers.add(findbugsAnalyzer);
		
		for (StaticCodeAnalyzer analyzer : analyzers) {

			ProcessBuilder pb = new ProcessBuilder(analyzer.getCommand());
			Map<String, String> envMap = pb.environment();

			String path = envMap.get("Path");
			path += "C:\\bootcamp\\bootcamp-casestudy-1\\StaticCodeAnalysisSummarizer\\static-code-analyzers\\findbugs\\bin;";
			path += "C:\\bootcamp\\bootcamp-casestudy-1\\StaticCodeAnalysisSummarizer\\static-code-analyzers\\pmd\\bin;";
				
			envMap.put("Path", path);
			
			Process process = pb.start();
			process.waitFor();
		}
	}

}
