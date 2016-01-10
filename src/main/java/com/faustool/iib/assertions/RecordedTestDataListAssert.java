package com.faustool.iib.assertions;

import java.util.List;

import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;

import com.ibm.broker.config.proxy.RecordedTestData;
import com.ibm.broker.config.proxy.TestData;

public class RecordedTestDataListAssert extends GenericAssert<RecordedTestDataListAssert, List<RecordedTestData>> {

	public RecordedTestDataListAssert(List<RecordedTestData> actual) {
		super(RecordedTestDataListAssert.class, actual);
	}

	public static RecordedTestDataListAssert assertThat(List<RecordedTestData> actual) {
		return new RecordedTestDataListAssert(actual);
	}

	public RecordedTestDataListAssert hasPath(String... nodes) {
		return this;
	}

	public RecordedTestDataListAssert hasMessageAt(int stepAt, String expectedMessage) {
		TestData actualData = getActualTestDataAt(stepAt);
		Assertions.assertThat(actualData.getMessage()).isEqualTo(expectedMessage);
		return this;
	}

	protected TestData getActualTestDataAt(int stepAt) {
		Assertions.assertThat(this.actual).isNotNull().overridingErrorMessage(String.format("No steps were recorded"));
		Assertions.assertThat(this.actual).isNotEmpty().overridingErrorMessage(String.format("No steps were recorded"));

		TestData actualData = null;

		try {
			actualData = this.actual.get(stepAt).getTestData();
		} catch (IndexOutOfBoundsException e) {
			fail(String.format("The step you are looking for (%d) is out of bounds (min 0 and max %d)", stepAt,
					this.actual.size() - 1));
		}

		Assertions.assertThat(actualData).isNotNull()
				.overridingErrorMessage(String.format("The step %d contains no step data", stepAt));

		return actualData;
	}
}
