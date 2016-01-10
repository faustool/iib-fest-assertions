package com.faustool.iib.assertions;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.fest.assertions.Assertions;
import org.junit.Test;

import com.ibm.broker.config.proxy.RecordedTestData;

public class RecordedListAssertTest {

	@Test(expected = AssertionError.class)
	public void testStepEmptyList() {
		List<RecordedTestData> list = Collections.emptyList();
		RecordedListAssert assertion = new RecordedListAssert(list);
		assertion.step(0);
	}

	@Test(expected = AssertionError.class)
	public void testStepNullList() {
		RecordedListAssert assertion = new RecordedListAssert(null);
		assertion.step(0);
	}

	@Test(expected = AssertionError.class)
	public void testStepDataOutOfBoundsLessThanZero() {
		List<RecordedTestData> list = new ArrayList<>();

		list.add(mock(RecordedTestData.class));

		RecordedListAssert assertion = new RecordedListAssert(list);

		assertion.step(-1);
	}

	@Test(expected = AssertionError.class)
	public void testStepDataOutOfBoundsGreaterThanList() {
		List<RecordedTestData> list = new ArrayList<>();

		list.add(mock(RecordedTestData.class));

		RecordedListAssert assertion = new RecordedListAssert(list);

		assertion.step(1);
	}

	@Test
	public void testStep0() {
		List<RecordedTestData> list = new ArrayList<>();

		list.add(mock(RecordedTestData.class));

		RecordedListAssert assertion = new RecordedListAssert(list);

		Assertions.assertThat(assertion.step(0)).isNotNull();
	}
	
}
