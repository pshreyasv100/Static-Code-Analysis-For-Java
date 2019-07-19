package staticcodeanalysis;

import java.util.Map;

public class FindbugsStaticCodeAnalyzer extends StaticCodeAnalyzer{

	public FindbugsStaticCodeAnalyzer(String sourceCodePath, String resultsPath, Map<String, String> optionsMap) {
		super(sourceCodePath, resultsPath, optionsMap);
	}

	
	@Override
	public String[] getCommand() {
		
		String[] command = {"cmd", "/c", "findbugs", "-textui" , "-output", resultsPath, optionsMap.get("outputFormat"), sourceCodePath};
		return command;
	}
	

}