package com.faustool.iib.assertions;

import java.util.List;

import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;

import com.ibm.broker.config.proxy.RecordedTestData;

public class RecordedListAssert extends GenericAssert<RecordedListAssert, List<RecordedTestData>> {

	public RecordedListAssert(List<RecordedTestData> actual) {
		super(RecordedListAssert.class, actual);
	}

	public static RecordedListAssert assertThat(List<RecordedTestData> actual) {
		return new RecordedListAssert(actual);
	}

	public RecordedListAssert hasPath(String... nodes) {
		return this;
	}

	protected StepAssert step(int stepAt) {
		Assertions.assertThat(this.actual).isNotEmpty().overridingErrorMessage(String.format("No steps were recorded"));

		RecordedTestData testData = null;

		try {
			testData = this.actual.get(stepAt);
		} catch (IndexOutOfBoundsException e) {
			fail(String.format("The step you are looking for (%d) is out of bounds (min 0 and max %d)", stepAt,
					this.actual.size() - 1));
		}

		return new StepAssert(testData);
	}

}
