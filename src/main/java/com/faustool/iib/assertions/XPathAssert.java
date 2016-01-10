package com.faustool.iib.assertions;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;
import org.fest.assertions.StringAssert;
import org.w3c.dom.Node;

public class XPathAssert extends GenericAssert<XPathAssert, Node> {

	private final XPathFactory xpathFactory;

	public XPathAssert(Node actual) {
		super(XPathAssert.class, actual);
		this.xpathFactory = XPathFactory.newInstance();
	}

	public static XPathAssert assertThat(Node actual) {
		return new XPathAssert(actual);
	}

	public XPathAssert has(String xpathString) {
		if (actual == null)
			failure("The XML structure is null");

		try {
			XPathExpression xpath = xpathFactory.newXPath().compile(xpathString);
			Boolean result = (Boolean) xpath.evaluate(actual, XPathConstants.BOOLEAN);

			if (!result)
				failure(String.format("Nothing could be found at path %s", xpathString));

		} catch (XPathExpressionException e) {
			fail("Error compiling XPath", e);
		}

		return this;
	}

	public XPathAssert hasNot(String xpathString) {
		if (actual == null)
			failure("The XML structure is null");

		try {
			XPathExpression xpath = xpathFactory.newXPath().compile(xpathString);
			Boolean result = (Boolean) xpath.evaluate(actual, XPathConstants.BOOLEAN);

			if (result)
				failure(String.format("There is a node at path %s", xpathString));

		} catch (XPathExpressionException e) {
			fail("Error compiling XPath", e);
		}

		return this;
	}

	public StringAssert valueOf(String xpathString) {
		if (actual == null)
			failure("The XML structure is null");

		String result = null;
		try {
			XPathExpression xpath = xpathFactory.newXPath().compile(xpathString);
			result = xpath.evaluate(actual);

		} catch (XPathExpressionException e) {
			fail("Error compiling XPath", e);
		}

		return Assertions.assertThat(result);
	}

}
