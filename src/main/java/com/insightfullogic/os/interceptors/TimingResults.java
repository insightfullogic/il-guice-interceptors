package com.insightfullogic.os.interceptors;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class TimingResults {

	private Map<String, Long> times = new HashMap<String, Long>();
	private Map<String, Integer> calls = new HashMap<String, Integer>();

	void addTime(final long time, final Method method) {
		final String meth = method.toString();
		final Long oldTime = times.get(meth);
		if (oldTime == null)
			times.put(meth, time);
		else
			times.put(meth, oldTime + time);

		final Integer counter = calls.get(meth);
		if (counter == null)
			calls.put(meth, 1);
		else
			calls.put(meth, counter + 1);
	}

	@SuppressWarnings("rawtypes")
	public long totalTime(final Class<?> cls, final String methodName,
			final Class... arguments) {
		try {
			return times.get(cls.getMethod(methodName, arguments).toString());
		} catch (final SecurityException e) {
			throw new MethodLookupException(e);
		} catch (final NoSuchMethodException e) {
			throw new MethodLookupException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	public int numberOfCalls(final Class<?> cls, final String methodName,
			final Class... arguments) {
		try {
			return calls.get(cls.getMethod(methodName, arguments).toString());
		} catch (final SecurityException e) {
			throw new MethodLookupException(e);
		} catch (final NoSuchMethodException e) {
			throw new MethodLookupException(e);
		}
	}

	public void logResults(final Logger log, final Level level) {
		if (log.isEnabledFor(level)) {
			final TreeMap<Long, String> ordered = new TreeMap<Long, String>();
			for (final Entry<String, Long> e : times.entrySet()) {
				ordered.put(e.getValue(), e.getKey());
			}

			for (final Entry<Long, String> e : ordered.entrySet()) {
				final String method = e.getValue();
				final Long time = e.getKey();
				final Integer noCalls = calls.get(method);
				log.log(level, String.format(
						"%s was called %d times for a total time usage of %d",
						method, noCalls, time));
			}
		}
	}

	public void reset() {
		times = new HashMap<String, Long>();
		calls = new HashMap<String, Integer>();
	}
}
