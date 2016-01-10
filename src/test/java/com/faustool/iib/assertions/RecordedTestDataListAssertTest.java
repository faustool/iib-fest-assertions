package com.faustool.iib.assertions;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Text;

import com.ibm.broker.config.proxy.RecordedTestData;
import com.ibm.broker.config.proxy.TestData;

public class RecordedTestDataListAssertTest {

	private static Document doc;

	@BeforeClass
	public static void before() {
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		} catch (ParserConfigurationException e) {
			throw new AssertionError(e.getMessage(), e);
		}
	}

	@Test(expected = AssertionError.class)
	public void testGetActualTestDataAtEmptyList() {
		List<RecordedTestData> list = Collections.emptyList();
		RecordedTestDataListAssert assertion = new RecordedTestDataListAssert(list);
		assertion.getActualTestDataAt(0);
	}

	@Test(expected = AssertionError.class)
	public void testGetActualTestDataAtNullList() {
		RecordedTestDataListAssert assertion = new RecordedTestDataListAssert(null);
		assertion.getActualTestDataAt(0);
	}

	@Test(expected = AssertionError.class)
	public void testGetActualTestDataOutOfBoundsLessThanZero() {
		List<RecordedTestData> list = new ArrayList<>();

		list.add(mock(RecordedTestData.class));

		RecordedTestDataListAssert assertion = new RecordedTestDataListAssert(list);

		assertion.getActualTestDataAt(-1);
	}

	@Test(expected = AssertionError.class)
	public void testGetActualTestDataOutOfBoundsGreaterThanList() {
		List<RecordedTestData> list = new ArrayList<>();

		list.add(mock(RecordedTestData.class));

		RecordedTestDataListAssert assertion = new RecordedTestDataListAssert(list);

		assertion.getActualTestDataAt(1);
	}

	@Test(expected = AssertionError.class)
	public void testGetActualTestNullTestData() {
		List<RecordedTestData> list = new ArrayList<>();

		list.add(mock(RecordedTestData.class));

		RecordedTestDataListAssert assertion = new RecordedTestDataListAssert(list);

		assertion.getActualTestDataAt(0);
	}

	@Test
	public void testMessageNotSame() {
		List<RecordedTestData> list = new ArrayList<>();
		RecordedTestData rtd = mock(RecordedTestData.class);
		list.add(rtd);

		TestData actual = new TestData();
		actual.setMessage(newNode("some text"));
		
		when(rtd.getTestData()).thenReturn(actual);
		
		RecordedTestDataListAssert.assertThat(list).hasMessageAt(0, "some text");
	}

	protected Text newNode(String text) {
		return doc.createTextNode(text);
	}

}
