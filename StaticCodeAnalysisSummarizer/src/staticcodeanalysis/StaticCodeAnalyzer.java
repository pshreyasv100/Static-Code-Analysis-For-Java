package staticcodeanalysis;

import java.util.Map;

public abstract class StaticCodeAnalyzer {
	
	String sourceCodePath;
	String resultsPath;
	Map<String, String> optionsMap;
	
	
	public StaticCodeAnalyzer(String sourceCodePath,String resultsPath, Map<String, String> optionsMap)
	{
		this.sourceCodePath = sourceCodePath;
		this.resultsPath = resultsPath;
		this.optionsMap = optionsMap;
	}
	
	
	public abstract String[] getCommand();
	
}