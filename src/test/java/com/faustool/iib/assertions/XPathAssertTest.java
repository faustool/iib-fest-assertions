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
	public void testHasRoot() {
		Node root = doc.createElement("root");
		XPathAssert.assertThat(root).has("/root");
	}

	@Test
	public void testHasNotRoot() {
		Node root = doc.createElement("root");
		XPathAssert.assertThat(root).hasNot("/rootNode");
	}

	@Test
	public void testValueOfNull() {
		Node root = doc.createElement("root");
		XPathAssert.assertThat(root).valueOf("/root").isNull();
	}
	
	@Test
	public void testValueOfSomething() {
		Node root = doc.createElement("root");
		root.appendChild(doc.createTextNode("something"));
		XPathAssert.assertThat(root).valueOf("/root/text()").isEqualTo("something");
	}
	
}
