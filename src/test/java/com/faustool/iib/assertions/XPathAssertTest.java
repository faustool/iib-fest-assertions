package com.faustool.iib.assertions;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class XPathAssertTest {

	public Document doc;

	@Before
	public void before() throws ParserConfigurationException {
		doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
	}

	@Test
	public void testBooleanTrue() {
		Node root = doc.createElement("root");
		doc.appendChild(root);
		XPathAssert.assertThat(doc).booleanOf("/root").isTrue();
	}

	@Test
	public void testHasNotRoot() {
		Node root = doc.createElement("root");
		doc.appendChild(root);
		XPathAssert.assertThat(doc).booleanOf("/rootNode").isFalse();
	}

	@Test
	public void testValueOfNull() {
		Node root = doc.createElement("root");
		doc.appendChild(root);
		XPathAssert.assertThat(doc).stringOf("/root").isEmpty();
	}

	@Test
	public void testValueOfNotFound() {
		Node root = doc.createElement("root");
		doc.appendChild(root);
		// String XPath expressions never return null
		XPathAssert.assertThat(doc).stringOf("/rootNode").isEmpty();
	}

	@Test
	public void testValueOfSomething() {
		Node root = doc.createElement("root");
		doc.appendChild(root);
		root.setTextContent("something");
		XPathAssert.assertThat(doc).stringOf("/root").isEqualTo("something");
	}

}
