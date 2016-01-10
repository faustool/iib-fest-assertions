package com.faustool.iib.assertions;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.fest.assertions.Assertions;
import org.fest.assertions.BooleanAssert;
import org.fest.assertions.GenericAssert;
import org.fest.assertions.StringAssert;
import org.w3c.dom.Node;

public class XPathAssert extends GenericAssert<XPathAssert, Node> {

	private final XPath xpath;
	private String currentExpression;
	private final XPathAssertNamespaceContext nsContext = new XPathAssertNamespaceContext();

	public XPathAssert(Node actual) {
		super(XPathAssert.class, actual);
		this.xpath = XPathFactory.newInstance().newXPath();
		this.xpath.setNamespaceContext(nsContext);
	}

	public static XPathAssert assertThat(Node actual) {
		return new XPathAssert(actual);
	}

	public XPathAssert at(String xpathExpression) {
		this.currentExpression = xpathExpression;
		return this;
	}
	
	public BooleanAssert asBoolean() {
		if (actual == null)
			failure("The XML structure is null");

		Boolean result = null;

		try {
			XPathExpression exp = xpath.compile(currentExpression);
			result = (Boolean) exp.evaluate(actual, XPathConstants.BOOLEAN);
		} catch (XPathExpressionException e) {
			fail("Error compiling XPath", e);
		}

		return Assertions.assertThat(result);
	}

	public StringAssert asString() {
		if (actual == null)
			failure("The XML structure is null");

		String result = null;
		try {
			XPathExpression exp = xpath.compile(currentExpression);
			result = exp.evaluate(actual);

		} catch (XPathExpressionException e) {
			fail("Error compiling XPath", e);
		}

		return Assertions.assertThat(result);
	}

	public XPathAssert withNS(String prefix, String namespaceURI) {
		nsContext.declare(prefix, namespaceURI);
		return this;
	}

	public XPathAssert withNS(String defaultNamespaceURI) {
		nsContext.setDefaultNamespaceURI(defaultNamespaceURI);
		return this;
	}
}
