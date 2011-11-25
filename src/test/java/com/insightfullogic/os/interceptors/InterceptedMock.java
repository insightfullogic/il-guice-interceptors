package com.insightfullogic.os.interceptors;

import com.insightfullogic.os.interceptors.TimeCalls;

import junit.framework.Assert;

public class InterceptedMock {

	private String check;

	@TimeCalls
	public void interceptedMethod(final String against) {
		Assert.assertEquals(getCheck(), against);
		try {
			Thread.sleep(100);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

	@TimeCalls
	public void interceptedException() {
		throw new RuntimeException("fail");
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(final String check) {
		this.check = check;
	}

}
