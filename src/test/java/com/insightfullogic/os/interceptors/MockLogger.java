package com.insightfullogic.os.interceptors;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

public class MockLogger extends Logger {

	@Override
	public boolean isEnabledFor(final Priority level) {
		return Level.INFO == level;
	}

	@Override
	public void log(final Priority priority, final Object message) {
		Assert.assertTrue(((String) message).startsWith(messages.get(counter)));
		counter++;
	}

	private final List<String> messages;
	private int counter;

	public MockLogger(final String... messages) {
		super("MockLogger");
		this.messages = Arrays.asList(messages);
		counter = 0;
	}

	public void checkFinished() {
		Assert.assertEquals(messages.size(), counter);
	}

}
