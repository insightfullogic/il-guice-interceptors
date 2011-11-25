package com.insightfullogic.os.interceptors;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class TimeCallsModule extends AbstractModule {

	private final boolean enabled;

	public TimeCallsModule(final boolean isEnabled) {
		enabled = isEnabled;
	}

	@Override
	protected void configure() {
		if (enabled) {
			final TimingResults results = new TimingResults();
			bindInterceptor(Matchers.any(), Matchers.annotatedWith(TimeCalls.class), new TimeCallsInterceptor(results));
			bind(TimingResults.class).toInstance(results);
		}
	}
}
