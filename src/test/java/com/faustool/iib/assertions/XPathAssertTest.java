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
		XPathAssert.assertThat(doc).at("/root").asBoolean().isTrue();
	}

	@Test
	public void testHasNotRoot() {
		Node root = doc.createElement("root");
		doc.appendChild(root);
		XPathAssert.assertThat(doc).at("/rootNode").asBoolean().isFalse();
	}

	@Test
	public void testValueOfNull() {
		Node root = doc.createElement("root");
		doc.appendChild(root);
		XPathAssert.assertThat(doc).at("/root").asString().isEmpty();
	}

	@Test
	public void testValueOfNotFound() {
		Node root = doc.createElement("root");
		doc.appendChild(root);
		// String XPath expressions never return null
		XPathAssert.assertThat(doc).at("/rootNode").asString().isEmpty();
	}

	@Test
	public void testValueOfSomething() {
		Node root = doc.createElement("root");
		doc.appendChild(root);
		root.setTextContent("something");
		XPathAssert.assertThat(doc).at("/root").asString().isEqualTo("something");
	}

	@Test
	public void testNSIgnore() {
		Node root = doc.createElementNS("http://test", "root");
		doc.appendChild(root);
		XPathAssert.assertThat(doc).at("/root").asBoolean().isFalse();
	}

	@Test
	public void testNSWithPrefix() {
		Node root = doc.createElementNS("http://test", "root");
		doc.appendChild(root);
		XPathAssert.assertThat(doc).withNS("ns", "http://test").at("/ns:root").asBoolean().isTrue();
	}

	@Test(expected = AssertionError.class)
	public void testNSWithWrongPrefix() {
		Node root = doc.createElementNS("http://test", "root");
		doc.appendChild(root);
		XPathAssert.assertThat(doc).withNS("ns", "http://test").at("/sn:root").asBoolean();
	}

	@Test
	public void testNSWithDefault() {
		Node root = doc.createElementNS("http://test", "root");
		doc.appendChild(root);
		XPathAssert.assertThat(doc).withNS("http://test").at("/:root").asBoolean().isTrue();
	}

	@Test
	public void testNSWithWrongDefault() {
		Node root = doc.createElementNS("http://test", "root");
		doc.appendChild(root);
		XPathAssert.assertThat(doc).withNS("http://asd").at("/:root").asBoolean().isFalse();
	}
}
