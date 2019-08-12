package staticcode.analysis;

import java.util.Map;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PMDStaticCodeAnalyzer extends StaticCodeAnalyzer {

	
	private final String CSV_OUTPUT_PATH = "../reports/pmd_report.csv";
	private String sourceCodePath;
	private String resultsPath;
	private Map<String, String> optionsMap;
	
	
	public PMDStaticCodeAnalyzer(String sourceCodePath, String resultsPath, Map<String, String> optionsMap) {
		this.sourceCodePath = sourceCodePath;
		this.resultsPath = resultsPath + "pmd_report"+ "." + optionsMap.get("outputFormat");
		this.optionsMap = optionsMap;	
	}

	@Override
	public String[] getCommand() {
		String[] command = { "cmd", "/c", "pmd", "-d", sourceCodePath, "-f", optionsMap.get("outputFormat"), "-R",
				optionsMap.get("ruleset"), ">", resultsPath};
		
		return command;
	}

	public void parseXMLToCSV() throws ParserConfigurationException, SAXException, IOException {
		try {
			System.setOut(new PrintStream(new FileOutputStream(CSV_OUTPUT_PATH)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = factory.newDocumentBuilder();
		System.out.println("PMD result");
		System.out.println("Error Type," + "Error Category," + "Classname," + "Startline," + "Endline,");
		Document doc = builder.parse(this.resultsPath);
		NodeList fileList = doc.getElementsByTagName("file");

		for (int i = 0; i < fileList.getLength(); i++) {
			Node p = fileList.item(i);
			if (p.getNodeType() == Node.ELEMENT_NODE) {
				Element file = (Element) p;

				NodeList violationList = file.getChildNodes();
				for (int j = 0; j < violationList.getLength(); j++) {
					Node n = violationList.item(j);
					if (n.getNodeType() == Node.ELEMENT_NODE) {
						Element violation = (Element) n;
						System.out.print(violation.getAttribute("rule") + ",");
						System.out.print(violation.getAttribute("ruleset") + ",");
						System.out.print(violation.getAttribute("class") + ",");
						System.out.print(violation.getAttribute("beginline") + ",");
						System.out.print(violation.getAttribute("endline") + ",");
						//System.out.print(violation.getTextContent()+ ",");
						System.out.println();
					}
				}
			}

		}
	}

}
