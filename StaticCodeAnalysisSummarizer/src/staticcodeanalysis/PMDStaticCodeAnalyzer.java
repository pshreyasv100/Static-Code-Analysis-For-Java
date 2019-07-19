package staticcodeanalysis;

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

	/**
	 * 
	 * @param sourceCodePath - PMD expects src of project as input
	 * @param resultsPath
	 * @param optionsMap
	 */
	public PMDStaticCodeAnalyzer(String sourceCodePath, String resultsPath, Map<String, String> optionsMap) {
		super(sourceCodePath, resultsPath, optionsMap);
	}

	@Override
	public String[] getCommand() {

		String[] command = { "cmd", "/c", "pmd", "-d", sourceCodePath, "-f", optionsMap.get("outputFormat"), "-R",
				"rulesets/java/quickstart.xml", ">", resultsPath };
		return command;
	}

	public void parseXML() throws ParserConfigurationException, SAXException, IOException {
		try {
			System.setOut(new PrintStream(new FileOutputStream("../reports/pmd_report.csv")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = factory.newDocumentBuilder();
		System.out.println("PMD result");
		System.out.println("Package," + "Classname," + "Startline," + "Endline," + "Error");
		Document doc = builder.parse("../reports/report1.xml");
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
						System.out.print(violation.getAttribute("package") + ",");
						System.out.print(violation.getAttribute("class") + ",");
						System.out.print(violation.getAttribute("beginline") + ",");
						System.out.print(violation.getAttribute("endline") + ",");
						System.out.print(violation.getTextContent()+ ",");
						System.out.println();
					}
				}
			}

		}
	}
}
