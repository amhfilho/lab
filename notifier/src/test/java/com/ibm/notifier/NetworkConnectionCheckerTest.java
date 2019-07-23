package com.ibm.notifier;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
public class NetworkConnectionCheckerTest {
	
	@Test
	public void shouldReturnTrueForLocalhost() throws IOException{
		NetworkConnectionChecker checker = new NetworkConnectionChecker();
		Assert.assertTrue(checker.isConnected("http://www.ibm.com"));
	}
	@Test
	public void shouldReturnFalseForFakeServer() throws IOException {
		NetworkConnectionChecker checker = new NetworkConnectionChecker();
		Assert.assertFalse(checker.isConnected("http://fakeserverforthistest.com"));
	}

}
