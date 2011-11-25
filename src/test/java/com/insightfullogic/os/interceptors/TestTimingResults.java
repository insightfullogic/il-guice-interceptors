package com.insightfullogic.os.interceptors;

import java.lang.reflect.Method;

import junit.framework.Assert;

import org.junit.Test;

import com.insightfullogic.os.interceptors.TimingResults;

public class TestTimingResults {

	@Test
	public void timingResultsStoresTimes() throws SecurityException, NoSuchMethodException {
		final TimingResults results = new TimingResults();

		final Method method = TestTimingResults.class.getMethod("timingResultsStoresTimes");
		results.addTime(5l, method);
		results.addTime(10l, method);
		Assert.assertEquals(2, results.numberOfCalls(TestTimingResults.class, "timingResultsStoresTimes"));
		Assert.assertEquals(15l, results.totalTime(TestTimingResults.class, "timingResultsStoresTimes"));
	}
}
