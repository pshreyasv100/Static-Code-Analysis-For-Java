package staticcode.analysis;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public abstract class StaticCodeAnalyzer {

	public abstract String[] getCommand();

	public abstract void parseXMLToCSV() throws ParserConfigurationException, SAXException, IOException;

	public static void runAnalyzers(List<StaticCodeAnalyzer> analyzers)
			throws IOException, InterruptedException, ParserConfigurationException, SAXException {

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
			analyzer.parseXMLToCSV();

		}
	}

}