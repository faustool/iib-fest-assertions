package com.faustool.iib.assertions;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import com.ibm.broker.config.proxy.RecordedTestData;
import com.ibm.broker.config.proxy.TestData;

public class StepAssertTest {
	
	public Document doc;
	
	@Before
	public void before() throws ParserConfigurationException {
		doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
	}

	@Test
	public void testMessageNullData() {
		RecordedTestData rtd = new RecordedTestData(null, null);
		Assertions.assertThat(StepAssert.assertThat(rtd).message()).isNotNull();
		StepAssert.assertThat(rtd).message().isNull();
	}

	@Test
	public void testMessageNullMessage() {
		RecordedTestData rtd = new RecordedTestData(null, new TestData());
		Assertions.assertThat(StepAssert.assertThat(rtd).message()).isNotNull();
		StepAssert.assertThat(rtd).message().isNotNull();
	}
	
	@Test
	public void testMessageSomeMessage() {
		TestData testData = new TestData();
		RecordedTestData rtd = new RecordedTestData(null, testData);
		testData.setMessage(doc.createTextNode("some text"));
		
		Assertions.assertThat(StepAssert.assertThat(rtd).messageNode()).isNotNull();
		StepAssert.assertThat(rtd).messageNode().isNotNull();
	}

	@Test
	public void testMessageNodeNullData() {
		RecordedTestData rtd = new RecordedTestData(null, null);
		Assertions.assertThat(StepAssert.assertThat(rtd).messageNode()).isNotNull();
		StepAssert.assertThat(rtd).messageNode().isNull();
	}

	@Test
	public void testMessageNodeNullMessageNode() {
		RecordedTestData rtd = new RecordedTestData(null, new TestData());
		Assertions.assertThat(StepAssert.assertThat(rtd).messageNode()).isNotNull();
		StepAssert.assertThat(rtd).messageNode().isNull();
	}
	
	@Test
	public void testMessageNodeSomeMessageNode() {
		TestData testData = new TestData();
		RecordedTestData rtd = new RecordedTestData(null, testData);
		testData.setMessage(doc.createTextNode("some text"));
		
		Assertions.assertThat(StepAssert.assertThat(rtd).messageNode()).isNotNull();
		StepAssert.assertThat(rtd).messageNode().isNotNull();
	}

}
