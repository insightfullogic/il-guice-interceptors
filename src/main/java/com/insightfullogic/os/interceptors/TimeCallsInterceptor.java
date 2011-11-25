package com.insightfullogic.os.interceptors;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TimeCallsInterceptor implements MethodInterceptor {

	private final TimingResults results;

	public TimeCallsInterceptor(final TimingResults results) {
		this.results = results;
	}

	@Override
	public Object invoke(final MethodInvocation invoke) throws Throwable {
		long time = System.currentTimeMillis();
		try {
			return invoke.proceed();
		} finally {
			time = System.currentTimeMillis() - time;
			results.addTime(time, invoke.getMethod());
		}
	}
}
