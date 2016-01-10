package com.faustool.iib.assertions;

import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;
import org.fest.assertions.StringAssert;

import com.ibm.broker.config.proxy.RecordedTestData;
import com.ibm.broker.config.proxy.TestData;

public class StepAssert extends GenericAssert<StepAssert, RecordedTestData> {

	private final TestData actualData;

	public StepAssert(RecordedTestData actual) {
		super(StepAssert.class, actual);
		if (actual != null)
			actualData = actual.getTestData();
		else
			actualData = null;
	}

	public static StepAssert assertThat(RecordedTestData actual) {
		return new StepAssert(actual);
	}

	public StringAssert message() {
		if (actualData == null)
			return Assertions.assertThat((String) null);

		return Assertions.assertThat(actualData.getMessage());
	}

	public XPathAssert messageNode() {
		if (actualData == null)
			return new XPathAssert(null);

		return XPathAssert.assertThat(actualData.getMessageNode());
	}

}
