package staticcode.analysis;

import java.io.IOException;
import java.util.Map;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FindbugsStaticCodeAnalyzer extends StaticCodeAnalyzer {

	
	private final String CSV_OUTPUT_PATH = "../reports/findbugs_report.csv";
	private String sourceCodePath;
	private String resultsPath;
	private Map<String, String> optionsMap;
	
	
	public FindbugsStaticCodeAnalyzer(String sourceCodePath, String resultsPath, Map<String, String> optionsMap) {
		
		this.sourceCodePath = sourceCodePath;
		this.resultsPath = resultsPath + "findbugs_report"+ "." + optionsMap.get("outputFormat");
		this.optionsMap = optionsMap;
		
	}


	@Override
	public String[] getCommand() {

		String[] command = { "cmd", "/c", "findbugs", "-textui", "-output", resultsPath , optionsMap.get("outputFormat"), sourceCodePath};
		return command;
	}

	@Override
	public void parseXMLToCSV() throws ParserConfigurationException, SAXException, IOException {

		try {
			
			System.setOut(new PrintStream(new FileOutputStream(CSV_OUTPUT_PATH)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(this.resultsPath);
		NodeList allBugs = doc.getElementsByTagName("BugInstance");
		System.out.println("Findbugs Results");
		System.out.println("Error Type," + "Error Category," + "Classname," + "Startline," + "Endline");
		
		for (int i = 0; i < allBugs.getLength(); i++) {
			Node bugInstance = allBugs.item(i);
			if (bugInstance.getNodeType() == Node.ELEMENT_NODE) {
				Element bug = (Element) bugInstance;
				System.out.print(bug.getAttribute("type") + ",");
				System.out.print(bug.getAttribute("category") + ",");
				NodeList bugInfo = bug.getChildNodes();
				
				String methodStartLine = null;
				String methodEndLine = null;
				String bugSourceLine = null;
				String className = null;
				boolean noSourceLineTag = true;
				
				for(int j=0; j < bugInfo.getLength(); j++) {
					
					if(bugInfo.item(j).getNodeType() == Node.ELEMENT_NODE){
						Element currentNode = (Element) bugInfo.item(j);
						if(currentNode.getNodeName().equals("Method")) {
							NodeList methodInfo = currentNode.getChildNodes();
							if(methodInfo.item(0).getNodeType() == Node.ELEMENT_NODE) {
								methodStartLine = ((Element) methodInfo.item(0)).getAttribute("start");
								methodEndLine = ((Element) methodInfo.item(0)).getAttribute("end");
								className = ((Element) methodInfo.item(0)).getAttribute("classname");
							}
						}
						
						if(bugInfo.item(j).getNodeName().equals("SourceLine")) {
							bugSourceLine = ((Element) bugInfo.item(j)).getAttribute("start");
							className = ((Element) bugInfo.item(j)).getAttribute("classname");
							noSourceLineTag = false;
						}	
					}
				}
				
				System.out.print(className + ",");
				if(noSourceLineTag) {
					System.out.print(methodStartLine + ",");
					System.out.print(methodEndLine + ",");
				}
				else {
					System.out.print(bugSourceLine + ",");
					System.out.print(bugSourceLine + ",");
				}
				
			System.out.println();

			}
		}

	}

}