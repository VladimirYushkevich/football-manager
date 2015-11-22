package com.company.dao.xml;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Base class for performing XML specific methods.
 * 
 * @author vladimir.yushkevich
 *
 */
public abstract class XMLAbstractDAO implements XMLDAO {

	private File file;

	public NodeList readNodeListFromFile(String tagName) {

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

		DocumentBuilder documentBuilder;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();

			if (file.length() != 0) {
				Document document = documentBuilder.parse(file);
				document.getDocumentElement().normalize();

				return document.getElementsByTagName(tagName);
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	public void wrideNodeListToFile(List<Object> objects, File output) {

		DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder icBuilder;
		try {
			icBuilder = icFactory.newDocumentBuilder();
			Document doc = icBuilder.newDocument();
			String rootName = getTagName();
			Element mainRootElement = doc.createElement(rootName);
			doc.appendChild(mainRootElement);

			objects.forEach(n ->
							mainRootElement.appendChild(createNode(doc, n))
			);

			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult destination = new StreamResult(output);
			transformer.transform(source, destination);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public File getFile() {
		return file;
	}
	
	protected Node getPlayerElements(Document doc, Element element, String name,
			String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}

	public abstract Node createNode(Document doc, Object object);

	private String getTagName() {
		String path = file.getPath();
		String fileSeparator = System.getProperty("file.separator");
		return path.substring(path.lastIndexOf(fileSeparator) + 1).split(fileSeparator+"\\.")[0];
	}
}