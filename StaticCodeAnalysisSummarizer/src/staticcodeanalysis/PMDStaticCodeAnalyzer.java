package staticcodeanalysis;


import java.util.Map;

public class PMDStaticCodeAnalyzer extends StaticCodeAnalyzer {

	public PMDStaticCodeAnalyzer(String sourceCodePath, String resultsPath, Map<String, String> optionsMap) {
		super(sourceCodePath, resultsPath, optionsMap);
	}

	@Override
	public String[] getCommand() {

		String[] command = {"cmd", "/c" , "pmd", "-d", sourceCodePath, "-f", optionsMap.get("outputFormat"), "-R", "rulesets/java/quickstart.xml", ">", resultsPath};
		return command;
	}
}